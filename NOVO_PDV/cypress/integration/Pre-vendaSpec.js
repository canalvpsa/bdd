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
        cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('2*0582')
        cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
        .should('contain', 'Desconto Progressivo')
        cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('0562.0044')
        cy.get(checkout.CHECKOUT.DESCRICAOPRODUTO)
        .should('contain', 'Camiseta Basica Thiago Martins Xxl Vermelho')
        cy.get(inserirItens.PRODUTO.ADICIONARITEM).type('CAMISETA BASICA THIAGO MARTINS XXL AMARELO')
        cy.wait(500)
        cy.get(inserirItens.BTN_SALVAR_ATENDIMENTO).click()
    })


    it(`Editando e finalizando um documento`, () => {
        cy.get(menu.SELECAO.MOVIMENTACOES).click()
        cy.get(':nth-child(1) > .card-body > .clickable > .fad').click()
        cy.get('[tooltip="Continuar atendimento"] > .acao-atendimento > .acao-legenda-completa').click()
        cy.get(':nth-child(1) > .w-25 > .botoesQuantidade > :nth-child(3) > .fal').click()
        cy.get(':nth-child(1) > .w-50 > span').click()
        cy.wait(500)
        cy.get('.select2-selection__arrow > b').click({ force: true })
        cy.get('.select2-search__field').type('Administrador Loccitan').click()
        cy.wait(500)
        //cy.get(':nth-child(1) > .w-50 > span').click() - Avaliar a situação de seleção do vendedor
        cy.get('.select2-search__field').type('e').click({force: true}).type('{enter}')
        cy.wait(500)
        cy.get('.row > :nth-child(2) > .form-control').type('{backspace}')
        cy.get('.row > :nth-child(2) > .form-control').type('2')
        cy.wait(500)
        //CLicar fora
        cy.get('.cdk-overlay-backdrop').click({force : true})
        cy.get(':nth-child(3) > .btn-primary').click({force : true})
        cy.get('.botoes-pagamento > :nth-child(1)').click({force : true})
        cy.get('.d-flex > :nth-child(1) > .foto > app-imagem > img').click({force : true})
        cy.wait(500)
        cy.get('#parcelas').type('{backspace}')
        cy.wait(500)
        cy.get('#parcelas').type('2')
        cy.wait(500)
        cy.get('#autorizacao').type('1222135')
        cy.wait(500)
        cy.get('#cdk-step-content-2-3 > .d-flex > .botao-grande').click({force : true})
        cy.get('.m-2')
    })


    it.only(`Pré venda para cliente diversos`, () => {
        cy.get('app-select-terceiro').contains('Diversos').click()
        cy.get('app-select-terceiro .mat-input-element').type('Carlos')
        cy.get(inserirItens.PRODUTO.FN_ADICIONAR_CARRINHO('1')).click()
        cy.get(inserirItens.BTN_PAGAMENTO).click()
        cy.get('.flex-column > :nth-child(1) > :nth-child(2)').click()
        cy.get(':nth-child(4) > .botao-grande').click()
        cy.get('.m-2').click()
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
     


})
