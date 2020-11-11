const elements = {
    LOGIN: {
        USER: 'input[formcontrolname=login]',
        PASSWORD: 'input[formcontrolname=senha]',
        BTN_ENTRAR: '.btn',
        CONECTADO: 'app-toggle'
    },
    MENU: {
        ATENDIMENTO: 'mat-nav-list fa-shopping-cart',
        MOVIMENTACOES: 'mat-nav-list .fa-file-chart-line',
        RANKINGVENDEDOR: 'mat-nav-list .fa-chart-bar',
        CLIENTES: 'mat-nav-list .fa-users',
        PESQUISA: 'mat-nav-list fa-search',
        CONFIGURACAO: 'mat-nav-list .fa-cog',
        TRANSACAOPENDENTE: '.fa-credit-card'
    },
    CHECKOUT: {
        LISTAPRODUTOS: {
            DESCRICAOPRODUTO: 'app-atendimento app-atendimento-vendivel span',
            QUANTIDADEMAIOR: 'app-atendimento-vendivel .btn-sm .fa-plus-circle',
            QUANTIDADEMENOR: 'app-atendimento-vendivel .btn-sm .fa-minus-circle',
            VALORTOTAL: 'app-atendimento-vendivel .valor-total',
            DETALHEITEM: {
                REAIS: 'app-atendimento-vendivel-edicao .input-group-prepend .btn-primary',
                PERCENTUAL: 'app-atendimento-vendivel-edicao .input-group-prepend .btn-outline-primary',
                INSERCAOCLIENTE: '.mat-input-element',
                DESCONTOVALOR: '.input-group .nput-group-prepend form-control',
            },
        },
        ACAO_PROMOCIONAL: {

        },
        CLIENTE: 'app-select-terceiro ',
        VALORTOTAL: '.action-total .total',
        BTN_PAGAMENTOS: '.d-flex btn-primary',
        BTN_SALVAR_ATENDIMENTO: '.d-flex .btn-outline-secondary',
        BTN_APLICAVEL: '.btn btn-outline-primary btn-block',
    },

    INSERIRITENS: {
        DETALHES: {
            TIPO: 'btn-outline-primary',
            DESCONTO: ':nth-child(1) > .input-group > .form-control',
            UNIDADE: '.row > :nth-child(2) > .form-control',
            VENDEDOR: '.select2-selection__arrow > b',
            NOMEVEND: '.select2-search__field',
            SAIR: '.cdk-overlay-backdrop'
        },
        PESQUISA_PRODUTO: 'app-vendivel .form-control',
        TOGGLE_PESQUISA: 'app-atendimento app-vendivel app-toggle-icons toggle-btn',
        OPCAO_INSERCAO_RAPIDA: 'app-atendimento app-vendivel app-toggle-icons toggle-btn-off',
        BTN_MODO_LISTAGEM: '.fad fa-th-list',
        FN_ADICIONAR_CARRINHO: posicao => `:nth-child(${posicao}) > .card > .card-footer > .clickable > .branco`,
        MENSAGEM: '.noty_body',
        PRECOVIGENTE: ':nth-child(2) > .card > .card-body > .precoVigente',
        PESQUISAITEM: 'app-atendimento app-vendivel .form-control',
    },


    MOVIMENTACAO: {
        LISTAGEM: 'app-movimentacao-listagem .form-control',
        DETALHAR: '//app-movimentacao-listagem/div/div[2]/div[2]/div[1]/div/div[1]/span',
        DETALHAMENTO: {
            CONTINUAR: 'app-movimentacao-listagem-detalhe .acao-legenda-completa',
            VOLTAR: 'app-movimentacao-listagem-detalhe .clickable',
            CANCELAR: 'app-movimentacao-listagem-detalhe .acao-atendimento'
        },
    },

    PAGAMENTO: {
        SELECAO: {
            CARTAO: '.botoes-pagamento > :nth-child(1)',
            OPERADORA: {
                CIELO: '.d-flex > :nth-child(1) > .foto > app-imagem > img',
                REDE: '#cdk-step-content-0-0 > .d-flex > :nth-child(2) > .foto > app-imagem > img',
                STONE: '.d-flex > :nth-child(3) > .foto > app-imagem > img',
                BANDEIRAREDE: {
                    AURA: '#cdk-step-content-0-1 > .d-flex > :nth-child(1) > .foto > app-imagem > img',
                    AMEX: '#cdk-step-content-0-1 > .d-flex > :nth-child(2) > .foto > app-imagem > img',
                    VISA: '#cdk-step-content-0-1 > .d-flex > :nth-child(3) > .foto > app-imagem > img',
                    DINNERS: ':nth-child(4) > .foto > app-imagem > img',
                    HIPER: ':nth-child(5) > .foto > app-imagem > img',
                    MASTER: ':nth-child(6) > .foto > app-imagem > img',
                },
                DINHEIRO: '.botoes-pagamento > :nth-child(2)',
                SALDO: {
                    TOTAL: '.flex-column > :nth-child(1) > :nth-child(1)',
                    CEM: '.flex-column > :nth-child(1) > :nth-child(2)',
                    CINQUENTA: '.flex-column > :nth-child(1) > :nth-child(3)',
                    VINTE: '.flex-column > :nth-child(1) > :nth-child(4)',
                    DEZ: '.flex-column > :nth-child(1) > :nth-child(5)',
                    CINCO: '.flex-column > :nth-child(1) > :nth-child(6)',
                    DOIS: '.flex-column > :nth-child(1) > :nth-child(7)',
                    MANUAL: '.flex-column > :nth-child(2) > .form-control',
                },
                CHEQUE: '.botoes-pagamento > :nth-child(3)',
                DADOSBANCO: {
                    BANCO: '.select2-selection__arrow',
                    AGENCIA: '.d-flex > :nth-child(2) > .form-control',
                    CONTA: ':nth-child(3) > .form-control',
                    CHEQUE: '.col-xl-1 > .form-control',
                    VALOR: ':nth-child(5) > .form-control',
                    ADICIONAR: '.row > :nth-child(1) > .btn',
                    CANCELAR: '.flex-column > .row > :nth-child(2) > .btn',
                },
                TEF: '[aria-describedby="tooltip-93"]',
                TRANSACAO: {
                    VALOR: '#valor',
                    CREDITO: '.ng-star-inserted > :nth-child(2) > :nth-child(1) > .btn',
                    DEBITO: '.container > .ng-star-inserted > :nth-child(2) > :nth-child(2) > .btn',
                },
                ADYEN: '.botoes-pagamento > :nth-child(5)',
                TRANSACAO2: {
                    VALOR: '#valor',
                    CREDITO: '.ng-star-inserted > :nth-child(2) > :nth-child(1) > .btn',
                    DEBITO: '.container > .ng-star-inserted > :nth-child(2) > :nth-child(2) > .btn',
                    PARCELAS: '#parcelas',
                    PROCESSAR: '.container > .ng-star-inserted > :nth-child(2) > :nth-child(2) > .btn',
                    VOLTAR: '.ng-star-inserted > :nth-child(2) > :nth-child(1) > .btn',
                },
                CREDIARIO: '.botoes-pagamento > :nth-child(6)',
                PLANO: {
                    PLANO: '.select2-selection__arrow > b',
                    CONFIRMAR: '.row > .d-flex > .btn',
                },
                ADICIONAR: ':nth-child(3) > .botao-grande',
                CONCLUIR: '.m-2'
            }
        },
    }
}

export default elements;