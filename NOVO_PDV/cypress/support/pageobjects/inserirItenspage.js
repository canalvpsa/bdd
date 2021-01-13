
/// <reference types="Cypress" />

import InserirItens from '../elements/InserirItensElements'
import checkout from '../elements/CheckoutElements'

class InsercaoItensPage{
    

    insercaoRapida(codigoItem){
        cy.get(InserirItens.OPCAO_INSERCAO_RAPIDA).click()
        cy.get(InserirItens.PESQUISAITEM).type(codigoItem)
        cy.wait(2000)
        cy.get(InserirItens.OPCAO_PESQUISA).click()
    }

    insercaoRapidaNegada(codigoItem, mensagem){
        cy.get(InserirItens.OPCAO_INSERCAO_RAPIDA).click()
        cy.get(InserirItens.PESQUISAITEM).type(codigoItem)
        cy.get('.noty_body').should('contain', mensagem)
    }

    insercaoPesquisa(codigoItem){
        cy.get(InserirItens.PESQUISAITEM)
            .click()
            .type(codigoItem)
            cy.server()
        cy.route("/produto/mais-vendidos").as("listaprodutos")
        cy.wait('@listaprodutos')
       this.adicionarCarrinho().click()
    }

    adicionarCarrinho(){
        cy.get('.card > .card-footer > .clickable > .branco').click().should('have.length', 1)
    }

    limparCampoPesquisa(){
        cy.get(InserirItens.PESQUISAITEM).clear()
    }

    getCampoPesquisaProduto(){
        return cy.get(InserirItens.PESQUISAITEM).should('have.length', 1)
    }


    insercaoPesquisaDetalheItem(codigoItem){
        cy.get(InserirItens.PESQUISAITEM).type(codigoItem)
    }

    getProdutoPesquisado(){
        return cy.get(InserirItens.PRODUTOPESQUISADO)
    }

    getProdutoImagem(){
        return cy.get(InserirItens.IMAGEMPRODUTO)
    }

    getPrecoAntigo(){
        return cy.get(InserirItens.PRODUTOPESQUISADO + InserirItens.PRECOANTIGO)
    }

    getPrecoVigente(){
        return cy.get(InserirItens.PRODUTOPESQUISADO + InserirItens.PRECOVIGENTE)
    }

    getBotaoAdicionar(){
    return cy.get(InserirItens.BTN_ADICIONAR)
    }
}

export default InsercaoItensPage;