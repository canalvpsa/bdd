class inserirprod{

    Insercaoitens = (`Inserção rápida`, () => {
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
    
}

export default CamposEntrada;