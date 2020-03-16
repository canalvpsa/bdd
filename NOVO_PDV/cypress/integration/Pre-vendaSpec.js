/// <reference types="Cypress" />

import inserirItens from '../support/elements/inserirItensElements'

describe('Pré-venda', () => {

    before(() => {
        cy.clearCookies()
        cy.login()
    })


    it(`Pré venda para cliente diversos`, () => {
        cy.get(inserirItens.PRODUTO.FN_ADICIONAR_CARRINHO('1')).click()
        cy.get(inserirItens.PRODUTO.MENSAGEM)
                .should('contain', 'Produto BLUSA MANGA CURTA REF XYZ P AZ sem classificação fiscal')
        cy.get(inserirItens.BTN_SALVAR_ATENDIMENTO).click()
    })


    it(`Pré venda para cliente diferente de diversos`, () => {
    })
        

    it(`Pré venda sem informar itens`, () => {
    })
     

    it(`Pré venda adicionando produto através da pesquisa`, () => {
    })
     
    it(`Pré venda adicionando produto através da inserção rápida`, () => {
    })
     
    it(`Pré venda adicionando quantidades para produto através da tela de itens inseridos`, () => {
    })
     
    it(`Pré venda adicionando quantidades para produto adicionando pela pesquisa de produto`, () => {
    })
     
    it(`Pré venda adicionando quantidades para produto adicionando pela inserção rápida`, () => {
    })
     
    it(`Pré venda diminuindo quantidade para produto`, () => {
    })
     
    it(`Pré venda removendo produto da listagem`, () => {
    })


    it(`Pré venda com desconto em reais no total`, () => {
    })
        

    it(`Pré venda com desconto em percentual no total`, () => {
    })

})
