
class CamposEntrada{

    campoPesquisaAberto = (campo, valor) => {
        cy.get('.select2-search__field').type(valor)
        cy.wait(1000)
        cy.get('.select2-results__option').click()
    }

    campoPesquisa = (campo, valor, campo2 = campo) => {
        cy.get(campo).click()
        cy.get('.select2-search__field').type(valor)
        cy.wait(1000)
        cy.contains('.select2-results__options li', campo2).click()
    }

    campoListagem = (campo, itemLista) => {
        cy.get(campo).click()
        cy.contains('.select2-results__options li', itemLista).click()
    }

    preencherInput = (campo, valor) => {
        cy.get(campo).type(valor)
    }
}

export default CamposEntrada;
