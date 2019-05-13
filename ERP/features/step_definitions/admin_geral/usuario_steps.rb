
Dado("que o usuário acessa o cadastro de usuário do sistema") do
    visit "/server/erp/usuarios/criar"
  end

Dado("que o usuário seleciona o tipo {string} do usuário do sistema") do |tipo_usuario|
    campo_tipo_usuario = find('app-vo-select[formcontrolname=tiposPessoa]')
  end
  
  Dado("informa todos os dados do usuário do sistema") do
    pending # Write code here that turns the phrase above into concrete actions
  end
  
  Quando("finaliza o cadastro") do
    pending # Write code here that turns the phrase above into concrete actions
  end
  
  Então("é exibida a mensagem {string}") do |string|
    pending # Write code here that turns the phrase above into concrete actions
  end
  
  Então("o usuário do sistema é cadastrado") do
    pending # Write code here that turns the phrase above into concrete actions
  end