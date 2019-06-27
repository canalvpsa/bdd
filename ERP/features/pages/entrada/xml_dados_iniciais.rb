require 'win32ole'

module EntradaXML
 
    def importarXML(nomeXML, concilia_nota, concilia_sistema)
        find('button', text: 'Importar XML').click   
        xml = Dir.pwd+'/resources/'+nomeXML

        page.execute_script("$('input[formcontrolname=arquivo]').removeAttr('readonly')")
        selecaoArquivo = find('input[formcontrolname=arquivo]', visible:false).set(xml)
        
        concilia_nota(concilia_nota)
        concilia_sistema(concilia_sistema)

        wait_until_load_campos

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
        setarPesquisa(abrirCampoPesquisa, 'COMPRA P/ COMERCIALIZA', true)
    end
end