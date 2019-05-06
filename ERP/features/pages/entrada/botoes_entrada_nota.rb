module BotoesEntradaNota

    def clicarBotaoSalvar()
        botao = find('button', text: 'Salvar e continuar',wait:100)
        botao.click
    end

    def clicarBotaoFinalizar ()
        botao = find('button', text: 'Finalizar', wait:100)
        botao.click
    end

    def clicarBotaoAdicionar ()
        botao = find('button', text: 'Adicionar')
        botao.click
    end
end