
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
        DATAEMISSAO: 'app-vo-data[formcontrolname=dataEmissao] input'
    },
    IMPORTAR_XML:{
        IMPORTAR_ARQUIVO: 'app-vo-entrada-importar-xml input[aria-describedby=button-importar]',
        ARQUIVO_FILE: 'app-vo-entrada-importar-xml input[type=file]',
        ESCOLHER_ARQUIVO: 'app-vo-entrada-importar-xml #button-importar',
        BTN_ESCOLHER_ARQUIVO: 'app-vo-entrada-importar-xml .btn-escolher',
        CONCILIA_NOTA: 'app-vo-radio-group[name=codigoConciliadorXml]',
        CONCILIA_SISTEMA: 'app-vo-radio-group[name=codigoConciliadorSistema]',
        BTN_IMPORTAR: 'app-vo-entrada-importar-xml app-vo-modal .btn-primary',
    },
    VALORESDADOSADICIONAIS:{
        FRETE: 'input[formcontrolname:valorFrete]',
        CTE: 'app-vo-entrada-valores .fa-truck',
        CUSTOADICIONAL: 'input[formcontrolname:valorCustoAdicional]'
    },
    CONHECIMENTOTRANSPORTE:{
        NUMERO: 'app-vo-entrada-cte input[formcontrolname=numero]',
        PRESTADORSERVICO: 'app-vo-entrada-cte app-vo-filtro-prestador-servico',
        VALORICMS: 'app-vo-entrada-cte input[formcontrolname=valorIcms]',
        VALORPIS: 'app-vo-entrada-cte input[formcontrolname=valorPis]',
        VALORCOFINS: 'app-vo-entrada-cte input[formcontrolname=valorCofins]',
        VALORTOTAL: 'app-vo-entrada-cte input[formcontrolname=valorTotal]'
    },
    PRODUTOS:{
        PRODUTO: '.select2-search__field',
        QUANTIDADE: 'input[formcontrolname=quantidade]',
        BTN_ADICIONAR: 'app-vo-entrada-item-insercao .btn-primary'
    },
    PESQUISA:{
        DOCUMENTO: numNota  => `//td[contains(., '${numNota}')]/../td[3]`,
        BTN_EXCLUIR: '.row .fa-trash-alt',
                      //span[contains(.,'Excluir')]/../../../../..//i[@class='fa fa-trash-alt']
        BTN_CONFIRMAR: '#noty-ok-button',
        PROCESSANDO: '.dataTables_processing'
    },
    BTN_SALVAR: '.wizard-button-next',
    MENSAGEM: '.noty_body',
    ULTIMAS_NOVIDADES: '#pushActionRefuse',
    LOAD_PAGINA: 'block-ui-content .block-ui-wrapper .active',
    LOAD_CAMPO: '.loading-stick'
}
export default entradaElements;