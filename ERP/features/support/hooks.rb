# frozen_string_literal: true

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
  if page.has_css?('#pushActionRefuse, a', text: 'Não, Obrigado'.upcase, wait: 5)
    modal = find('#pushActionRefuse, a', text: 'Não, Obrigado'.upcase, wait: 5).click
  end
end

After('@seminovo', '@troca_nacional') do
  pending
end

After ('@entrada') do
  include BD_validacao
  if page.has_css?('app-vo-filter-container', wait: 10)
    find('.text-center', text: consulta_ultimaEntrada, match: :prefer_exact).click
    find('button', text: 'Excluir').click
    find('#noty-ok-button', text: 'Sim').click
  end
end

After ('@usuario') do
  include BD_validacao
  deletar_ultimo_usuario
end

After do |scenario|
  if scenario.failed?
    data = Time.now.strftime '%d-%m-%y-%H_%M_%S'
    page.current_window.maximize
    page.execute_script("document.body.style.zoom = '70%'")
    shot_file = page.save_screenshot('log/screenshot_' + data.to_s + '.png')
    puts 'log/screenshot_' + data.to_s + '.png'
    shot_b64 = Base64.encode64(File.open(shot_file, 'rb').read)
    embed(shot_b64, 'image/png', 'Screenshot') # Cucumber anexa o screenshot no report
    page.current_window.resize_to(1280, 800)
    page.execute_script("document.body.style.zoom = '100%'")
  end
end
