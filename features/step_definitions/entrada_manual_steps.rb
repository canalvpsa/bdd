include BotoesEntradaNota
include CampoesEntradaNota
include EfetuarLogin;
include ApertarEnter;

Dado("que o usu\xC3\xA1rio informou todos os dados da entrada e o tipo da nota \xC3\xA9 {string}") do |tipo_nota|

  visit 'https://erp.varejonline.com.br/server/erp/estoque/entradas/criar/manual'

  efetuarLogin

  fornecedor = setarPesquisa(abrirCampoPesquisa, 'ARICANDUVA COMERCIAL ELETRICA');
  sleep 3
  apertarEnter(fornecedor)

  abrirEntidades
  setarEntidade('DEV')

  setarNumeroDocumento(1010)
  setarSerieDocuimento(1)

  abrirPesquisaOperacoes
  operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA')

  sleep 1
  operacao.send_keys(:enter)

  abrirPesquisaTipoNota
  setarTipoNota(tipo_nota)

  sleep 2
  clicarBotaoSalvar
  sleep 1
  clicarBotaoSalvar

  setarPesquisa(abrirCampoPesquisa, 'PRODUTO DA LETICIA VERMELHO');
  sleep 1

  setarQuantidade(20_000)
  clicarBotaoAdicionar
  sleep 1

  setarPesquisa(abrirCampoPesquisa, 'PRODUTO DA LETICIA Preto');
  sleep 1

  setarQuantidade(20_000)
  clicarBotaoAdicionar
  sleep 1

  clicarBotaoSalvar
  clicarBotaoSalvar
end

Quando('finalizar a entrada') do
  clicarBotaoFinalizar
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
