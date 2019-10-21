include BotoesEntradaNota
include CamposEntradaNota
include ApertarEnter
include BD_validacao
include EntradaProdutos
include EntradaManual
include EntradaXML
include VisaoGeral

Dado("que o usuário inicia uma nova entrada de notas") do
  visit '/server/erp/estoque/entradas/criar/manual'
end

Dado("que o usuário informou todos os dados da entrada e o tipo da nota é {string}") do |tipo_nota|
  preencherDadosIniciais_tiposNota(tipo_nota, 1010, 1)

  # Salvar nos dados iniciais
  sleep 2
  clicarBotao
end

Dado("que os produtos adicionados nas entradas manuais são:") do |table|
  @produto_lista = table.hashes
end

Dado("que confirma os dados adicionais, adiciona os produtos e confirma a visão geral") do
  # Salvar nos dados adicionais
  sleep 2
  clicarBotao

  incluirProdutos(@produto_lista)

  # Salvar nos produtos
  sleep 2
  clicarBotao

  # Salvar visão geral
  sleep 2
  clicarBotao
end

Quando('finaliza a entrada') do
  prosseguirAteFinalizar
end

Então("a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
  mensagemExibida = find('.noty_body', wait: 200).text
  puts mensagemExibida
  find('.noty_body').click
  expect('A entrada ' + numeroDocumento + '/' + serie + ' foi efetuada com sucesso.').to include mensagemExibida
end

Então("a nota fiscal é registrada com status {string}") do |status_escrituracao|
  expect(status_escrituracao).to have_content consulta_escrituracao
end

Quando("o usuário informar na entrada o tipo de nota {string} com número do documento {string} e série {string} informando chave de acesso") do |tipo_nota, doc, serie|
  preencherDadosIniciais_tiposNota(tipo_nota, doc, serie)

  chaveNFe = find('input[formcontrolname=chaveNfe]')
  chaveNFe.set '42181275552133001303550040000005621722790267'
 setarDataEmissao('20122018')

  sleep 1
  # Salvar nos dados iniciais
  clicarBotao
end

Quando("o usuário preenche os dados iniciais informando um XML") do
  importarXML('xml_simples.xml', 'codigo_produto', 'codigo_interno')
  preencherDadosIniciaisXML_entidade('real')

  # Salvar nos dados iniciais
  sleep 2
  clicarBotao
end

Quando("que confirma a conciliação, confirma os dados adicionais, confirma os produtos e confirma a visão geral") do
  # Salvar na conciliação
  sleep 2
  clicarBotao

  # Salvar nos dados adicionais
  sleep 2
  clicarBotao

  # Salvar nos produtos
  clicarBotao

  # Salvar visão geral
  sleep 2
  clicarBotao
end

Dado("que o usuário preencheu os dados iniciais informando o {string} conciliando na nota {string} e no sistema {string}") do |nomeXML, conciliar_nota, conciliar_sistema|
  importarXML(nomeXML, conciliar_nota, conciliar_sistema)
  preencherDadosIniciaisXML_entidade('real')

  # Salvar nos dados iniciais
  sleep 2
  clicarBotao
end

Dado("que confirma a conciliação, confirma os dados adicionais e confirma os produtos") do
  # Salvar na conciliação
  sleep 2
  wait_until_load_page
  find('app-vo-entrada-conciliacao',wait:20)
  clicarBotao

  # Salvar nos dados adicionais
  sleep 2
  wait_until_load_page
  find('app-vo-entrada-valores',wait:10)
  clicarBotao

  # Salvar nos produtos
  sleep 2
  wait_until_load_page
  find('app-vo-wizard-step app-vo-entrada-itens',wait:10)
  clicarBotao
end

Quando('validar os dados da nota fiscal de entrada') do
  wait_until_load_page
  @alertas_visao_geral = find('app-vo-entrada-visao-geral')
  expect(@alertas_visao_geral).to have_selector('.text-center', text: 'Atenção')
end

Então("nenhuma mensagem de divergência de valores deve ser exibida") do
  wait_until_load_page
  expect(@alertas_visao_geral).not_to have_selector('.alert alert-danger, p', text: 'Totalizador e somatório dos itens estão com valores divergentes')
end

Então("na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado {string}") do |xml|
  validaXML(xml)

end

Então("ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
  steps %(
      Quando finaliza a entrada
      Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "#{numeroDocumento}" e série "#{serie}"
  )
end

Dado("que o parâmetro de custo do ERP está configurado para {int} casas decimais") do |qtde_decimais|
  parametro_decimais_custo(qtde_decimais)
end

Então("os centavos divergentes serão distribuídos entre os itens da nota") do
end

Então("na visão geral o valor total dos produtos é {string}") do |total_produtos|
  valida_total_produtos(total_produtos)
end

Então("na visão geral o valor total da nota fiscal é {string}") do |total_NF|
  valida_total_NF(total_NF)
end

Então("na visão geral o valor total do desconto é {string}") do |desconto|
  valida_total_desconto(desconto)
end

Dado('que informa desconto de {string} nos dados adicionais') do |desconto|
  preencherDesconto(desconto)
end

Dado('que confirma os dados adicionais') do
  # Salvar nos dados adicionais
  sleep 2
  clicarBotao
end

Dado('adiciona os produtos') do
  incluirProdutos(@produto_lista)

  # Salvar nos produtos
  sleep 2
 clicarBotao
end


Dado("que na conciliação um item do {string} é conciliado com vários do sistema") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end

Dado("que na conciliação vários itens do {string} são conciliados com o mesmo item do sistema") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end


Dado("que o usuário informou todos os dados da entrada e a operação da nota é {string}") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end

Quando("validar os dados financeiros da entrada de notas") do
  pending # Write code here that turns the phrase above into concrete actions
end

Então("nenhum valor é exibido") do
  pending # Write code here that turns the phrase above into concrete actions
end

Dado("que o plano de pagamento de compras padrão é {string}") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end

Dado("que o parâmetro que permite editar o valor da provisão em entrada de notas XML está {string}") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end

Então("o plano pagamento exibido é {string}") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end

Então("o valor total {string} alteração") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end

Dado("que existe um pedido de compra em aberto com plano de pagamento {string}") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end

Dado("que o usuário associou o pedido de compra na entrada manual") do
  pending # Write code here that turns the phrase above into concrete actions
end

Dado("que o usuário preencheu os demais campos da entrada e a operação da nota é {string}") do |string|
  pending # Write code here that turns the phrase above into concrete actions
end

Dado("que o usuário preencheu os dados iniciais informando o {string} e a operação {string}") do |string, string2|
  pending # Write code here that turns the phrase above into concrete actions
end

Então("as parcelas são exibidas conforme o XML") do
  pending # Write code here that turns the phrase above into concrete actions
end