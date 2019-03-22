include BotoesEntradaNota
include CamposEntradaNota

module DadosAdicionais

    def preencherCustoAdicional(valor)
        find('input[formcontrolname=valorCustoAdicional]').set valor
    end
end