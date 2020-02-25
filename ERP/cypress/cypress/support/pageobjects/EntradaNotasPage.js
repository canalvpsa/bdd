/// <reference types="Cypress" />

import entradaElements from '../elements/Entrada_notasElements'
import CamposGenericos from '../pageobjects/GenericPage'
//import DB from '../../support/db/bd_validacao'
const camposGenericos = new CamposGenericos()
//const banco = new DB()


class EntradaNotas {

    preencherDadosIniciais_padrao() {
        cy.visit((Cypress.env('baseUrl')) + '/server/erp/estoque/entradas/criar')
        camposGenericos.campoPesquisaAberto(entradaElements.DADOSINICIAIS.FORNECEDOR, 'EMPRESA NFE')
        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.ENTIDADE, '7 - TESTE AUTOMATIZADO - LUCRO REAL')
        camposGenericos.preencherInput(entradaElements.DADOSINICIAIS.NUMERONOTA, '1010')
        camposGenericos.preencherInput(entradaElements.DADOSINICIAIS.SERIE, '1')
        camposGenericos.campoPesquisa(entradaElements.DADOSINICIAIS.OPERACAO, 'compra p/ comercializa%o', 'Compra p/ comercialização')
    }

    preencherDadosIniciais_tiposNota(tipoNota) {
        this.preencherDadosIniciais_padrao()
        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.TIPONOTA, tipoNota)
        this.clicarSalvar()
    }

    preencherDadosIniciais_comChave() {
        this.preencherDadosIniciais_padrao()
        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.TIPONOTA, 55)
        camposGenericos.preencherInput(entradaElements.DADOSINICIAIS.CHAVE, '42181275552133001303550040000005621722790267')
        this.clicarSalvar()
    }

    preencherDadosIniciais_xml(nomeXML, conciliaNota, conciliaSistema) {
        cy.visit((Cypress.env('baseUrl')) + '/server/erp/estoque/entradas/criar')
        cy.get(entradaElements.DADOSINICIAIS.BTN_IMPORTAR_XML).click()

        // cy.get(entradaElements.IMPORTAR_XML.IMPORTAR_ARQUIVO).invoke('removeAttr', 'readonly')
        // cy.get(entradaElements.IMPORTAR_XML.ARQUIVO_FILE).invoke('removeAttr', 'readonly')

        cy.fixture(nomeXML).then(importfile => { //using default encoding, but I've tried all supported 
            cy.log(importfile) // this correctly logs the file's content for most encoding values 
            cy.get(entradaElements.IMPORTAR_XML.ARQUIVO_FILE).upload({ fileContent: importfile, fileName: nomeXML, mimeType: 'text/xml', encoding: 'utf-8' })
        })

        this.conciliarXML(conciliaNota, conciliaSistema)
        cy.get(entradaElements.IMPORTAR_XML.BTN_IMPORTAR).click()

        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.ENTIDADE, 'TESTE AUTOMATIZADO - LUCRO REAL')
        camposGenericos.campoPesquisa(entradaElements.DADOSINICIAIS.OPERACAO, 'compra p/ comercializa%o', 'Compra p/ comercialização')
        this.clicarSalvar()
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

    incluirProdutos(lista_produtos) {
        lista_produtos.forEach(lista_produtos => {
            cy.get(entradaElements.PRODUTOS.PRODUTO)
                .click()
                .type(lista_produtos)

            camposGenericos.preencherInput(entradaElements.PRODUTOS.QUANTIDADE, '1')
            cy.get(entradaElements.PRODUTOS.BTN_ADICIONAR).click()
            //  i = 0
            //  num = consulta_parametro_custo
            //  begin
            //       zeros = zeros.to_s + "0"
            //       i +=1
            //  end while i < num

            // setarQuantidade(p["quantidade"]+'_'+zeros)
        })
        entradaNotas.clicarSalvar()
    }

    clicarSalvar(){
        cy.get(entradaElements.BTN_SALVAR).should('not.have.attr', 'disabled',{ timeout: 10000 }).click()
       
        cy.get('.noty_type__error',{ timeout: 3000 }).should('not.exist')
       // cy.get('body').find('.noty_type__error').should('not.exists')
    }

    validarMensagem(mensagem){
        cy.get(entradaElements.MENSAGEM,{timeout: 100000})
            .should('contain', mensagem)
    }

    excluirUtimaEntrada(numeroNota) {
        cy.visit((Cypress.env('baseUrl')) + '/server/erp/estoque/entradas')
     //   cy.request('DELETE', (Cypress.env('baseUrl')+'/server/api/estoque/entrada/excluir?idEntrada='+banco.consulta_ultimaEntrada()))

        cy.get('table tr').contains(numeroNota).click()
        cy.get(entradaElements.PESQUISA.BTN_EXCLUIR).click()
        cy.get(entradaElements.PESQUISA.BTN_CONFIRMAR).click()
        cy.get(entradaElements.MENSAGEM, { timeout: 100000 })
            .should('contain', 'A entrada 1010/1 foi efetuada com sucesso')
        // https://qa.varejonline.com.br:7443/server/api/estoque/entrada/excluir?idEntrada=10942
        // DELETE
    }    
}


export default EntradaNotas;