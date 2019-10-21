module EntradaProdutos
     
    def incluirProdutos(lista_produtos)

          lista_produtos.each do |p|
               setarPesquisa(abrirCampoPesquisa, p["codigo_item"], false)
               sleep 1

               i = 0
               num = consulta_parametro_custo
               begin
                    zeros = zeros.to_s + "0"
                    i +=1
               end while i < num

               setarQuantidade(p["quantidade"]+'_'+zeros)
               clicarBotaoAdicionar
               sleep 1
          end
     end
end