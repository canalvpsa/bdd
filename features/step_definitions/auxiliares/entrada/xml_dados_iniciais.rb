module EntradaXML

    def preencherDadosIniciaisXML()      
        abrirEntidades
        setarEntidade('DEV')
      
        abrirPesquisaOperacoes
        operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA')
      
        sleep 1
        operacao.send_keys(:enter)
    end
end