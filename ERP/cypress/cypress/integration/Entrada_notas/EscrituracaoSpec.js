/// <reference types="Cypress" />

import entradaElements from '../../support/elements/Entrada_notasElements'
import EntradaNotas from '../../support/pageobjects/EntradaNotasPage'
const entradaNotas = new EntradaNotas();

describe('ERP - Entrada de nota fiscal no ERP e validação do status da escrituração', () => {

    before(() => {
        cy.clearCookies()
        cy.login()
    })

    beforeEach(() => {
        Cypress.Cookies.preserveOnce('CSRF-TOKEN', 'baseOuCNPJ', '_single-sign-on-server_session', 'service', 'tgt')
    })
   
    const modeloNota = ['1A', '1', '55', '4']
    const escrituracao = 'ESCRITURADA_EXTERNO'
    const produtos = ['g2.001', 'g2.002']

    
    modeloNota.forEach(modeloNota => {
        it(`Entrada de notas manual validando status da escrituração para modelo ${modeloNota}`, () => {
            if (modeloNota == '55')
                escrituracao = 'NAO_ESCRITURADA'

            entradaNotas.preencherDadosIniciais_tiposNota(modeloNota)
            cy.get(entradaElements.BTN_SALVAR).click()
            entradaNotas.incluirProdutos(produtos)
            cy.get(entradaElements.BTN_SALVAR).click()
            cy.get(entradaElements.BTN_SALVAR).click()
        })
    })


    it(`Entrada de notas manual validando status da escrituração para modelo 55 e chave de acesso`, () => {

    })

    it(`Entrada de notas com XML validando status da escrituração`, () => {

    })

    //     enário: Entrada de notas manual validando status da escrituração para modelo 55 e chave de acesso
    //     Quando o usuário informar na entrada o tipo de nota "55" com número do documento "562" e série "4" informando chave de acesso
    //     E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
    //     E finaliza a entrada
    //     Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    //     E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


    // @entrada_XML @modelo_nota
    // Cenário: Entrada de notas com XML validando status da escrituração
    //     Quando o usuário preenche os dados iniciais informando um XML
    //     E que confirma a conciliação, confirma os dados adicionais, confirma os produtos e confirma a visão geral
    //     E finaliza a entrada
    //     Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    //     E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"
})
