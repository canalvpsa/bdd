
/// <reference types="Cypress" />

import menu from '../elements/menuElements'
import movimentacao from '../elements/movimentacaoElements'
import pagamento from '../elements/PagamentoElements'

class PagamentoPage{


    clicarPagamento(){
        cy.xpath(checkout.FN_BTN_PAGAMENTOS).should('exist')
    }

    concluirAtendimento(){

         
    }
    pagarDinheiro(valor){
        clicarPagamento()
        cy.type(valor)
        cy.xpath(FN_BTN_ADICIONAR).click()

    }

}