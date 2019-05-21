
Before ('@entrada') do 
  realizar_Login('/server/erp/estoque/entradas')
end


Before ('@usuario') do 
  realizar_Login('/server/erp/usuarios')
end

def realizar_Login(rotina)
  visit rotina
  find('input[id$=username]').set 'admin'
  find('input[id$=password]').set 'varejonline'
  find('input[id$=cnpj]').set 'qa_vpsa_aut'
  find('button', text: 'Acessar').click
  mensagemBeamer
end

def mensagemBeamer
  if  page.has_css?('#pushActionRefuse, a', text: 'Não, Obrigado'.upcase, wait:5) 
  modal = find('#pushActionRefuse, a', text: 'Não, Obrigado'.upcase, wait:5).click
  end
end

After ('@entrada') do 
  include BD_validacao
    find('.text-center', text: consulta_ultimaEntrada, match: :prefer_exact).click
    find('button', text: 'Excluir').click
    find('#noty-ok-button', text: 'Sim').click
  end


  After ('@usuario') do 
    include BD_validacao
    deletar_ultimo_usuario
    end


  After do |scenario|
    if scenario.failed?
      shot_file = page.save_screenshot("log/screenshot.png")
      shot_b64 = Base64.encode64(File.open(shot_file, "rb").read)
      embed(shot_b64, "image/png", "Screenshot") # Cucumber anexa o screenshot no report
    end
  end


  #rake specs ["-t @smoke"]