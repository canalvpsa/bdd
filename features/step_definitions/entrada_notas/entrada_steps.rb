include VisaoGeral

Quando('finalizar a entrada') do
    clicarBotaoFinalizar
  end

 Quando("informa custo adicional de {string} nos dados adicionais") do |valorCusto|
    #Salvar dados adicionais
    preencherCustoAdicional(valorCusto)
    clicarBotaoSalvar
 end

 Então("ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
    clicarBotaoFinalizar

    mensagemExibida = find('.noty_body', wait: 20).text
    puts mensagemExibida
    expect('A entrada '+ numeroDocumento+'/'+serie+' foi efetuada com sucesso.').to include mensagemExibida
  end


Então("a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
    mensagemExibida = find('.noty_body', wait: 20).text
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


  Então("o custo total do item {string} da nota será {string}") do |codigoProduto, custoProduto|
    include BD_validacao
    expect(custoProduto).to have_content consulta_custoProduto(codigoProduto)
  end