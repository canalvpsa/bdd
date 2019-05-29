include CamposUsuario

Dado("que o usuário acessa o cadastro de usuário do sistema") do
    visit "/server/erp/usuarios/criar"
  end

Dado("que o usuário seleciona o tipo {string} do usuário do sistema") do |tipo_usuario|
    campo_tipo = find('app-vo-select[formcontrolname=tiposPessoa]').find('.select2-selection__rendered', text: 'Pessoa Física Nacional').click
    setar_campo_tipo = find('li', text:tipo_usuario).click
    
  end
  
  Dado("informa todos os dados do usuário do sistema") do
    find('input[formcontrolname=nome]').set 'Maria Ana'
    find('input[formcontrolname=email').set 'maria.ana@varejo.com'
    find('input[formcontrolname=login').set 'mariaana'
    setarDataNascimento('30031992')
    find('input[formcontrolname=documentoTerceiro').set '97405334008'
    campo_permissao = find('app-vo-select[formcontrolname=permissao]').find('.select2-selection__placeholder', text: 'Selecione...').click
    setar_campo_permissao = find('li', text: 'ADMINISTRADOR').click

    campo_entidade = find('app-vo-filtro-entidades[formcontrolname=entidadesLiberadas]').find('.select2-search__field',  match: :first).click
    setar_campo_entidade = find('li',text:'Homologação' ).click
    find('input[formcontrolname=senha').set 'varejonline'
    find('input[formcontrolname=confirmarSenha').set 'varejonline'

  end
  
  Quando("finaliza o cadastro") do
  botao=find('button', text: 'Salvar', wait: 5)
  botao.click
  end
  
  Então("é exibida a mensagem {string}") do |mensagem_esperada|
    mensagemExibida = find('.noty_body', wait: 3).text
    puts mensagemExibida
    expect(mensagem_esperada).to include mensagemExibida
  end
  