class XMLConciliacao
     
    def conciliarItens(produto_conciliacao)
        find('app-vo-filtro-produtos app-vo-select').find('.select2-selection__placeholder', text: 'Selecione...').click

        produto_conciliacao.each do |p|
            campo = find('.select2-search').find('.select2-search__field')
            campo.set p["nome_produto"]
            find('li', text: p["nome_produto"], match: :prefer_exact).click

            qtde = find('app-vo-entrada-conciliacao').find('table tbody tr', text: p["nome_produto"])
            qtdenova = qtde.all('td')[3].find('.form-control')
            qtdenova.click
            qtdenova.send_keys [:control, 'a'], :backspace
            qtdenova.set p["quantidade"]
            qtdenova.send_keys :tab
            sleep 1
        end
    end
end