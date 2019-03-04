module EntradaProdutos

    def incluirProdutos()
         setarPesquisa(abrirCampoPesquisa, '2541.001')
         sleep 1

         setarQuantidade(20_000)
         clicarBotaoAdicionar
         sleep 1

         setarPesquisa(abrirCampoPesquisa, '2541.002')
         sleep 1

         setarQuantidade(10_000)
         clicarBotaoAdicionar
         sleep 1
    end

    def incluirProdutos_AlterandoValorUnitario()
        setarPesquisa(abrirCampoPesquisa, '2541.001')
        sleep 1
       
        setarQuantidade(20_000)
        clicarBotaoAdicionar
        sleep 1
       
        setarPesquisa(abrirCampoPesquisa, '2541.002')
        sleep 1
       
        setarQuantidade(20_000)
        clicarBotaoAdicionar
        sleep 1
   end
end