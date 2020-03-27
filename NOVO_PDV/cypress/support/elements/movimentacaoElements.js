const movimentacaoElements = {
    RELATORIO:{
        PESQUISA: '.form-control',
        DETALHAR: ':nth-child(1) > .card-body > .clickable > .fad',
        CONTINUAR: '[tooltip="Continuar atendimento"] > .acao-atendimento > .acao-legenda-completa',
        VOLTAR: '[routerlink="/relatorio"] > .acao-atendimento > .fad',
        CANCELAR: '[tooltip="Cancelar Atendimento"] > .acao-atendimento'
    }
}

export default movimentacaoElements;