include VisaoGeral

Dado("que o parâmetro de custo do ERP está configurado para {int} casas decimais") do |qtde_decimais|
  parametro_decimais_custo(qtde_decimais)
end


Dado("que confirma os dados adicionais") do
  #Salvar os dados adicionais
  sleep 2
  clicarBotaoSalvar
end


Dado("que confirma os dados adicionais e confirma os produtos") do
  #Salvar nos dados adicionais
  sleep 2
  clicarBotaoSalvar

  #Salvar nos produtos
  sleep 2
  clicarBotaoSalvar
end


Dado("que informa custo adicional de {string} nos dados adicionais") do |valorCusto|
  #Salvar dados adicionais
  preencherCustoAdicional(valorCusto)
  clicarBotaoSalvar
end


Quando("confirma os produtos") do
  #Salvar nos produtos
  sleep 2
  clicarBotaoSalvar  
end


Quando("que confirma a visão geral") do
  #Salvar visão geral
  sleep 2
  clicarBotaoSalvar  
end


Quando('finaliza a entrada') do
  clicarBotaoFinalizar
  end

 Então("ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
    clicarBotaoFinalizar

    mensagemExibida = find('.noty_body', wait: 100).text
    puts mensagemExibida
    expect('A entrada '+ numeroDocumento+'/'+serie+' foi efetuada com sucesso.').to include mensagemExibida
  end


Então("a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
    mensagemExibida = find('.noty_body', wait: 100).text
    puts mensagemExibida
    expect('A entrada '+ numeroDocumento+'/'+serie+' foi efetuada com sucesso.').to include mensagemExibida
  end
  
  
  Então("a nota fiscal é registrada com status {string}") do |status_escrituracao|
    expect(status_escrituracao).to have_content consulta_escrituracao
    
  end
  
  
  Então("na visão geral é exibido custo adicional de {string}") do |valorCusto|
    validaCustoAdicional(valorCusto)

     #Salvar na visão geral
     clicarBotaoSalvar
  end

  Então("na visão geral o valor total dos produtos é {string}") do |total_produtos|
    valida_total_produtos(total_produtos)

    #Salvar na visão geral
    clicarBotaoSalvar
  end


  Então("o custo total do item {string} da nota será {string}") do |codigoProduto, custoProduto|
    include BD_validacao
    expect(custoProduto).to have_content consulta_custoProduto(codigoProduto)
  end