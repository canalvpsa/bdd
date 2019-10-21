include BotoesEntradaNota
include CamposEntradaNota

module EntradaManual

    
    def preencherDadosIniciais_tiposNota(tipo_nota, nro_documento, serie)
        abrirFornecedor
        setarPesquisa(abrirCampoPesquisa, 'LUNELLI', true)
       

        abrirEntidades
        setarEntidade('ENTIDADE SÃO PAULO - LUCRO REAL')
      
        setarNumeroDocumento(nro_documento)
        setarSerieDocumento(serie)
      
        abrirPesquisaOperacoes
        operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA', true)

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

        abrirFornecedor
        fornecedor = setarPesquisa(abrirCampoPesquisa, 'LUNELLI', true)

        abrirEntidades
        setarEntidade(entidade)
      
        setarNumeroDocumento(1010)
        setarSerieDocumento(1)
      
        abrirPesquisaOperacoes
        operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA', true)

        abrirPesquisaTipoNota
        setarTipoNota('55')
    end
end