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

Cypress.Commands.add("login", (usuario, senha) => {
  cy.visit('https://192.168.25.51/pdv/')
  cy.get(loc.LOGIN.USER).type(usuario)
  cy.get(loc.LOGIN.PASSWORD).type(senha)
  cy.get(loc.LOGIN.BOTAO).should('not.have.attr', 'disabled', { timeout: 1000000 }).click()
  cy.get('mat-toolbar span', { timeout: 10000 }).should('contain', 'Olá')
});

Cypress.Commands.add("loginfalha", (usuario, senha) => {
  cy.visit('https://192.168.25.51/pdv/')
  cy.get(loc.LOGIN.USER).type(usuario)
  cy.get(loc.LOGIN.PASSWORD).type(senha)
  cy.get(loc.LOGIN.BOTAO).should('not.have.attr', 'disabled', { timeout: 1000000 }).click()
  
  cy.get('.noty_body').should('contain', 'Usuário ou senha incorretos')
});

let LOCAL_STORAGE_MEMORY = {};

Cypress.Commands.add("saveLocalStorage", () => {
  Object.keys(localStorage).forEach(key => {
    LOCAL_STORAGE_MEMORY[key] = localStorage[key];
  });
});

Cypress.Commands.add("restoreLocalStorage", () => {
  Object.keys(LOCAL_STORAGE_MEMORY).forEach(key => {
    localStorage.setItem(key, LOCAL_STORAGE_MEMORY[key]);
  });
});