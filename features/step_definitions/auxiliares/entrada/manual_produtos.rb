module EntradaProdutos

    def incluirProdutos()
 setarPesquisa(abrirCampoPesquisa, 'PRODUTO DA LETICIA VERMELHO')
 sleep 1

 setarQuantidade(20_000)
 clicarBotaoAdicionar
 sleep 1

 setarPesquisa(abrirCampoPesquisa, 'PRODUTO DA LETICIA Preto')
 sleep 1

 setarQuantidade(20_000)
 clicarBotaoAdicionar
 sleep 1
    end

    def incluirProdutos_AlterandoValorUnitario()
        setarPesquisa(abrirCampoPesquisa, 'PRODUTO DA LETICIA VERMELHO')
        sleep 1
       
        setarQuantidade(20_000)
        clicarBotaoAdicionar
        sleep 1
       
        setarPesquisa(abrirCampoPesquisa, 'PRODUTO DA LETICIA Preto')
        sleep 1
       
        setarQuantidade(20_000)
        clicarBotaoAdicionar
        sleep 1
           end
end