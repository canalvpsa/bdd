/// <reference types="Cypress" />

// import entradaElements from '../../support/elements/Entrada_notasElements'
// import EntradaNotas from '../../support/pageobjects/EntradaNotasPage'
// const entradaNotas = new EntradaNotas();

describe('Pré-venda', () => {

    before(() => {
        cy.clearCookies()
        cy.login()
        
    })

    // beforeEach(() => {
    //     Cypress.Cookies.preserveOnce('CSRF-TOKEN', 'baseOuCNPJ', '_single-sign-on-server_session', 'service', 'tgt')
    // })


    it(`Pré venda para cliente diversos`, () => {
        cy.visit('http://192.168.25.96:9090/pdv')
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
