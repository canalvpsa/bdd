// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This is will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

import loc from './elements/Elements'

Cypress.Commands.add('login', () => {
    // cy.visit('http://192.168.25.96:9090/pdv')
    // cy.get(loc.LOGIN.USER).type('vendedor')
    // cy.get(loc.LOGIN.PASSWORD).type('varejo')
    // cy.get(loc.LOGIN.BTN_ENTRAR).click()
    // cy.get('.usuario > span').should('contain', 'Olá')

    cy.server()           // enable response stubbing
cy.route({
        method: 'POST',
        url: 'http://192.168.25.96:9090/pdv', // baseUrl is prepended to url
        form: true, // indicates the body should be form urlencoded and sets Content-Type: application/x-www-form-urlencoded headers
        body: {
            login: 'vendedor',
            senha: 'varejo'
        }
      })
})