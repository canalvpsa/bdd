include BotoesEntradaNota
include CamposEntradaNota
include EfetuarLogin
include ApertarEnter
include BD_validacao
include EntradaProdutos
include EntradaManual


Dado("que o usuário informou todos os dados da entrada e o tipo da nota é {string}") do |tipo_nota|
  efetuarLogin('https://qa.varejonline.com.br:7443/server/erp/estoque/entradas/criar/manual')

  preencherDadosIniciais(tipo_nota)

  #Salvar nos dados iniciais
  sleep 2
  clicarBotaoSalvar

  #Salvar nos dados adicionais
  sleep 1
  clicarBotaoSalvar

  incluirProdutos

  #Salvar nos produtos
  clicarBotaoSalvar

  #Salvar  visão geral
  clicarBotaoSalvar
end

Quando('finalizar a entrada') do
  clicarBotaoFinalizar
  sleep 5
end

Quando("o usu\xC3\xA1rio informar na entrada o tipo de nota {string}") do |tipo_nota|
  efetuarLogin('/server/erp/estoque/entradas/criar/manual')

  preencherDadosIniciais(tipo_nota)
   #Salvar nos dados iniciais
end

Quando('informar chave de acesso') do
  find('input[formcontrolname=chaveNfe]').set '42181275552133001303550040000005621722790267'
  sleep 1
  #Salvar nos dados iniciais
  clicarBotaoSalvar

   #Salvar nos dados adicionais
   sleep 1
   clicarBotaoSalvar

  incluirProdutos
  #Salvar nos produtos
  clicarBotaoSalvar

  #Salvar na visão Geral
  clicarBotaoSalvar
end

Então("a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
  mensagemExibida = find('.noty_body', wait: 20).text
  puts mensagemExibida
  expect('A entrada '+ numeroDocumento+'/'+serie+' foi efetuada com sucesso.').to include mensagemExibida
end


Então("a nota fiscal é registrada com status {string}") do |status_escrituracao|
  expect(status_escrituracao).to have_content consulta_escrituracao
  
end
