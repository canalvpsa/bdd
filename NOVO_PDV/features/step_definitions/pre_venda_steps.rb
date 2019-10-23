Dado("que o cliente padrão é o diversos") do
   terceiro = find('app-select-terceiro').find('h2').text
   expect(terceiro). to have_text('Diversos')
  end
  
  Dado("o usuário adicionou um produto na pré venda") do
    produto = find('app-vendivel-grid-item', match: :first, wait:5)
    produto.find('.branco').click
  end
  
  Quando("salvar pré venda") do
    find('button', text: 'Salvar Pré-Venda').click
  end
  
  Então("é exibida a mensagem {string}") do |mensagemEsperada|
    find('.noty_body', wait: 2)
    mensagemExibida = find('.noty_body').text
    expect(mensagemExibida).to have_text(mensagemEsperada)
  end