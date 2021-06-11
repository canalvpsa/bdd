const pagamentoElements = {
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
            FN_BTN_ADICIONAR: `//div//button[contains(., 'Adicionar')]`,
            CONCLUIR: '.m-2'
        }
    },

}

export default pagamentoElements;