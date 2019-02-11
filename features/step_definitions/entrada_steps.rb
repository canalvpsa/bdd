class EntradaManual
     def botoes
    botaoSalvar = find('button', text: 'Salvar e continuar').click
    botaoFinalizar = find('button', text: 'Finalizar').click
  end

  def manual_dadosIniciais
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

    find('.select2-results__option', text: tipo_nota, exact_text: true).click

    sleep 1
  end

  def manual_produtos
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
  end
end
