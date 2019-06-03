module BotoesEntradaNota

    def clicarBotaoSalvar()
        click_button('Salvar e continuar', wait:100)
    end

    def clicarBotaoFinalizar()
        click_button('Finalizar', wait:100)
    end

    def clicarBotaoAdicionar()
        click_button('Adicionar', wait:100)
    end
end