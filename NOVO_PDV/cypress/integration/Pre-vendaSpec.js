/// <reference types="Cypress" />

import checkoutElements from '../support/elements/checkoutElements'
import menu from '../support/elements/menuElements'

import MovimentacaoPage from '../support/pageobjects/MovimentacaoPage'
import CheckoutPage from '../support/pageobjects/CheckoutPage'
import InsercaoItemPage from '../support/pageobjects/InserirItensPage'

const movimentacao = new MovimentacaoPage()
const inserirItem = new InsercaoItemPage()
const checkout = new CheckoutPage()

describe('Pré-venda', () => {

    before(() => {
        cy.clearCookies()
        cy.login('realizado', 'varejonline')
        cy.get(menu.ATENDIMENTO).click({ force: true })
    })

    beforeEach(() => {
        cy.restoreLocalStorage();
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


    context('Tipos de inserção de item e validação de quantidade', () => {


        it(`Inserção rápida informando quantidade`, () => {
            inserirItem.insercaoRapida('2*'+codigo)
            checkout.getItemListagem().contains(codigo)
            validaQuantidadeValores('2', '199,82')
        })

        it(`Inserção pela pesquisa`, () => {
            inserirItem.getCampoPesquisaProduto().type(codigo)
            inserirItem.adicionarCarrinho()
            inserirItem.limparCampoPesquisa()
            checkout.getItemListagem().contains(codigo)
            validaQuantidadeValores('3', '299,73')
        })

        it(`Inserção pelo detalhe do item`, () => {
            inserirItem.getCampoPesquisaProduto().type(codigo)
            inserirItem.getProdutoImagem().click()
            inserirItem.getBotaoAdicionar().click()
            inserirItem.limparCampoPesquisa()
            validaQuantidadeValores('4', '399,64')
        })

        it(`Aumentar quantidade do item clicando em +`, () => {
            checkout.getAumentarQuantidadeItem().click()
            validaQuantidadeValores('5', '499,55')
        })

        it(`Diminuir quantidade do item clicando em - até excluir item`, () => {
            checkout.getDiminuirQuantidadeIem().click()
            validaQuantidadeValores('4', '399,64')
            cy.wait(1000)
            
            checkout.getDiminuirQuantidadeIem().click()
            validaQuantidadeValores('3', '299,73')
            cy.wait(1000)

            checkout.getDiminuirQuantidadeIem().click()
            validaQuantidadeValores('2', '199,82')
            cy.wait(1000)

            checkout.getDiminuirQuantidadeIem().click()
            validaQuantidadeValores('1', '99,91')
            cy.wait(1000)

            checkout.getDiminuirQuantidadeIem().click()
            checkout.validaListaVazia()
        })
    })


    context('Validação de preços para cliente vinculado a categoria de cliente', () => {
        const prod = require("../fixtures/produtos.json")
        const precoantigo = '52,98'


        it(`Validação de precedência de preços - Cliente com categoria`, () => {

            checkout.alterarCliente('ADEMIR MOREIRA')

            prod.forEach(prod => {
                inserirItem.getCampoPesquisaProduto().type(prod.codigo).type('{enter}')
                cy.wait(2000)

                //Valida preços exibidos na pesquisa
                inserirItem.getPrecoAntigo().should('contain', precoantigo);
                inserirItem.getPrecoVigente().should('contain', prod.comcategoria);
                inserirItem.adicionarCarrinho()
                inserirItem.limparCampoPesquisa()

                checkout.getItemListagem().contains(prod.codigo).parents('.d-flex').within(() => {
                    cy.get(checkout.getItemPrecoUnitario().contains(prod.comcategoria))
                    cy.get(checkout.getItemPrecoTotal().contains(prod.comcategoria))
                })
            })
            checkout.getSalvarAtendimento().click()
            cy.wait(2000)
        })

        it(`Validação de precedência de preços - Cliente sem categoria`, () => {

            prod.forEach(prod => {
                inserirItem.getCampoPesquisaProduto().type(prod.codigo)
                cy.wait(2000)

                //Valida preços exibidos na pesquisa
                inserirItem.getPrecoAntigo().should('contain', precoantigo);
                inserirItem.getPrecoVigente().should('contain', prod.semcategoria);
                inserirItem.adicionarCarrinho()
                inserirItem.getCampoPesquisaProduto().clear()

                checkout.getItemListagem().contains(prod.codigo).parents('.d-flex').within(() => {
                    cy.get(checkout.getItemPrecoUnitario().contains(prod.semcategoria))
                    cy.get(checkout.getItemPrecoTotal().contains(prod.semcategoria))
                })
            })
            checkout.getSalvarAtendimento().click()
            cy.wait(2000)
        })


        it(`Validação de preço - Tabela padrão com categoria`, () => {
            const codigo = '75338119'
            const preco = '52,98'

            checkout.alterarCliente('ADEMIR MOREIRA')

            inserirItem.getCampoPesquisaProduto().type(codigo)
            cy.wait(2000)

            //Valida preços exibidos na pesquisa
            inserirItem.getPrecoAntigo().should('not.exist');
            inserirItem.getPrecoVigente().should('contain', preco);
            inserirItem.adicionarCarrinho()
            inserirItem.limparCampoPesquisa()

            checkout.getItemListagem().contains(codigo).parents('.d-flex').within(() => {
                cy.get(checkout.getItemPrecoUnitario().contains(preco))
                cy.get(checkout.getItemPrecoTotal().contains(preco))
            })
            checkout.getSalvarAtendimento().click()
            cy.wait(2000)
        })

        it(`Validação de preço - Tabela padrão sem categoria`, () => {
            const codigo = '75338119'
            const preco = '52,98'

            inserirItem.getCampoPesquisaProduto().type(codigo)
            cy.wait(2000)

            //Valida preços exibidos na pesquisa
            inserirItem.getPrecoAntigo().should('not.exist');
            inserirItem.getPrecoVigente().should('contain', preco);
            inserirItem.adicionarCarrinho()
            inserirItem.limparCampoPesquisa()

            checkout.getItemListagem().contains(codigo).parents('.d-flex').within(() => {
                cy.get(checkout.getItemPrecoUnitario().contains(preco))
                cy.get(checkout.getItemPrecoTotal().contains(preco))
            })
            checkout.getSalvarAtendimento().click()
            cy.wait(2000)
        })
    })



    context('Continuar atendimento com usuário caixa iniciadas por usuário vendedor', () => {

        beforeEach(() => {
            inserirItem.insercaoRapida('2*7891343865330', 'Blusa Manga Curta Ref. Xyz Azul M')
            cy.wait(3000)
        //    cy.xpath(checkoutElements.FN_BTN_SALVAR).click()
            cy.wait(2000)
        })

        it(`Cancelamento de atendimento com status "Em andamento"`, () => {
            movimentacao.cancelarUltimoAtendimento("Desistencia do cliente{ENTER}", "Teste automatizado - Cancelamento")
            movimentacao.validarMensagem('encerrado com sucesso!')
        })


        it(`Continuar atendimento com desconto unitário`, () => {
            movimentacao.cancelarUltimoAtendimento("Desistencia do cliente{ENTER}", "Teste automatizado - Cancelamento")
            movimentacao.validarMensagem('encerrado com sucesso!')
        })

        it('Continuar atendimento com troca', () => {
        })

        it('Continuar atendimento com ação promocional', () => {
        })

        it('Continuar atendimento e realizar pagamento em dinheiro', () => {
        })

        it('Continuar atendimento e realizar pagamento em cartão', () => {
        })

        it('Continuar atendimento e realizar pagamento em crediário para cliente Diversos', () => {
        })

        it('Continuar atendimento e realizar pagamento em crediário para cliente sem endereço', () => {
        })

        it('Continuar atendimento e realizar pagamento em crediário para cliente com endereço', () => {
        })

        it('Continuar atendimento e realizar pagamento com adiantamento', () => {
        })

    })


})
