/// <reference types="Cypress" />

import loginElements from '../../support/elements/LoginElements'
import entradaElements from '../../support/elements/Entrada_notas.elements'

'../../support/elements/Entrada_notas.elements'

describe('ERP - Entrada de nota fiscal no ERP e validação do status da escrituração', () => {

    before(() => {
        cy.login()
        })
    
        it('Entrada de notas manual validando status da escrituração para modelo 1A, 1, 55 e 4', () => {
            cy.visit('/server/erp/estoque/entradas/criar')
            cy.get(entradaElements.DADOSINICIAIS.FORNECEDOR).type('GUARAMIRIM - LOJA COLABORADOR')
            cy.get('.select2-results__option').click()
            cy.get(entradaElements.DADOSINICIAIS.ENTIDADE).type('GUARAMIRIM - LOJA COLABORADOR')
        })

    })
