
After ('@entrada') do 
  include BD_validacao
    find('.text-center', text: consulta_ultimaEntrada, match: :prefer_exact).click
    find('button', text: 'Excluir').click
    find('#noty-ok-button', text: 'Sim').click
  end