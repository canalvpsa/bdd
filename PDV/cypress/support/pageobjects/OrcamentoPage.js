/// <reference types="Cypress" />

import orcamento from '../elements/orcamentoElements'


class OrcamentoPage{

    validaBotoesOrcamento(){
        cy.xpath(checkout.FN_BTN_SALVAR).should('exist')
    }

    getSalvarOrcamento(){
        return cy.xpath(orcamento.FN_BTN_SALVAR)
    }
}

export default CheckoutPage;