/// <reference types="Cypress" />

import loginElements from '../elements/LoginElements'

const url = Cypress.config("baseUrl")

class LoginPage {
    // Acessa o site que será testado
    acessarSite() {
        cy.visit(url)
    }

    // Clica no botão que acessa a página de login do site
    clicarBotaoPaginaLogin() {
        cy.get(loginElements.botaoLogin()).click()
    }

    // Verifica se o botão tem o texto "Esqueceu sua senha?"
    visualizarBotaoRecuperarSenha() {
        cy.get(loginElements.botaoRecuperarSenha()).should('contain', 'Esqueceu sua senha?')
    }
}

class LoginERP {
    acessarERP() {
        cy.visit('https://erp.varejonline.com.br')
        cy.get(LoginElements.USER).type('devcanal')
        cy.get(LoginElements.PASSWORD).type('devcanal')
        cy.get(LoginElements.BASE).type('vo348')
        cy.get(LoginElements.BTN_LOGIN).click()
    }
}

export default LoginERP;