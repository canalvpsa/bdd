const InserirItensElements = {
    DETALHES: {
        TIPO: 'btn-outline-primary',
        DESCONTO: ':nth-child(1) > .input-group > .form-control',
        UNIDADE: '.row > :nth-child(2) > .form-control',
        VENDEDOR: '.select2-selection__arrow > b',
        NOMEVEND: '.select2-search__field',
        SAIR: '.cdk-overlay-backdrop'
    },
    PESQUISA_PRODUTO: 'app-vendivel .form-control',
    OPCAO_INSERCAO_RAPIDA: 'app-atendimento app-vendivel app-radio-group-icons .form-group .c1',
    OPCAO_PESQUISA: 'app-atendimento app-vendivel app-radio-group-icons .form-group .c2',
    OPCAO_EXCLUIR: 'app-atendimento app-vendivel app-radio-group-icons .form-group .c3',
    BTN_MODO_LISTAGEM: '.fad fa-th-list',
    FN_ADICIONAR_CARRINHO: posicao => `:nth-child(${posicao}) > .card > .card-footer > .clickable > .branco`,
    MENSAGEM: '.noty_body',
    PRODUTOPESQUISADO: 'app-vendivel-grid-item.ng-star-inserted > .card > .card-body',
    PRECOANTIGO: ' del',
    PRECOVIGENTE: ' .precoVigente',
    PESQUISAITEM: 'app-atendimento app-vendivel .form-control',
    IMAGEMPRODUTO: '.card > app-imagem > img',
    BTN_ADICIONAR: '.mat-raised-button'
}

export default InserirItensElements;
