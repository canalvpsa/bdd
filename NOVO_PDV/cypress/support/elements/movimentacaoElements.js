const movimentacaoElements = {
    LISTAGEM: 'app-movimentacao-listagem .form-control',
    DETALHAR: '.listagem-body > :nth-child(1) > .card-body',
    MENSAGEM: '.noty_body',
    DETALHAMENTO: {
        FN_CONTINUAR: `//i[@class='acao-primaria fad fa-shopping-cart']/..//span[contains(., 'Continuar Atendimento')]`,
        FN_VOLTAR: `//i[@class='fad fa-reply']/..//span[contains(., 'Voltar')]`,
        FN_CANCELAR: `//i[@class='fad fa-file-times']/..//span[contains(., 'Cancelar Atendimento')]`
    },
    CANCELAMENTO: {
        MOTIVO: 'app-motivo-cancelamento app-select .select2-container',
        OBSERVACAO: 'mat-dialog-container app-motivo-cancelamento textarea',
        FN_BTN_SAIR:  `//div//button[contains(., 'Sair')]`,
        FN_BTN_CANCELAR: `//div//button[contains(., 'Cancelar Doc. Fiscal')]`
    }
}

export default movimentacaoElements;