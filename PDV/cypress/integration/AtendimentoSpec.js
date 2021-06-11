/// <reference types="Cypress" />

import checkoutElements from '../support/elements/checkoutElements'
import menu from '../support/elements/menuElements'
import usuario from '../support/elements/UsuarioElements'

import MovimentacaoPage from '../support/pageobjects/MovimentacaoPage'
import CheckoutPage from '../support/pageobjects/CheckoutPage'
import InsercaoItemPage from '../support/pageobjects/InserirItenspage'

const movimentacao = new MovimentacaoPage()
const inserirItem = new InsercaoItemPage()
const checkout = new CheckoutPage()

describe('Atendimentos', () => {

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
        cy.log('after each')
        cy.saveLocalStorage();
    })

    const codigo = '7891343865330'
    const valorUnitario = '99,90'

    function validaQuantidadeValores(quantidade, valorTotal) {
        cy.wait(2000)
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
        checkout.salvarAtendimento()
    }

    function loginUsuarioCaixa(){
        cy.get(menu.USUARIO).click()
        cy.xpath(usuario.FN_BTN_SAIR).click()
        cy.login('realizado', 'varejonline')
    }

    context('Validação de tipos de inserção de item', () => {

        it(`Inserção rápida com quantidade`, () => {
            inserirItem.insercaoRapida('2*' + codigo)
            checkout.getItemListagem().contains(codigo)
            validaQuantidadeValores('2', '199,80')
            checkout.getValorTotalVenda().should('contain', '199,80')
            checkout.cancelarAtendimento()
        })

        it(`Inserção pela pesquisa`, () => {
            var i
            for (i = 0; i < 3; i++) {
                inserirItem.getCampoPesquisaProduto().type(codigo)
                cy.wait(2000)
                inserirItem.adicionarCarrinho()
                inserirItem.limparCampoPesquisa()
                checkout.getItemListagem().contains(codigo)
            }
            validaQuantidadeValores('3', '299,70')
            checkout.getValorTotalVenda().should('contain', '299,70')
            checkout.cancelarAtendimento()
        })

        it(`Inserção detalhando item`, () => {
            inserirItem.getCampoPesquisaProduto().type(codigo)
            cy.wait(2000)
            inserirItem.getProdutoImagem().click()
            inserirItem.getBotaoAdicionar().click()
            inserirItem.limparCampoPesquisa()
            validaQuantidadeValores('1', '99,90')
            checkout.getValorTotalVenda().should('contain', '99,90')
            checkout.cancelarAtendimento()
        })

        it(`+ e - na quantidade`, () => {

            inserirItem.insercaoRapida('4*' + codigo)
            checkout.getAumentarQuantidadeItem().click()

            validaQuantidadeValores('5', '499,50')
            checkout.getValorTotalVenda().should('contain', '499,50')

            checkout.getDiminuirQuantidadeIem().click()
            validaQuantidadeValores('4', '399,60')
            cy.wait(1000)
            checkout.getValorTotalVenda().should('contain', '399,60')

            checkout.getDiminuirQuantidadeIem().click()
            validaQuantidadeValores('3', '299,70')
            cy.wait(1000)
            checkout.getValorTotalVenda().should('contain', '299,70')

            checkout.getDiminuirQuantidadeIem().click()
            validaQuantidadeValores('2', '199,80')
            cy.wait(1000)
            checkout.getValorTotalVenda().should('contain', '199,80')

            checkout.getDiminuirQuantidadeIem().click()
            validaQuantidadeValores('1', '99,90')
            cy.wait(1000)
            checkout.getValorTotalVenda().should('contain', '99,90')

            checkout.getDiminuirQuantidadeIem().click()
            checkout.validaListaVazia()
        })

        it(`Aumentar quantidade pela edição`, () => {
            loginUsuarioCaixa()
            inserirItemVenda('75338119', '52,98')
            inserirItemVenda('75338121', '47,00')
            inserirItemVenda('75338011', '47,00')
            checkout.alteraQuantidadeDetalhe('75338119', '20000')
            validaTotalContinuaAtendimento('199,96')
        })
    })

    function validaPrecoAntigo(produto, precoAntigo) {
        inserirItem.getCampoPesquisaProduto().type(produto)
        inserirItem.getPrecoAntigo().should('contain', precoAntigo);
        inserirItem.limparCampoPesquisa()
    }

    function validaSemPrecoAntigo(produto) {
        inserirItem.getCampoPesquisaProduto().type(produto)
        inserirItem.getPrecoAntigo().should('not.exist');
        inserirItem.limparCampoPesquisa()
    }

    function inserirItemVenda(produto, precoVigente) {
        inserirItem.getCampoPesquisaProduto().type(produto).type('{enter}')
        cy.wait(2000)

        //Valida preço vigente na pesquisa
        inserirItem.getPrecoVigente().should('contain', precoVigente);
        inserirItem.adicionarCarrinho()
        inserirItem.limparCampoPesquisa()
    }


    function validaPrecosItensInseridosSemDesconto(produto, precoCategoria) {
        checkout.getItemListagem().contains(produto).parents('.d-flex').within(() => {
            cy.get(checkout.getItemPrecoUnitario().contains(precoCategoria))
            cy.get(checkout.getItemPrecoTotal().contains(precoCategoria))
            cy.get(checkout.getItemDescontoUnitario().should('not.exist'));
        })
    }

    function validaPrecosItensInseridosComDesconto(produto, desconto, precoUnitario, precoTotal) {
        cy.wait(2000)
        checkout.getItemListagem().contains(produto).parents('.d-flex').within(() => {
            cy.get(checkout.getItemPrecoUnitario().contains(precoUnitario))
            cy.get(checkout.getItemPrecoTotal().contains(precoTotal))
            cy.get(checkout.getItemDescontoUnitario().contains(desconto));
        })
    }



    function validaTotalContinuaAtendimento(valorTotal) {
        checkout.getValorTotalVenda().should('contain', valorTotal)
        cy.wait(5000)
        checkout.salvarAtendimento()

        // Valida se o valor continua correto ao 'Continuar atendimento'
        validaPrecoContinuarAtendimento(valorTotal)
    }



    context('Validação de preços sem desconto', () => {
        const prod = require("../fixtures/produtos.json")
        const precoantigo = '52,98'


        it(`Validação de precedência de preços - Cliente com categoria`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')

            prod.forEach(prod => {
                validaPrecoAntigo(prod.codigo, precoantigo)
                inserirItemVenda(prod.codigo, prod.comcategoria)
                validaPrecosItensInseridosSemDesconto(prod.codigo, prod.comcategoria)
            })
            validaTotalContinuaAtendimento('232,50')
        })

        it(`Validação de precedência de preços - Cliente sem categoria`, () => {

            prod.forEach(prod => {
                inserirItemVenda(prod.codigo, prod.semcategoria)
                validaPrecosItensInseridosSemDesconto(prod.codigo, prod.semcategoria)
            })
            validaTotalContinuaAtendimento('223,35')
        })


        it(`Validação de preço - Tabela padrão - cliente com categoria`, () => {
            const codigo = '75338119'
            const preco = '52,98'

            checkout.alterarCliente('ADEMIR MOREIRA')
            validaSemPrecoAntigo(codigo)
            inserirItemVenda(codigo, preco)
            validaPrecosItensInseridosSemDesconto(codigo, preco)
            validaTotalContinuaAtendimento('52,98')
        })


        it(`Validação de preço - Tabela padrão - cliente sem categoria`, () => {
            const codigo = '75338119'
            const preco = '52,98'

            validaSemPrecoAntigo(codigo)
            inserirItemVenda(codigo, preco)
            validaPrecosItensInseridosSemDesconto(codigo, preco)
            validaTotalContinuaAtendimento('52,98')
        })
    })


    context('Validação de preços tabela de preço com desconto', () => {
        const produto = '75338010'
        const precoantigo = '52,98'
        const precovigente = '42,20'
        const desconto = '10,78'


        it(`Validação de preço como desconto`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')

            validaPrecoAntigo(produto, precoantigo)
            inserirItemVenda(produto, precovigente)
            
            validaPrecosItensInseridosComDesconto(produto, desconto, precoantigo, precovigente)

            validaTotalContinuaAtendimento('42,20')
        })
    })


    context('Validação de descontos', () => {
        const prod = require("../fixtures/produtos.json")
        const precoantigo = '52,98'


        it(`Validação de desconto progressivo tipo valor unitário`, () => {
            checkout.alterarCliente('ADEMIR MOREIRA')
            //0120.0011 5qtd desconto 1.99 preço final 50,99 unitario
            // 8 qantidades 2,56 50,42 unitario
            //0120.0010 5qtde 1,5 % 52,18
            //8qtde 2,55% 51,63
        })

        it(`Validação de desconto no item - Reais`, () => {
            inserirItemVenda('75338119', '52,98')
            inserirItemVenda('75338121', '47,00')
            inserirItemVenda('75338011', '47,00')

            checkout.descontoItem_reais('75338119', '2,98')
            validaPrecosItensInseridosComDesconto('75338119', '2,98', '52,98', '50,00')

            validaTotalContinuaAtendimento('144,00')
        })

        it(`Validação de desconto no item - Percentual`, () => {
            inserirItemVenda('75338119', '52,98')
            inserirItemVenda('75338121', '47,00')
            inserirItemVenda('75338011', '47,00')

            checkout.descontoItem_percentual('75338119', '10,00')
            validaPrecosItensInseridosComDesconto('75338119', '5,30', '52,98', '47,68')

            validaTotalContinuaAtendimento('141,68')
        })
    })
})

        // it(`Validação de desconto no total`, () => {

        // })

        // it(`Validação de desconto no item e no total`, () => {


        // })


    // context('Ação promocional', () => {
    //     it(`Por faixa de valor`, () => {

    //     })

    //     it(`Por quantidade`, () => {

    //     })

    //     it(`Por combinação de itens`, () => {

    //     })


    //     it(`Premiar com desconto no total`, () => {

    //     })

    //     it(`Premiar com brinde`, () => {

    //     })
    // })

    // context('Validação de valores adicionais', () => {

    //     it(`Acréscimo no total do atendimento`, () => {

    //     })
    // })
// })
