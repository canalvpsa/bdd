
/// <reference types="Cypress" />

import menu from '../elements/menuElements'
import movimentacao from '../elements/movimentacaoElements'


class MovimentacaoPage{

  // cancelarUltimoAtendimento(motivo, observacao){
  //   cy.get(menu.MOVIMENTACOES).click()
  //   cy.xpath(movimentacao.DETALHAR, {timeout:10000}).click()
  //  // cy.get(
  //   cy.xpath(movimentacao.DETALHAMENTO.FN_CANCELAR).click()
  //   cy.get(movimentacao.CANCELAMENTO.MOTIVO).click()
  //   cy.get('.select2-search__field').click().type(motivo)
  //   cy.get(movimentacao.CANCELAMENTO.OBSERVACAO).type(observacao)
  //   cy.xpath(movimentacao.CANCELAMENTO.FN_BTN_CANCELAR).click()
  //  // Atendimento 44-00118 encerrado com sucesso!
  // }

  validarStatusUltimoAtendimento(){

  }

  validarMensagem(mensagem) {
    cy.get(movimentacao.MENSAGEM).should('contain', mensagem)
  }
  
}

export default MovimentacaoPage;