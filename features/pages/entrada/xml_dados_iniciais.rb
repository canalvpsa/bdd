require 'win32ole'


module EntradaXML

    def importarXML(nomeXML, concilia_nota, concilia_sistema)
        
        wsh = WIN32OLE.new('Wscript.Shell')
        find('button', text: 'Importar XML').click;
        find('button', text: 'Escolher arquivo').click
        sleep 2
        caminho = Dir.pwd + '/resources/'+nomeXML+'{ENTER}'
        wsh.SendKeys(caminho.tr!('/', '\\'))
        sleep 2
        conciliacao_nota = concilia_nota(concilia_nota)
        conciliacao_nota.find('.checkmark').click
        
        conciliacao_sistema = concilia_sistema(concilia_sistema)
        conciliacao_sistema.find('.checkmark').click

        find('.modal-footer').find('button', text: 'Importar').click
    end


    def concilia_nota(conciliacao)
        case conciliacao
       
        when 'codigo_produto'
            find('app-vo-radio-group[name=codigoConciliadorXml] label', text: 'Código do produto').click

        when 'codigo_barras'
            find('app-vo-radio-group[name=codigoConciliadorXml] label', text: 'Código de barras').click
        end
    end



    def concilia_sistema(conciliacao)
        
        case conciliacao

        when 'codigo_interno'
            find('app-vo-radio-group[name=codigoConciliadorSistema] label', text: 'Código interno').click
        
         when 'codigo_barras'
            find('app-vo-radio-group[name=codigoConciliadorSistema] label', text: 'Código de barras').click
        end
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