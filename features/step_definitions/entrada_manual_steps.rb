class EntradaManual_steps
  def initialize
    botaoSalvar = find('button', text: 'Salvar e continuar').click
    botaoFinalizar = find('button', text: 'Finalizar').click
  end
end
Dado("que o usu\xC3\xA1rio informou todos os dados da entrada e o tipo da nota \xC3\xA9 {string}") do |tipo_nota|
  entrada = EntradaManual.new

  visit 'https://erp.varejonline.com.br/server/erp/estoque/entradas/criar/manual'
  find('input[id$=username]').set 'devcanal'
  find('input[id$=password]').set 'devcanal'
  find('input[id$=cnpj]').set 'vpsa_vpsa_348'
  find('input[id$=login-submit]').click

  fornecedor = find('.select2-search__field').click
  fornecedor.set('ARICANDUVA COMERCIAL ELETRICA')
  sleep 1
  fornecedor.send_keys(:enter)

  entidade = find('.form-group col-sm-6, app-vo-filtro-entidades[formcontrolname=entidade] div', match: :first)
             .find('.select2-selection__placeholder', text: 'Selecione...').click

  find('li', text: 'DEV'.upcase).click

  find('input[formcontrolname=numeroDocumento]').set 1010
  find('input[formcontrolname=serie]').set 1

  operacao = find('app-vo-filtro-operacoes[formcontrolname=operacao]')
             .find('.select2-selection__placeholder', text: 'Selecione...').click
  operacao = find('.select2-search__field').click

  operacao.set('COMPRA P/ COMERCIALIZA')
  sleep 1
  operacao.send_keys(:enter)

  tipoNotaFiscal = find('app-vo-select[formcontrolname=tipoNotaFiscal]')
  .find('.select2-selection__placeholder', text: 'Selecione...').click

    find('.select2-results__option',text:tipo_nota, exact_text: true).click

  sleep 2
  find('button', text: 'Salvar e continuar').click
  sleep 1
  find('button', text: 'Salvar e continuar').click

  produto1 = find('.select2-search__field').click
  produto1.set('PRODUTO DA LETICIA VERMELHO')
  sleep 1

  quantidade = find('input[formcontrolname=quantidade]').set 20_000
  adicionar = find('button', text: 'Adicionar').click
  sleep 1

  produto2 = find('.select2-search__field').click
  produto2.set('PRODUTO DA LETICIA Preto')
  sleep 1

  quantidade = find('input[formcontrolname=quantidade]').set 20_000
  adicionar = find('button', text: 'Adicionar').click
  sleep 1

  find('button', text: 'Salvar e continuar').click
  find('button', text: 'Salvar e continuar').click
end

Quando('finalizar a entrada') do
  find('button', text: 'Finalizar').click
  sleep 5
end

Quando('informar chave de acesso') do
 # find('input[formcontrolname=serie]').set 0o0000000000000000000000000000000000000000000
end

Então("a entrada \xC3\xA9 concluida com a mensagem:") do |mensagem|
  mensagemExibida = find('.noty_body', wait: 20).text
  puts mensagemExibida
  expect(mensagem).to include mensagemExibida
  find('.text-center', text: '1010').click
  excluir = find('button', text: 'Excluir').click
  confirmar = find('#noty-ok-button', text: 'Sim').click
end
