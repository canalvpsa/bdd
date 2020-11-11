
/// <reference types="Cypress" />

import inserirItens from '../elements/InserirItensElements'
import checkout from '../elements/CheckoutElements'

class InsercaoItensPage{
    

    insercaoRapida(codigoItem){
        cy.get(inserirItens.OPCAO_INSERCAO_RAPIDA).should('not.exist').get(inserirItens.TOGGLE_PESQUISA).click()
        cy.get(inserirItens.PESQUISAITEM).type(codigoItem)
        cy.wait(2000)
        cy.get(inserirItens.TOGGLE_PESQUISA).click()
    }

    insercaoRapidaNegada(codigoItem, mensagem){
        cy.get(inserirItens.OPCAO_INSERCAO_RAPIDA).should('not.exist').get(inserirItens.TOGGLE_PESQUISA).click()
        cy.get(inserirItens.PESQUISAITEM).type(codigoItem)
        cy.get('.noty_body').should('contain', mensagem)
    }

    insercaoPesquisa(codigoItem){
        cy.get(inserirItens.PESQUISAITEM)
            .click()
            .type(codigoItem)
            cy.server()
        cy.route("/produto/mais-vendidos").as("listaprodutos")
        cy.wait('@listaprodutos')
       this.adicionarCarrinho().click()
    }

    adicionarCarrinho(){
        
        cy.get('.card > .card-footer > .clickable > .branco').click()
    }

    limparCampoPesquisa(){
        cy.get(inserirItens.PESQUISAITEM).clear()
    }

    getCampoPesquisaProduto(){
        return cy.get(inserirItens.PESQUISAITEM).should('have.length', 1)
    }


    insercaoPesquisaDetalheItem(codigoItem){
        cy.get(inserirItens.PESQUISAITEM).type(codigoItem)
    }

    getProdutoPesquisado(){
        return cy.get(inserirItens.PRODUTOPESQUISADO)
    }

    getProdutoImagem(){
        return cy.get(inserirItens.IMAGEMPRODUTO)
    }

    getPrecoAntigo(){
        return cy.get(inserirItens.PRODUTOPESQUISADO + inserirItens.PRECOANTIGO)
    }

    getPrecoVigente(){
        return cy.get(inserirItens.PRODUTOPESQUISADO + inserirItens.PRECOVIGENTE)
    }

    getBotaoAdicionar(){
    return cy.get(inserirItens.BTN_ADICIONAR)
    }
}

export default InsercaoItensPage;