module BotoesEntradaNota

    def clicarBotaoSalvar()
        find('button', text: 'Salvar e continuar').click
    end

    def clicarBotaoFinalizar ()
        find('button', text: 'Finalizar').click
    end

    def clicarBotaoAdicionar ()
        find('button', text: 'Adicionar').click
    end
end