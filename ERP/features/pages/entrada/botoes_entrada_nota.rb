module BotoesEntradaNota

    def clicarBotaoSalvar()
        botao = find('button', text: 'Salvar e continuar')
        botao.click
    end

    def clicarBotaoFinalizar ()
        botao = find('button', text: 'Finalizar')
        botao.click
    end

    def clicarBotaoAdicionar ()
        botao = find('button', text: 'Adicionar')
        botao.click
    end
end