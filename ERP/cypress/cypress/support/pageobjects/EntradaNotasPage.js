/// <reference types="Cypress" />

import entradaElements from '../elements/Entrada_notasElements'
import CamposGenericos from '../pageobjects/GenericPage'
//import DB from '../../support/db/bd_validacao'
const camposGenericos = new CamposGenericos()
//const banco = new DB()


class EntradaNotas {

    preencherDadosIniciais_manual(fornecedor, entidade, numeroNota, serie) {
        cy.visit((Cypress.env('baseUrl')) + '/server/erp/estoque/entradas/criar')
        camposGenericos.campoPesquisaAberto(entradaElements.DADOSINICIAIS.FORNECEDOR, fornecedor)
        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.ENTIDADE, entidade)
        camposGenericos.preencherInput(entradaElements.DADOSINICIAIS.NUMERONOTA, numeroNota)
        camposGenericos.preencherInput(entradaElements.DADOSINICIAIS.SERIE, serie)
        camposGenericos.campoPesquisa(entradaElements.DADOSINICIAIS.OPERACAO, 'compra p/ comercializa%o', 'Compra p/ comercialização')
    }

    preencherDadosIniciais_tiposNota(tipoNota) {
        let empresa = '7 - TESTE AUTOMATIZADO - LUCRO REAL'
        this.preencherDadosIniciais_manual(empresa, '1010', '1')
        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.TIPONOTA, tipoNota)
        this.clicarBotao(entradaElements.BTN_SALVAR)
    }

    preencherDadosIniciais_comChave() {
        this.preencherDadosIniciais_manual('EMPRESA NFE', '7 - TESTE AUTOMATIZADO - LUCRO REAL', '562', '4')
        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.TIPONOTA, 55)
        camposGenericos.preencherInput(entradaElements.DADOSINICIAIS.CHAVE, '42181275552133001303550040000005621722790267')
        cy.get(entradaElements.DADOSINICIAIS.DATAEMISSAO)
            .clear()
            .type('20122018')
        cy.get(entradaElements.DADOSINICIAIS.CHAVE).focus()
        this.clicarBotao(entradaElements.BTN_SALVAR)
    }

    preencherDadosIniciais_xml(nomeXML, conciliaNota, conciliaSistema) {
        cy.visit((Cypress.env('baseUrl')) + '/server/erp/estoque/entradas/criar')
        cy.get(entradaElements.DADOSINICIAIS.BTN_IMPORTAR_XML).click()
        cy.get(entradaElements.IMPORTAR_XML.ARQUIVO_FILE).focus()

        // cy.get(entradaElements.IMPORTAR_XML.IMPORTAR_ARQUIVO).invoke('removeAttr', 'readonly')
        // cy.get(entradaElements.IMPORTAR_XML.ARQUIVO_FILE).invoke('removeAttr', 'readonly')

        cy.fixture(nomeXML).then(importfile => { 
            cy.log(importfile)
            cy.get(entradaElements.IMPORTAR_XML.ARQUIVO_FILE).upload({ fileContent: importfile, fileName: nomeXML, mimeType: 'text/xml', encoding: 'utf-8' })
        })

        this.conciliarXML(conciliaNota, conciliaSistema)
        cy.get(entradaElements.IMPORTAR_XML.BTN_IMPORTAR).click()

        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.ENTIDADE, 'TESTE AUTOMATIZADO - LUCRO REAL')
        camposGenericos.campoPesquisa(entradaElements.DADOSINICIAIS.OPERACAO, 'compra p/ comercializa%o', 'Compra p/ comercialização')
        this.clicarBotao(entradaElements.BTN_SALVAR)
    }

    conciliarXML(nota, sistema) {
        switch (nota) {

            case 'codigo_produto':
                cy.get(entradaElements.IMPORTAR_XML.CONCILIA_NOTA).contains('Código do produto').click()
                break;

            case 'codigo_barras':
                cy.get(entradaElements.IMPORTAR_XML.CONCILIA_NOTA).contains('Código de barras').click()
                break;
        }

        switch (sistema) {
            case 'codigo_interno':
                cy.get(entradaElements.IMPORTAR_XML.CONCILIA_SISTEMA).contains('Código interno').click()
                break;

            case 'codigo_barras':
                cy.get(entradaElements.IMPORTAR_XML.CONCILIA_SISTEMA).contains('Código de barras').click()
                break;
        }
    }

    preencherCTE(regime) {
        cy.get(entradaElements.VALORESDADOSADICIONAIS.CTE).click()
        cy.get(entradaElements.CONHECIMENTOTRANSPORTE.NUMERO, { timeout: 3000 }).type('2563')
        camposGenericos.campoPesquisa(entradaElements.CONHECIMENTOTRANSPORTE.PRESTADORSERVICO, 'TRANSPORTADORA LTDA')
        cy.get(entradaElements.CONHECIMENTOTRANSPORTE.VALORICMS).type('4,50')
        cy.get(entradaElements.CONHECIMENTOTRANSPORTE.VALORTOTAL)
        .type('25,00')
        .type('{tab}')
        .type('{tab}')

        switch ('regime') {
            case 'simples':
                cy.get(entradaElements.CONHECIMENTOTRANSPORTE.VALORPIS).should('not.exist')
                cy.get(entradaElements.CONHECIMENTOTRANSPORTE.VALORCOFINS).should('not.exist')        
              break;
            case 'presumido':
                cy.get(entradaElements.CONHECIMENTOTRANSPORTE.VALORPIS).type('0,41')
                cy.get(entradaElements.CONHECIMENTOTRANSPORTE.VALORCOFINS).type('1,90')
                break;
                case 'real':
                    cy.get(entradaElements.CONHECIMENTOTRANSPORTE.VALORPIS).type('0,41')
                    cy.get(entradaElements.CONHECIMENTOTRANSPORTE.VALORCOFINS).type('1,90')
                break;

        }
        this.clicarBotao(entradaElements.BTN_SALVAR)
        this.clicarBotao(entradaElements.BTN_SALVAR)
    }


    incluirProdutos(lista_produtos) {
        lista_produtos.forEach(lista_produtos => {
            cy.get(entradaElements.PRODUTOS.PRODUTO)
                .click()
                .type(lista_produtos)
            cy.get('.modal-content', { timeout: 3000 })
            camposGenericos.preencherInput(entradaElements.PRODUTOS.QUANTIDADE, '10000')
            this.clicarBotao(entradaElements.PRODUTOS.BTN_ADICIONAR)
        })
        this.clicarBotao(entradaElements.BTN_SALVAR)
    }

    clicarBotao(elementoBotao) {
        cy.get(entradaElements.LOAD_PAGINA, { timeout: 1000000 }).should('not.exist')
        cy.get(elementoBotao)
            .should('not.have.attr', 'disabled', { timeout: 100000 })
            .click()
        cy.get('.noty_type__error', { timeout: 3000 }).should('not.exist').log(entradaElements.MENSAGEM)
    }

    validarMensagem(mensagem) {
        cy.get(entradaElements.MENSAGEM).should('contain', mensagem)
    }

    excluirUtimaEntrada(numeroNota) {
        cy.get(entradaElements.PROCESSANDO, { timeout: 10000 }).should('not.exist')
        cy.xpath(entradaElements.PESQUISA.DOCUMENTO(numeroNota)).click()
        cy.get(entradaElements.PESQUISA.BTN_EXCLUIR)
            .click()
        cy.get(entradaElements.PESQUISA.BTN_CONFIRMAR).click()
        cy.get(entradaElements.LOAD_PAGINA, { timeout: 100000 }).should('not.exist')
        cy.get(entradaElements.MENSAGEM, { timeout: 100000 })
            .should('contain', 'foi excluida com sucesso')
    }
}

export default EntradaNotas;