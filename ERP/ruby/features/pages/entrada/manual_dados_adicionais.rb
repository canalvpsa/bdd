#include BotoesEntradaNota
#include CamposEntradaNota

class EntradaManual_adicionais
    include Capybara::DSL
    
    def preencherCustoAdicional(valor)
        find('input[formcontrolname=valorCustoAdicional]').set valor
    end
end