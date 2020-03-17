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

import loc from './elements/LoginElements'

Cypress.Commands.add('login', () => {
    cy.visit('https://pdv.varejonline.com.br/pdv/')
    cy.get(loc.LOGIN.USER).type('vendedor')
    cy.get(loc.LOGIN.PASSWORD).type('varejo')
    cy.get(loc.LOGIN.CONECTADO).click()
    cy.get(loc.LOGIN.BTN_ENTRAR).click()
    cy.get('.usuario > span').should('contain', 'Olá')
})