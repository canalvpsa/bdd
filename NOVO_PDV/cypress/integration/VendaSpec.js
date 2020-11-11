/// <reference types="Cypress" />

import inserirItens from '../support/elements/inserirItensElements'

import pagamentos from '../support/elements/pagamentosElements'
import checkoutElements from '../support/elements/checkoutElements'
import menu from '../support/elements/menuElements'

import MovimentacaoPage from '../support/pageobjects/MovimentacaoPage'
import CheckoutPage from '../support/pageobjects/CheckoutPage'
import InsercaoItemPage from '../support/pageobjects/InserirItensPage'

const movimentacao = new MovimentacaoPage()
const inserirItem = new InsercaoItemPage()
const checkout = new CheckoutPage()

describe('Venda', () => {

    before(() => {
        cy.clearCookies()
        cy.login('admin', 'varejonline')
        cy.get(menu.ATENDIMENTO).click()
    })

    beforeEach(() => {
        cy.restoreLocalStorage();
    })

    afterEach(() => {
        cy.saveLocalStorage();
});
})


//     it(`Venda aumentando a quantidade do item`, () => {
//     //     cy.get(menu.ATENDIMENTO).click()
//     //    checkout.alterarCliente('Ana maria')
//     //    inserirItem.insercaoRapida('7891343865330', 'Blusa Manga Curta Ref. Xyz Azul M')
//     //    checkout.aumentarQuantidadeItem('Blusa Manga Curta Ref. Xyz Azul M')
//     //    checkout.validaValorTotalItem('169,00')
//     //    checkout.validaValorTotalVenda('169,00')
//     })


//     // it(`Venda com desconto unitário`, () => {
//     //     cy.get(menu.ATENDIMENTO).click()
//     // })

//     // it(`Venda com desconto total`, () => {
//     //     cy.get(menu.ATENDIMENTO).click()
//     // })

//     // it(`Venda com desconto unitário e total`, () => {
//     //     cy.get(menu.ATENDIMENTO).click()
 
//     // })

//     // it(`Venda com pagamento em dinheiro`, () => {
//     //     cy.get(menu.ATENDIMENTO).click()
 
//     // })


//     // it(`Venda com pagamento em cheque`, () => {
//     //     cy.get(menu.ATENDIMENTO).click()
 
//     // })


//     it(`Alterando e finalizando uma venda com status em atendimento`, () => {
//         //primeiro faz a pre venda
//         // inserirItem.insercaoRapida('2*0582', 'Produto Willian G')
//         // cy.get(checkoutElements.FN_BTN_SALVAR).click()
//         // checkout.validaListaVazia()

//         // cy.get(menu.MOVIMENTACOES).click()
//     //     cy.xpath(movimentacao.DETALHAR, {timeout:10000}).click()
//     //    // cy.get(
//     //     cy.xpath(movimentacao.DETALHAMENTO.FN_CONTINUAR).click()
//     //     //adiciona desconto no item
//     //     cy.get(':nth-child(1) > .w-25 > .botoesQuantidade > :nth-child(3) > .fal').click()
//     //     cy.get(':nth-child(1) > .w-50 > span',{timeout:500}).click()
//     //     cy.get(infoitem.DETALHE.VENDEDOR).click({ force: true })
//     //     //muda o vendedor
//     //     cy.get(infoitem.DETALHE.NOMEVEND).type('Administrador Loccitan').click()
//     //     cy.wait(500)
//     //     cy.get(infoitem.DETALHE.NOMEVEND).type('e').click({force: true}).type('{enter}')
//     //     cy.wait(500)
//     //     cy.get(infoitem.DETALHE.UNIDADE,{timeout:500}).type('{backspace}')
//     //     cy.get(infoitem.DETALHE.UNIDADE).type('2')
//     //     cy.wait(500)
//     //     cy.get(infoitem.DETALHE.SAIR, {timeout:500}).click({force : true})
//     //     cy.xpath(inserirItens.FN_BTN_PAGAMENTOS).click({force : true})
//     //     cy.get(pagamentos.SELECAO.CARTAO).click({force : true})
//     //     cy.get(pagamentos.SELECAO.OPERADORA.CIELO, {timeout:500}).click({force : true})
//     //     cy.get('#parcelas').type('{backspace}')
//     //     cy.wait(500)
//     //     cy.get('#parcelas').type('2')
//     //     cy.wait(500)
//     //     cy.get('#autorizacao').type('1222135')
//     //     cy.wait(500)
//     //     cy.get('#cdk-step-content-2-3 > .d-flex > .botao-grande').click({force : true})
//     //     cy.get('.m-2')
//     })

//     context('Continuar atendimento com pagamento vinculado', () => {


//     it('Continuar atendimento pago no dinheiro', () => {
//     })
// })
