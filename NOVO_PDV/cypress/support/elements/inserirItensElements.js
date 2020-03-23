const inserirItensElements = {
    PRODUTO:{
        PESQUISA_PRODUTO: '.form-control modo-pesquisa',
        TOGGLE_PESQUISA: '.toggle-btn',
        BTN_MODO_LISTAGEM: '.fad fa-th-list',
        FN_ADICIONAR_CARRINHO: posicao => `:nth-child(${posicao}) > .card > .card-footer > .clickable > .branco`,
        MENSAGEM: '.noty_body',
        precoVigente: ':nth-child(2) > .card > .card-body > .precoVigente',
        INSERCAORAPIDA: '.fa-bolt',
        ADICIONARITEM: '.insercao-rapida'
    },
    BTN_SALVAR_ATENDIMENTO: '.mr-1',
    BTN_PAGAMENTO: '.ml-1'
}

export default inserirItensElements;
