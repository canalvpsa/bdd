module BotoesEntradaNota

    def clicarBotao()
        botao = find('.wizard-button-next', wait:100)
        wait_until_load_button
        botao.click
    end

    def clicarBotaoAdicionar()
        click_button('Adicionar', wait:5)
    end
    

    def prosseguirAteFinalizar()
        #produtos
        if page.has_css?("app-vo-wizard-step app-vo-entrada-itens") 
            page.execute_script ("window.scrollTo(0,0)")
            clicarBotao
            wait_until_load_page
        end

        #visao geral
        if page.has_css?("app-vo-entrada-visao-geral") 
            clicarBotao
            wait_until_load_page
        end

        #pagamento
        if page.has_css?("app-vo-entrada-pagamento") 
            clicarBotao
        end
    end
end