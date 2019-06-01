module EntradaProdutos
    def incluirProdutos(lista_produtos)

          lista_produtos.each do |p|
               # converte para inteiro e faz loop
               # p["codigo_produto"].to_i.times do
               setarPesquisa(abrirCampoPesquisa, p["codigo_item"], false)
               sleep 1

               setarQuantidade(p["quantidade"]+'_000')
               clicarBotaoAdicionar
               sleep 1
          end
     end
end