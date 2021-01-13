/// <reference types="Cypress" />

import checkout from '../elements/checkoutElements'


class CheckoutPage {

    validaListaVazia() {
        cy.wait(2000)
        cy.get(checkout.CLIENTE).should('contain', 'Diversos')
        cy.get(checkout.PRODUTOLISTA, { timeout: 10000 }).should('not.exist')
        cy.get(checkout.VALORATENDIMENTO).should('contain', '0,00')
    }


    validaBotoesPreVenda() {
        cy.xpath(checkout.FN_BTN_SALVAR).should('exist')
        cy.xpath(checkout.FN_BTN_PAGAMENTOS).should('not.exist')
    }

    validaBotoesVenda() {
        cy.xpath(checkout.FN_BTN_SALVAR).should('exist')
        cy.xpath(checkout.FN_BTN_PAGAMENTOS).should('exist')
    }

    alterarCliente(nomeCliente) {
        cy.get(checkout.CLIENTE).click()
        cy.get(checkout.CLIENTEPESQUISA).type(nomeCliente)
        cy.get(checkout.CLIENTELISTAGEM).click({ force: true })
        cy.get(checkout.CLIENTE).contains(nomeCliente, { matchCase: false })
    }

    getAumentarQuantidadeItem() {
        return cy.get(checkout.LISTAPRODUTOS.QUANTIDADEMAIOR)
    }

    getDiminuirQuantidadeIem() {
        return cy.get(checkout.LISTAPRODUTOS.QUANTIDADEMENOR)
    }


    alterarItem(produto){
        cy.wait(2000)
        this.getItemListagem().contains(produto).parents('.d-flex').within(() => {
            cy.get(checkout.LISTAPRODUTOS.DESCRICAOPRODUTO).click()
        })
    }


    fecharAlterarItem(){
        cy.wait(2000)
        cy.xpath(checkout.LISTAPRODUTOS.EDICAOITEM.FN_BTN_FECHAR).click()
        cy.xpath(checkout.LISTAPRODUTOS.EDICAOITEM.FN_BTN_FECHAR).should('not.exist')
    }

    alteraQuantidadeDetalhe(produto, quantidade) {
       this.alterarItem(produto)
        cy.get(checkout.LISTAPRODUTOS.EDICAOITEM.QUANTIDADE)
            .type('{selectall}')
            .type(quantidade)
          this.fecharAlterarItem()
    }

    descontoItem_percentual(produto, desconto) {
        this.alterarItem(produto)
        cy.get(checkout.LISTAPRODUTOS.EDICAOITEM.BTN_TIPO).contains('R$').click()
        cy.get(checkout.LISTAPRODUTOS.EDICAOITEM.DESCONTOVALOR)
            .type('{selectall}')
            .type(desconto)
            this.fecharAlterarItem()
    }


    descontoItem_reais(produto, desconto) {
        this.alterarItem(produto)
        cy.get(checkout.LISTAPRODUTOS.EDICAOITEM.DESCONTOVALOR).type(desconto)
        this.fecharAlterarItem()
    }

    descontoTotal_percentual(desconto) {
        cy.get(checkout.LISTAPRODUTOS.PERCENTUAL).click()
        cy.get(checkout.LISTAPRODUTOS.DESCONTOVALOR).type(desconto)

    }

    descontoTotal_reais() {

    }

    salvarAtendimento() {
       cy.xpath(checkout.FN_BTN_SALVAR).click()
       cy.get(checkout.MENSAGEM).should('contain', 'Atendimento salvo com sucesso')
       cy.wait(3000)
    }

    getItemListagem() {
        return cy.get(checkout.PRODUTOLISTA)
    }

    getItemQuantidade() {
        return cy.get(checkout.LISTAPRODUTOS.QUANTIDADEITEM)
    }

    getItemPrecoUnitario() {
        return cy.get(checkout.LISTAPRODUTOS.PRECOUNITARIO)
    }

    getItemPrecoTotal() {
        return cy.get(checkout.LISTAPRODUTOS.VALORTOTAL)
    }

    getItemDescontoUnitario() {
        return cy.get(checkout.LISTAPRODUTOS.DESCONTOUNITARIO)
    }

    getValorTotalItem() {
        return cy.get(checkout.LISTAPRODUTOS.VALORTOTAL)
    }

    getValorTotalVenda() {
        return cy.get(checkout.VALORATENDIMENTO)
    }

}

export default CheckoutPage;