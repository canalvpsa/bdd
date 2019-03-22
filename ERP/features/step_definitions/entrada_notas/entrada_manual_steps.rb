include BotoesEntradaNota
include CamposEntradaNota
include EfetuarLogin
include ApertarEnter
include BD_validacao
include EntradaProdutos
include EntradaManual

Dado("que confirma os dados adicionais, adiciona os produtos e confirma a visão geral") do
  
   #Salvar nos dados adicionais
   sleep 2
  clicarBotaoSalvar

  incluirProdutos

  #Salvar nos produtos
  sleep 2
  clicarBotaoSalvar

  #Salvar visão geral
  sleep 2
  clicarBotaoSalvar
end

Dado("que o usuário informou todos os dados da entrada e o tipo da nota é {string}") do |tipo_nota|
  efetuarLogin(linkRotina('entrada'))

  preencherDadosIniciais_tiposNota(tipo_nota, 1010, 1)

  #Salvar nos dados iniciais
  sleep 2
  clicarBotaoSalvar
end

Dado("que o usuário preencheu manualmente os dados iniciais da entrada e a entidade é do sistema de tributacao {string}") do |tributacaoEntidade|

  efetuarLogin(linkRotina('entrada'))

  preencherDadosIniciais_tiposEntidade(tributacaoEntidade)

  #Salvar nos dados iniciais
  sleep 2
  clicarBotaoSalvar
end

Quando("adiciona e confirma os produtos") do                                      
  incluirProdutos

  #Salvar nos produtos
  sleep 2
  clicarBotaoSalvar
end                                                                              

Quando("o usuário informar na entrada o tipo de nota {string} com número do documento {string} e série {string} informando chave de acesso") do |tipo_nota, doc, serie|
  efetuarLogin(linkRotina('entrada'))

  preencherDadosIniciais_tiposNota(tipo_nota, doc, serie)
  
  find('input[formcontrolname=chaveNfe]').set '42181275552133001303550040000005621722790267'
  setarDataEmissao('20122018')
  
  sleep 1
  #Salvar nos dados iniciais
  clicarBotaoSalvar
end