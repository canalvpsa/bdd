module BotoesEntradaNota

    def clicarBotao()
        botao = find('.wizard-button-next', wait:20)
        Timeout.timeout(1000) do
            loop until botao.has_no_css?('.wizard-button-next:disabled')
        end
         
        botao.click
        expect(page).not_to have_css('.noty_type__error', wait: 3)
    end


    def clicarBotaoAdicionar()
        click_button('Adicionar', wait:5)
        expect(page).not_to have_css('.noty_type__error', wait: 3)
    end
    

    def prosseguirAteFinalizar()
        paginaAtiva = find('.tab-pane.active')
    
        #produtos
        if paginaAtiva.has_css?("app-vo-wizard-step app-vo-entrada-itens", wait: 0.5) 
            paginaAtiva.execute_script ("window.scrollTo(0,0)")
            clicarBotao
            wait_until_load_page
        end


        #visao geral
        if paginaAtiva.has_css?("app-vo-entrada-visao-geral", wait: 0.5) 
            clicarBotao
            wait_until_load_page
        end

        #pagamento
        if paginaAtiva.has_css?("app-vo-entrada-pagamento", wait: 0.5) 
            clicarBotao
            wait_until_load_page
        end
    end
end