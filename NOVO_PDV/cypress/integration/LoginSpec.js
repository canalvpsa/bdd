/// <reference types="Cypress" />

import InsercaoItemPage from '../support/pageobjects/InserirItensPage'

const inserirItem = new InsercaoItemPage()
import CheckoutPage from '../support/pageobjects/CheckoutPage'
const checkout = new CheckoutPage()

describe('Login no PDV', () => {

    before(() => {
        cy.clearCookies()
        cy.restoreLocalStorage();
    })

    afterEach(() => {
        cy.saveLocalStorage();
    })


    context('Login não permitido', () => {

        it(`Acesso com login de usuario que não é vendedor`, () => {
            cy.loginfalha('comprador', 'varejonline')
        })

        it(`Acesso com login de usuario vendedor inativo`, () => {
            cy.loginfalha('vendedorinativo', 'varejonline')
        })
    })


    context('Login permitido para diferentes perfis de usuários vendedores', () => {

        it(`Acesso com login de usuario vendedor que não está autorizado a vender`, () => {
            cy.login('naovendepdv', 'varejonline')
            inserirItem.insercaoRapidaNegada('2*7891343865330', 'O vendedor NAO VINCULA VENDA PDV não possui permissão para realizar vendas')
        })

        it(`Acesso com login de vendedor com perfil de vendedor`, () => {
            cy.login('realizado', 'varejonline')
            //Deve ser exibido somente botão para 'salvar atendimento'
            checkout.validaBotoesPreVenda()
        })

        it(`Acesso com login de vendedor com perfil de gerente`, () => {
            cy.login('gerente', 'varejonline')
            //Deve ser exibido botão para 'salvar atendimento' e 'pagamento'
            checkout.validaBotoesVenda()
        })

        it(`Acesso com login de vendedor com perfil de caixa`, () => {
            cy.login('pdv1', 'varejonline')
            //Deve ser exibido somente botão para 'salvar atendimento' e 'pagamento'
            checkout.validaBotoesVenda()
        })
    })
})