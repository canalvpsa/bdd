/// <reference types="Cypress" />

import checkoutElements from '../support/elements/checkoutElements'
import menu from '../support/elements/menuElements'

import MovimentacaoPage from '../support/pageobjects/MovimentacaoPage'
import CheckoutPage from '../support/pageobjects/CheckoutPage'
import InsercaoItemPage from '../support/pageobjects/InserirItenspage'

const movimentacao = new MovimentacaoPage()
const inserirItem = new InsercaoItemPage()
const checkout = new CheckoutPage()
const orcamento = new OrcamentoPage()

describe('Orçamentos', () => {

    before(() => {
        cy.clearCookies()
        cy.login('realizado', 'varejonline')
        cy.get(menu.ATENDIMENTO).click({ force: true })
    })

    beforeEach(() => {
        cy.restoreLocalStorage();
        cy.get(menu.ATENDIMENTO).click({ force: true })
        cy.reload()
    })

    afterEach(() => {
        cy.saveLocalStorage();
    })

    const codigo = '7891343865330'
    const valorUnitario = '99,91'

    function validaQuantidadeValores(quantidade, valorTotal) {
        checkout.getItemListagem().should('contain', codigo)
        checkout.getItemQuantidade().should('contain', quantidade)
        checkout.getItemPrecoUnitario().should('contain', valorUnitario)
        checkout.getItemPrecoTotal().should('contain', valorTotal)
    }

    function validaPrecoContinuarAtendimento(preco) {
        cy.get(menu.MOVIMENTACOES).click({ force: true })
        movimentacao.getUltimoAtendimento().click()
        movimentacao.getContinuarAtendimento().click()
        checkout.getValorTotalVenda().should('contain', preco)
        checkout.getSalvarAtendimento().click()
    }


    context('Não permite salvar orçamento', () => {

        it(`Cliente Diversos`, () => {
            inserirItem.insercaoRapida('2*' + codigo)
            checkout.getItemListagem().contains(codigo)
            orcamento.getSalvarOrcamento().click()
        })

        it(`Cliente sem endereço`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')
            inserirItem.insercaoRapida(codigo)
            checkout.getItemListagem().contains(codigo)
            checkout.getSalvarAtendimento().click()
        })
    })

    context('Valida vendedor', () => {
        it(`Vendedor orçamento igual ao vendedor logado`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')


        })

        it(`Vendedor orçamento diferente do vendedor logado`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')


        })

        it(`Valida alteração de vendedor após inserir item`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')
        })


  })
    context('Orçamentos com desconto', () => {
        
        it(`Desconto no item`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')


        })



        it(`Desconto no total`, () => {

        })


        it(`Desconto no item e no total`, () => {

        })
    })


    context('Validação de preços tabela de preço configurada com desconto', () => {
        const prod = require("../fixtures/produtos.json")
        const precoantigo = '52,98'


        it(`Validação de precedência de preços - Cliente com categoria`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')

            prod.forEach(prod => {

                inserirItem.getPrecoAntigo().should('contain', precoantigo);
                inserirItemVenda(prod.codigo, prod.comcategoria)
                validaPrecosItensInseridosSemDesconto(prod.codigo, prod.comcategoria)
            })
            validaTotalContinuaAtendimento('232,50')
        })
    })

    context('Reserva de estoque', () => {
        const prod = require("../fixtures/produtos.json")
        const precoantigo = '52,98'


        it(`Produtos com saldo`, () => {
           
        })

        it(`Produtos sem saldo`, () => {
           
        })
    })
})
