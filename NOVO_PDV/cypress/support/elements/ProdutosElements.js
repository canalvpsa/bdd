const produtosElements = {
    DETALHES: {
        TIPO: 'btn-outline-primary',
        DESCONTO: ':nth-child(1) > .input-group > .form-control',
        UNIDADE: '.row > :nth-child(2) > .form-control',
        VENDEDOR: '.select2-selection__arrow > b',
        NOMEVEND: '.select2-search__field',
        SAIR: '.cdk-overlay-backdrop'
    },
    PESQUISA_PRODUTO: '.form-control modo-pesquisa',
    TOGGLE_PESQUISA: '.toggle-btn',
    BTN_MODO_LISTAGEM: '.fad fa-th-list',
    FN_ADICIONAR_CARRINHO: posicao => `:nth-child(${posicao}) > .card > .card-footer > .clickable > .branco`,
    MENSAGEM: '.noty_body',
    precoVigente: ':nth-child(2) > .card > .card-body > .precoVigente',
    INSERCAORAPIDA: '.fa-bolt',
    ADICIONARITEM: '.insercao-rapida',
    BTN_SALVAR_ATENDIMENTO: '.mr-1',
    BTN_PAGAMENTO: '.ml-1'
}

export default produtosElements;