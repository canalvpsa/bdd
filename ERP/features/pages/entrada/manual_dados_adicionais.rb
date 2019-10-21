include BotoesEntradaNota
include CamposEntradaNota

module EntradaManual
    
    def preencherCustoAdicional(valor)
        find('input[formcontrolname=valorCustoAdicional]').set valor
    end
end