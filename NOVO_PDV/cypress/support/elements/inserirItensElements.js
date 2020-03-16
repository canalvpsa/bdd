const inserirItensElements = {
    PRODUTO:{
        PESQUISA_PRODUTO: '.form-control modo-pesquisa',
        TOGGLE_PESQUISA: '.toggle-btn',
        BTN_MODO_LISTAGEM: '.fad fa-th-list',
        FN_ADICIONAR_CARRINHO: posicao => `:nth-child(${posicao}) > .card > .card-footer > .clickable > .branco`,
        MENSAGEM: '.noty_body'
    },
    BTN_SALVAR_ATENDIMENTO: '.btn-outline-secondary'
}

export default inserirItensElements;
