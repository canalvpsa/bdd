/// <reference types="Cypress" />

import inserirItens from '../support/elements/inserirItensElements'
import checkout from '../support/elements/checkoutElements'
import cliente from '../support/elements/clienteElements'
import desconto from '../support/elements/descontoElements'
import menu from '../support/elements/menuElements'

describe('Pré-venda', () => {

    before(() => {
        cy.clearCookies()
        cy.login()
    })

    beforeEach(() => {
        Cypress.Cookies.preserveOnce('pdv-auth-token', 'pdv-usuarios', 'pdv-componente-vendivel')
    })


    it(`Inserção rápida`, () => {
        //cy.get(inserirItens.PRODUTO.FN_ADICIONAR_CARRINHO('1')).click()
        cy.get(inserirItens.PRODUTO.INSERCAORAPIDA).click()
        cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('2*0135')
        cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
        .should('contain', 'Desconto Progressivo')
        cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('0002.0001')
        cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
        .should('contain', 'Blusa Manga Curta Ref Xyz P Az')
        cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('Teste Transferência M Ro')
        cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
        .should('contain', '110,00')
        cy.get(inserirItens.BTN_SALVAR_ATENDIMENTO).click()
    })

    it(`Pré venda para cliente diversos`, () => {
        cy.get(inserirItens.PRODUTO.FN_ADICIONAR_CARRINHO('1')).click()
        cy.get(inserirItens.PRODUTO.MENSAGEM)
                .should('contain', 'Produto BLUSA MANGA CURTA REF XYZ P AZ sem classificação fiscal')
        cy.get(inserirItens.BTN_SALVAR_ATENDIMENTO).click()
    })

  //  it(`Pré venda para cliente válido`, () => {
  //      cy.get(inserirItens.PRODUTO.FN_ADICIONAR_CARRINHO('1')).click()
  //      cy.get(inserirItens.PRODUTO.MENSAGEM)
  //              .should('contain', 'Produto BLUSA MANGA CURTA REF XYZ P AZ sem classificação fiscal')
  //      cy.get(inserirItens.BTN_SALVAR_ATENDIMENTO).click()
  //  })

   it(`Pré venda para cliente diferente de diversos`, () => {
    cy.get(inserirItens.PRODUTO.INSERCAORAPIDA).click()
    cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('2*0135')
    cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
    .should('contain', 'Desconto Progressivo')
    cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('0002.0001')
    cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
    .should('contain', 'Blusa Manga Curta Ref Xyz P Az')
    cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('Teste Transferência M Ro')
    cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
    .should('contain', '110,00')
    cy.get(checkout.CHECKOUT.BTN_PAGAMENTO).click()
    })
        

    it(`Venda com vínculo de cliente`, () => {
        cy.get(cliente.CLIENTE.CLIENTE).contains('Diversos').click()
        cy.get(cliente.CLIENTE.INSERCAOCLIENTE).type('Carlos Alessandro Laurindo')
        cy.contains('095.605.299-19').click()
        cy.get(inserirItens.PRODUTO.INSERCAORAPIDA).click()
        cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('2*0002.0003')
        cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
        .should('contain', 'Blusa Manga Curta Ref Xyz')
        cy.wait(500)
        cy.get(inserirItens.BTN_SALVAR_ATENDIMENTO).click()
    })
     

  //  it(`Inserção de desconto`, () => {
  //      cy.get(inserirItens.PRODUTO.INSERCAORAPIDA).click()
  //      cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('2*Boneca T02.')
  //      cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
  //      .should('contain', 'Boneca T02.')
  //      //cy.get('.mb-3',).type('50,00')
  //      cy.get('.flex-direction-column')
  //      cy.get('.mb-3',)
  //      cy.get('.form-control').type('50,00')
  //  })
     
  it.only(`Relatório de movimentações`, () => {
    cy.get(menu.SELECAO.MOVIMENTACOES).click()
    cy.get(':nth-child(1) > .card-body > .clickable > .fad').click()
    cy.get('[tooltip="Continuar atendimento"] > .acao-atendimento > .acao-legenda-completa').click()
    cy.get(':nth-child(1) > .w-25 > .botoesQuantidade > :nth-child(3) > .fal').click()
    cy.get(':nth-child(1) > .w-50 > span').click()
    cy.get('.select2-selection__arrow > b').click()
    cy.get('.select2-search__field').type('GERENTE')
    //cy.get(':nth-child(1) > .w-50 > span').click()
    cy.get('.cdk-overlay-backdrop').click()
})

})
