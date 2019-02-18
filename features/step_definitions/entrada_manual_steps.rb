include BotoesEntradaNota
include CamposEntradaNota
include EfetuarLogin
include ApertarEnter
include BD_validacao
include EntradaProdutos
include EntradaManual

# resultado_consulta = consulta_banco('select * from QA_VPSA.TERCEIRO', 'QUANTIDADE')
# puts resultado_consulta.to_s + ' registros encontrados.'

# resultado_consultaC = consulta_banco('select * from QA_VPSA.TERCEIRO', 'CONTEUDO')
# resultado_consultaC.exec
# resultado_consultaC.fetch() {|row|
#   print row
# }

Dado("que o usuário informou todos os dados da entrada e o tipo da nota é {string}") do |tipo_nota|
  efetuarLogin('https://erp.varejonline.com.br/server/erp/estoque/entradas/criar/manual')
  # efetuarLogin('https://qa.varejonline.com.br:7443/server/erp/estoque/entradas/criar/manual')

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
  preencherDadosIniciais(tipo_nota)
   #Salvar nos dados iniciais
   sleep 2
   clicarBotaoSalvar
end

Quando('informar chave de acesso') do
  find('input[formcontrolname=serie]').set 0o0000000000000000000000000000000000000000000
  sleep 1
  clicarBotaoSalvar

  incluirProdutos

  clicarBotaoSalvar
  clicarBotaoSalvar
end

Então("a entrada é realizada com sucesso exibindo a mensagem:") do |mensagem|
  mensagemExibida = find('.noty_body', wait: 20).text
  puts mensagemExibida
  expect(mensagem).to include mensagemExibida
  find('.text-center', text: '1010').click
  find('button', text: 'Excluir').click
  find('#noty-ok-button', text: 'Sim').click
end

Então("a nota fiscal é registrada com status {string}") do |_string|
  resultado_consulta = consulta_escriturao
  puts resultado_consulta
end
