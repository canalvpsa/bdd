
Before do
    visit 'https://pdv.varejonline.com.br/pdv'
    find('input[formcontrolname=login]').set 'pdvnovo'
    find('input[formcontrolname=senha]').set 'varejonline'
    find('button', text: 'Entrar').click
  end