const CheckoutElements = {
    PRODUTOLISTA: 'app-atendimento-vendivel .d-flex',
    LISTAPRODUTOS: {
        DESCRICAOPRODUTO: '.descricao span',
        QUANTIDADEMAIOR: '.botoesQuantidade .fa-plus-circle',
        QUANTIDADEMENOR: '.botoesQuantidade .fa-minus-circle',
        QUANTIDADEITEM: '.botoesQuantidade .font-weight-bold',
        PRECOUNITARIO: 'div small',
        VALORTOTAL: 'div .valor-total',
        DESCONTO: {
            REAIS: 'app-atendimento-vendivel-edicao .input-group-prepend .btn-primary',
            PERCENTUAL: 'app-atendimento-vendivel-edicao .input-group-prepend .btn-outline-primary',
            DESCONTOVALOR: 'app-atendimento-vendivel-edicao .input-group-prepend .form-control',
            INSERCAOCLIENTE: '.mat-input-element'
        },
    },
    DESCONTO: {
        REAIS: '.input-group .nput-group-prepend .btn-primary',
        PERCENTUAL: '.input-group .nput-group-prepend .btn-outline-primary',
        DESCONTOVALOR: '.input-group .nput-group-prepend form-control',
    },
    ACAO_PROMOCIONAL: {

    },
    CLIENTE: 'app-atendimento app-select-terceiro .font-weight-normal',
    CLIENTEPESQUISA: 'app-atendimento app-select-terceiro .barra-pesquisa .mat-input-element',
    CLIENTELISTAGEM: `.mat-option-text .d-flex`,
    VALORATENDIMENTO: '.action-total .total',
    FN_BTN_PAGAMENTOS: `//div//button[contains(., 'Pagamentos')]`,
    FN_BTN_SALVAR: `//div//button[contains(., 'Salvar atendimento')]`,
}

export default CheckoutElements;