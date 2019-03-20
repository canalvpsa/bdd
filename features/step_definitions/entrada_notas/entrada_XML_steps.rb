include DadosAdicionais
include EfetuarLogin
include EntradaXML
include LinkRotinas
include VisaoGeral

Dado("que o usuário preencheu os dados iniciais informando um XML e a entidade é do sistema de tributacao {string}") do |tributacao|
  efetuarLogin(linkRotina('entrada'))
  importarXML('custoProduto.xml')
  preencherDadosIniciaisXML_entidade(tributacao)

  #Salvar nos dados iniciais
  sleep 2
  clicarBotaoSalvar
end

Dado("que o usuário preencheu os dados iniciais informando um XML") do
  efetuarLogin(linkRotina('entrada'))
  importarXML('CST00_correto.xml')
  preencherDadosIniciaisXML_entidade('real')

  #Salvar nos dados iniciais
  sleep 2
  clicarBotaoSalvar
end

Dado("que confirma a conciliação, confirma os dados adicionais e confirma os produtos") do
  #Salvar na conciliação
  sleep 2
  clicarBotaoSalvar

  #Salvar nos dados adicionais
  sleep 2
  clicarBotaoSalvar

  #Salvar nos produtos
  sleep 2
  clicarBotaoSalvar
end

Dado("que confirma a conciliação") do
  #Salvar na conciliação
  sleep 2
  clicarBotaoSalvar
end


Quando('o usuário preenche os dados iniciais informando um XML') do
  efetuarLogin(linkRotina('entrada'))
  importarXML('42181275552133001303550040000005621722790267.xml')
  preencherDadosIniciaisXML_entidade('real')

  #Salvar nos dados iniciais
  sleep 2
  clicarBotaoSalvar
  
end


Dado("que o usuário preencheu os dados iniciais informando o {string} conciliando na nota {string} e no sistema {string}") do |nomeXML, conciliar_nota, conciliar_sistema|
  efetuarLogin(linkRotina('entrada'))                           
  importarXML(nomeXML, conciliar_nota, conciliar_sistema)          
  preencherDadosIniciaisXML_entidade('real')

  #Salvar nos dados iniciais
  sleep 2
  clicarBotaoSalvar
end  

Dado("que confirma a conciliação, confirma os dados adicionais, confirma os produtos e confirma a visão geral") do
  #Salvar conciliação
  sleep 2
  clicarBotaoSalvar
  
  
  #Salvar nos dados adicionais
  sleep 2
  clicarBotaoSalvar

  #Salvar nos produtos
  sleep 2
  clicarBotaoSalvar

  #Salvar visão geral
  sleep 2
  clicarBotaoSalvar
end


                                                                                                                 
Quando("validar os dados da nota fiscal de entrada") do
  expect(page).to have_content('Atenção')       
end


Então("nenhuma mensagem de divergência de valores deve ser exibida") do
  wait_until_load
  expect(page).not_to have_content('.alert alert-danger') 
end


Então("na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado {string}") do |xml|
  
  case xml

  when 'CST00_1.xml'
    validaCST00_1

  when 'CST00_2.xml'
    validaCST00_2

  when 'CST10_1.xml'
    validaCST10_1

  when 'CST10_2.xml'
    validaCST10_2

  when 'CST51_1.xml', 'CST51_2.xml', 'CST51_3.xml', 'CST51_4.xml'
    validaCST51

  when 'ICMSSN101_1.xml'
    validaCSOSN101

  when 'ICMSSN202_1.xml'
    validaCSOSN202
    
  else
    puts "CST/CSOSN nao previsto"
  end
  
   #Salvar na visão geral
   sleep 2
   clicarBotaoSalvar
end