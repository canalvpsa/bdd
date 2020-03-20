/// <reference types="Cypress" />

import inserirItens from '../support/elements/inserirItensElements'
import checkout from '../support/elements/checkoutElements'
import cliente from '../support/elements/clienteElements'
import desconto from '../support/elements/descontoElements'
import menu from '../support/elements/menuElements'

describe('Cliente', () => {

    before(() => {
        cy.clearCookies()
        cy.login()
    })

    beforeEach(() => {
        Cypress.Cookies.preserveOnce('pdv-auth-token', 'pdv-usuarios', 'pdv-componente-vendivel')
    })


    it(`Pesquisa de cliente`, () => {
        cy.get(menu.SELECAO.RELCLIENTES).click()
        cy.get('.item').click()
        cy.get('.form-control')
    })


})