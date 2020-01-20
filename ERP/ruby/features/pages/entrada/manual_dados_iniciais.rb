#include BotoesEntradaNota
#include CamposEntradaNota

class EntradaManual_dados_iniciais
    include Capybara::DSL

     # construtor
    def initialize
        #@campos_entrada = CamposEntradaNota.load
    end


    def preencherDadosIniciais_tiposNota(tipo_nota, nro_documento, serie)
        @@campos_entrada.abrirFornecedor
        @@campos_entrada.setarPesquisa(abrirCampoPesquisa, 'LUNELLI', true)
       

        @@campos_entrada.abrirEntidades
        @@campos_entrada.setarEntidade('ENTIDADE SÃO PAULO - LUCRO REAL')
      
        @@campos_entrada.setarNumeroDocumento(nro_documento)
        @@campos_entrada.setarSerieDocumento(serie)
      
        @@campos_entrada.abrirPesquisaOperacoes
        operacao =  @campos_entrada.setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA', true)

        @@campos_entrada.abrirPesquisaTipoNota
        @@campos_entrada.setarTipoNota(tipo_nota)
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

        @@campos_entrada.abrirFornecedor
        fornecedor = @@campos_entrada.setarPesquisa(abrirCampoPesquisa, 'LUNELLI', true)

        @@campos_entrada.abrirEntidades
        @@campos_entrada.setarEntidade(entidade)
      
        @@campos_entrada.setarNumeroDocumento(1010)
        @@campos_entrada.setarSerieDocumento(1)
      
        @@campos_entrada.abrirPesquisaOperacoes
        operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA', true)

        @@campos_entrada.abrirPesquisaTipoNota
        @@campos_entrada.setarTipoNota('55')
    end
end