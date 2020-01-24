/// <reference types="Cypress" />


import entradaElements from '../elements/Entrada_notasElements'
import CamposGenericos from '../pageobjects/GenericPage'
const camposGenericos = new CamposGenericos()


class EntradaNotas {

    preencherDadosIniciais_tiposNota(tipoNota) {
        cy.visit('/server/erp/estoque/entradas/criar')
        camposGenericos.campoPesquisaAberto(entradaElements.DADOSINICIAIS.FORNECEDOR, 'GUARAMIRIM - LOJA COLABORADOR')
        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.ENTIDADE, '1 - GUARAMIRIM - LOJA COLABORADOR - REAL')
        camposGenericos.preencherInput(entradaElements.DADOSINICIAIS.NUMERONOTA, '1010')
        camposGenericos.preencherInput(entradaElements.DADOSINICIAIS.SERIE, '1')
        camposGenericos.campoPesquisa(entradaElements.DADOSINICIAIS.OPERACAO, 'compra p/ comercializa%o', 'Compra p/ comercialização')
        camposGenericos.campoListagem(entradaElements.DADOSINICIAIS.TIPONOTA, tipoNota)
        cy.get(entradaElements.BTN_SALVAR).click()
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
    }
}


export default EntradaNotas;