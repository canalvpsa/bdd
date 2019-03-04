include BotoesEntradaNota
include CamposEntradaNota
include EfetuarLogin
include ApertarEnter
include BD_validacao
include EntradaProdutos
include EntradaManual


Dado("que o usuário informou todos os dados da entrada e o tipo da nota é {string}") do |tipo_nota|
  efetuarLogin(linkRotina('entrada'))

  preencherDadosIniciais_tiposNota(tipo_nota)

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

Dado("que o usuário preencheu manualmente os dados iniciais da entrada e a entidade é do sistema de tributacao {string}") do |tributacaoEntidade|

  efetuarLogin(linkRotina('entrada'))

  preencherDadosIniciais_tiposEntidade(tributacaoEntidade)

  #Salvar nos dados iniciais
  sleep 2
  clicarBotaoSalvar
end

Quando("inclui os produtos para compra") do                                      
  incluirProdutos

  #Salvar nos produtos
  clicarBotaoSalvar
end                                                                              

Quando("o usuário informar na entrada o tipo de nota {string}") do |tipo_nota|
  efetuarLogin(linkRotina('entrada'))

  preencherDadosIniciais_tiposNota(tipo_nota)
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