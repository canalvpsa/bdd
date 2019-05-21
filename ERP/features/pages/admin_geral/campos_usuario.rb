module CamposUsuario


    def abrirCampoPesquisa()
        campo_pesquisa = find('.select2-search__field')
        campo_pesquisa.click
    end

    def setarPesquisa(campo, texto_pesquisa, enter)
        campo.set texto_pesquisa

        if enter == true
            sleep 1
            campo.send_keys(:enter)
        end
    end

    def setarDataNascimento(data)
    dataNascimento = find('app-vo-data[formcontrolname=dataNascimento]').find('input')
    dataNascimento.send_keys [:control, 'a'], :backspace
    dataNascimento.set data
    end
    
end

