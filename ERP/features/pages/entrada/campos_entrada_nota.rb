module CamposEntradaNota

    def setarQuantidade(quantidade)
        find('input[formcontrolname=quantidade]').set quantidade
    end

    def abrirCampoPesquisa()
        campo_pesquisa = find('.select2-search__field')
        campo_pesquisa.click
    end

    def setarPesquisa(campo, texto_pesquisa, enter)
        campo.set texto_pesquisa

        if enter == true
            sleep 3
            campo.send_keys(:enter)
        end
    end


    def abrirEntidades
        find('app-vo-entrada-dados-iniciais app-vo-filtro-entidades[formcontrolname=entidade] div')
        .find('.select2-selection__placeholder', text: 'Selecione...').click
    end

    def abrirFornecedor
        if page.has_css?('.select2-search__field') 
            find('.select2-search__field').click
        else
            find('app-vo-filtro-fornecedores[formcontrolname=fornecedor]').find('.select2-selection__placeholder', text: 'Selecione...').click
            find('.select2-search__field').click
        end
    end

    def setarEntidade(nomeEntidade)
        find('li', text: nomeEntidade.upcase).click
    end

    def setarNumeroDocumento(numDoc)
        find('input[formcontrolname=numeroDocumento]').set numDoc
    end

    def setarSerieDocumento(serDoc)
        find('input[formcontrolname=serie]').set serDoc
    end

    def abrirPesquisaOperacoes
        find('app-vo-filtro-operacoes[formcontrolname=operacao]').find('.select2-selection__placeholder', text: 'Selecione...').click
    end

    def abrirPesquisaTipoNota
        find('app-vo-select[formcontrolname=tipoNotaFiscal]').find('.select2-selection__placeholder', text: 'Selecione...').click
    end

    def setarTipoNota(tipoNota)
        find('.select2-results__option',text:tipoNota, exact_text: true).click
    end

    def setarDataEmissao(data)
      dataEmissao = find('app-vo-data[formcontrolname=dataEmissao]').find('input')
      dataEmissao.send_keys [:control, 'a'], :backspace
      dataEmissao.set data
    end

    def preencherDesconto(desconto)
        find('input[formcontrolname=valorDesconto]').set desconto
      end


    def wait_until_load_page
        Timeout.timeout(1000) do
          loop until page.has_no_css?('.block-ui-wrapper.active')
        end
    end

    def wait_until_load_campos
        Timeout.timeout(1000) do
            loop until page.has_no_css?('.loading-stick')
          end
      end
end