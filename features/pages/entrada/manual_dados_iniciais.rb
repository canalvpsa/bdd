include BotoesEntradaNota
include CamposEntradaNota

module EntradaManual

    def preencherDadosIniciais(tipo_nota)
        fornecedor = setarPesquisa(abrirCampoPesquisa, 'LUNELLI')
        sleep 3
        apertarEnter(fornecedor)
      
        abrirEntidades
        setarEntidade('ENTIDADE SÃO PAULO - LUCRO REAL')
      
        setarNumeroDocumento(1010)
        setarSerieDocuimento(1)
      
        abrirPesquisaOperacoes
        operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA')
      
        sleep 1
        operacao.send_keys(:enter)
      
        abrirPesquisaTipoNota
        setarTipoNota(tipo_nota)
    end
end