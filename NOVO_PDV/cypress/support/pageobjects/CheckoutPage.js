/// <reference types="Cypress" />

import checkout from '../elements/checkoutElements'


class CheckoutPage{

    validaListaVazia(){
        cy.wait(2000)
        cy.get(checkout.CLIENTE).should('contain', 'Diversos')
        cy.get(checkout.PRODUTOLISTA, {timeout:10000}).should('not.exist')
        cy.get(checkout.VALORATENDIMENTO).should('contain', '0,00')
    }


    validaBotoesPreVenda(){
        cy.xpath(checkout.FN_BTN_SALVAR).should('exist')
        cy.xpath(checkout.FN_BTN_PAGAMENTOS).should('not.exist')
    }

    validaBotoesVenda(){
        cy.xpath(checkout.FN_BTN_SALVAR).should('exist')
        cy.xpath(checkout.FN_BTN_PAGAMENTOS).should('exist')
    }

    alterarCliente(nomeCliente){
        cy.get(checkout.CLIENTE).click()
        cy.get(checkout.CLIENTEPESQUISA).type(nomeCliente)
        cy.get(checkout.CLIENTELISTAGEM).click({ force: true })
    }

    getAumentarQuantidadeItem(){
        return cy.get(checkout.LISTAPRODUTOS.QUANTIDADEMAIOR)
    }

    getDiminuirQuantidadeIem(){
        return cy.get(checkout.LISTAPRODUTOS.QUANTIDADEMENOR)
    }

   

    descontoItem_percentual(){

    }

    descontoItem_reais(desconto){
        cy.get(checkout.LISTAPRODUTOS.DESCRICAOPRODUTO).click()
        cy.get(checkout.LISTAPRODUTOS.REAIS).should('contain', 'R$')
        cy.get(checkout.LISTAPRODUTOS.DESCONTOVALOR).type(desconto)
        cy.get(checkout.LISTAPRODUTOS.DESCRICAOPRODUTO).click()
    }

    descontoTotal_percentual(desconto){
        cy.get(checkout.LISTAPRODUTOS.DESCRICAOPRODUTO).click()
        cy.get(checkout.LISTAPRODUTOS.PERCENTUAL).click()
        cy.get(checkout.LISTAPRODUTOS.DESCONTOVALOR).type(desconto)
        cy.get(checkout.LISTAPRODUTOS.DESCRICAOPRODUTO).click()
    }

    descontoTotal_reais(){
        
    }

    getSalvarAtendimento(){
        return cy.xpath(checkout.FN_BTN_SALVAR)
    }

    getItemListagem(){
        return cy.get(checkout.PRODUTOLISTA)
    }

    getItemQuantidade(){
        return cy.get(checkout.LISTAPRODUTOS.QUANTIDADEITEM)
    }

    getItemPrecoUnitario(){
        return cy.get(checkout.LISTAPRODUTOS.PRECOUNITARIO)
    }

    getItemPrecoTotal(){    
        return cy.get(checkout.LISTAPRODUTOS.VALORTOTAL)
    }

    validaValorTotalItem(){
        return cy.get(checkout.LISTAPRODUTOS.VALORTOTAL)
    }

    getValorTotalVenda(){
        return cy.get(checkout.VALORATENDIMENTO)
    }
}

export default CheckoutPage;