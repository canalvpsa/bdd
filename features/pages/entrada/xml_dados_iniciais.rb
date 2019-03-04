require 'win32ole'


module EntradaXML

    def importarXML(nomeXML)
        
        wsh = WIN32OLE.new('Wscript.Shell')
        find('button', text: 'Importar XML').click;
        find('button', text: 'Escolher arquivo').click
        sleep 2
        caminho = Dir.pwd + '/resources/'+nomeXML+'{ENTER}'
        wsh.SendKeys(caminho.tr!('/', '\\'))
        sleep 2
        find("input[value='CODIGO_PRODUTO']").click
        find("input[value='CODIGO_INTERNO']").click
        find('.modal-footer').find('button', text: 'Importar').click
    end


    def preencherDadosIniciaisXML_entidade(entidade)   
        case entidade
        when 'real'
            entidade = 'ENTIDADE SÃO PAULO - LUCRO REAL'

        when 'presumido'
            entidade = 'ENTIDADE SÃO PAULO - LUCRO PRESUMIDO'

        when 'simples'
            entidade = 'ENTIDADE SÃO PAULO - SIMPLES'
        end

        abrirEntidades
        setarEntidade(entidade)
      
        abrirPesquisaOperacoes
        operacao = setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA')
      
        sleep 1
        operacao.send_keys(:enter)
    end
end