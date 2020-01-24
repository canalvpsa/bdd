
const entradaElements = {
    DADOSINICIAIS:{
        FORNECEDOR: '.select2-search__field',
        ENTIDADE: 'app-vo-entrada-dados-iniciais app-vo-filtro-entidades',
        NUMERONOTA:'input[formcontrolname=numeroDocumento]',
        SERIE: 'input[formcontrolname=serie]',
        OPERACAO: 'app-vo-filtro-operacoes[formcontrolname=operacao] .select2-selection__placeholder',
        TIPONOTA: 'app-vo-select[formcontrolname=tipoNotaFiscal] .select2-selection__placeholder',
        
    },
    VALORESDADOSADICIONAIS:{
        FRETE: 'input[formcontrolname:valorFrete]',
    },
    PRODUTOS:{
        PRODUTO: '.select2-search__field',
        QUANTIDADE: 'input[formcontrolname=quantidade]',
        BTN_ADICIONAR: 'app-vo-entrada-item-insercao .btn-primary'
    },
    BTN_SALVAR: '.wizard-button-next'
}
export default entradaElements;