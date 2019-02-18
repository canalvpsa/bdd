require 'win32ole'
wsh = WIN32OLE.new('Wscript.Shell')

Quando('preenche os dados iniciais informando um XML') do
  find('button', text: ' Importar XML ').click
  find('button', text: 'Escolher arquivo').click
  sleep 2
  caminho = Dir.pwd + '/features/arquivosAuxiliares/42181275552133001303550040000005621722790267.xml{ENTER}'
  wsh.SendKeys(caminho.tr!('/', '\\'))
  sleep 2
  find("input[value='CODIGO_PRODUTO']").click
  find("input[value='CODIGO_INTERNO']").click
  find('.modal-footer').find('button', text: 'Importar').click
  preencherDadosIniciaisXML
  
   #Salvar nos dados iniciais
   sleep 2
   clicarBotaoSalvar
end
