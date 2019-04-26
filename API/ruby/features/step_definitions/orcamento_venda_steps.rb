include BD_consultas

Dado("o endereço da API para cadastrar orçamento de venda") do
 # token = consulta_token                                        
    $uri_base = 'http://qa.varejonline.com.br:8787/apps/api/orcamentos?token=9593bb0e62f13f6d75472a1336d252f0f6e7a5d12f66d7c5b73a5d801184e0aa'         
  end                                                                                                     
                                                                                                          
  Quando("realizar uma requisição para cadastar um orçamento de venda") do                                
    $response = HTTParty.post($uri_base, :body => {"validade":"5", "idEntidade":"10", "idTerceiro":"42", "idRepresentante":"1"})                      
  end                                                                                                     
                                                                                                          
  Então("a API retorna os dados do cadastro do orçamento de venda respondendo o código {int}") do |int|   
    puts "response body #{$response.body}"
    puts "response code #{$response.code}" 
  end                                                                                                     