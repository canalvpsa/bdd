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


Dado("que o usuário preencheu os dados iniciais informando o {string}") do |nomeXML|    
  efetuarLogin(linkRotina('entrada'))                           
  importarXML(nomeXML)          
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
  expect(page).not_to have_content('Totalizador e somatório dos itens estão com valores divergentes') 
end

Então("na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado {string}") do |xml|
  
  case xml

  when 'CST00_correto.xml'
    validaCST00_correto

  when 'CST00_incorreto.xml'
    validaCST00_incorreto

  when 'CST10_correto.xml'
    validaCST10_correto

  when 'CST10_incorreto.xml'
    validaCST10_incorreto

  when 'CST51_correto.xml', 'CST51_incorreto.xml'
    validaCST51

  when 'ICMSSN202_correto.xml'
    validaCSOSN202
    
  else
    puts "CST/CSOSN nao previsto"
  end
  
   #Salvar na visão geral
   sleep 2
   clicarBotaoSalvar
end