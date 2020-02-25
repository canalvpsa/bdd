
const entradaElements = {
    DADOSINICIAIS:{
        FORNECEDOR: '.select2-search__field',
        ENTIDADE: 'app-vo-entrada-dados-iniciais app-vo-filtro-entidades',
        NUMERONOTA:'input[formcontrolname=numeroDocumento]',
        SERIE: 'input[formcontrolname=serie]',
        OPERACAO: 'app-vo-filtro-operacoes[formcontrolname=operacao] .select2-selection__placeholder',
        TIPONOTA: 'app-vo-select[formcontrolname=tipoNotaFiscal] .select2-selection__placeholder',
        CHAVE: 'input[formcontrolname=chaveNfe]',
        BTN_IMPORTAR_XML: '.offset-2 > .btn',
        
    },
    IMPORTAR_XML:{
        IMPORTAR_ARQUIVO: 'app-vo-entrada-importar-xml input[aria-describedby=button-importar]',
        ARQUIVO_FILE: 'app-vo-entrada-importar-xml input[formcontrolname=arquivo]',
        ESCOLHER_ARQUIVO: 'app-vo-entrada-importar-xml #button-importar',
        BTN_ESCOLHER_ARQUIVO: 'app-vo-entrada-importar-xml .btn-escolher',
        CONCILIA_NOTA: 'app-vo-radio-group[name=codigoConciliadorXml]',
        CONCILIA_SISTEMA: 'app-vo-radio-group[name=codigoConciliadorSistema]',
        BTN_IMPORTAR: 'app-vo-entrada-importar-xml app-vo-modal .btn-primary',
    },
    VALORESDADOSADICIONAIS:{
        FRETE: 'input[formcontrolname:valorFrete]',
    },
    PRODUTOS:{
        PRODUTO: '.select2-search__field',
        QUANTIDADE: 'input[formcontrolname=quantidade]',
        BTN_ADICIONAR: 'app-vo-entrada-item-insercao .btn-primary'
    },
    PESQUISA:{
        BTN_EXCLUIR: '.fa fa-trash-alt',
        BTN_CONFIRMAR: '.noty_body noty-ok-button'
    },
    BTN_SALVAR: '.wizard-button-next',
    MENSAGEM: '.noty_body'
}
export default entradaElements;