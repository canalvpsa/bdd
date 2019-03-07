include BotoesEntradaNota
include CamposEntradaNota

module EntradaManual

    def preencherDadosIniciais_tiposNota(tipo_nota, nro_documento, serie)
        fornecedor = setarPesquisa(abrirCampoPesquisa, 'LUNELLI')
        sleep 3
        apertarEnter(fornecedor)
      
        abrirEntidades
        setarEntidade('ENTIDADE SÃO PAULO - LUCRO REAL')
      
        setarNumeroDocumento(nro_documento)
        setarSerieDocuimento(serie)
      
        abrirPesquisaOperacoes
        operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA')
      
        sleep 1
        operacao.send_keys(:enter)
      
        abrirPesquisaTipoNota
        setarTipoNota(tipo_nota)
    end

    def preencherDadosIniciais_tiposEntidade(entidade)
        case entidade
        when 'real'
            entidade = 'ENTIDADE SÃO PAULO - LUCRO REAL'

        when 'presumido'
            entidade = 'ENTIDADE SÃO PAULO - LUCRO PRESUMIDO'

        when 'simples'
            entidade = 'ENTIDADE SÃO PAULO - SIMPLES'
        end


        fornecedor = setarPesquisa(abrirCampoPesquisa, 'LUNELLI')
        sleep 3
        apertarEnter(fornecedor)
      
        abrirEntidades
        setarEntidade(entidade)
      
        setarNumeroDocumento(1010)
        setarSerieDocuimento(1)
      
        abrirPesquisaOperacoes
        operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA')
      
        sleep 1
        operacao.send_keys(:enter)
      
        abrirPesquisaTipoNota
        setarTipoNota('55')
    end
end