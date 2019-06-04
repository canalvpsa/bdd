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

    def prosseguirAteFinalizar()
        if page.has_css?("app-vo-entrada-itens") 
            click_button('Salvar e continuar', wait:100)
            wait_until_load
        end

        if page.has_css?("app-vo-entrada-visao-geral") 
            click_button('Salvar e continuar', wait:100)
            wait_until_load
        end

        if page.has_css?("app-vo-entrada-pagamento") 
            click_button('Finalizar', wait:100)
        end
    end
end