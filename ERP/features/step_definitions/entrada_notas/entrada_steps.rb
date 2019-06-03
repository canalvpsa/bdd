include BotoesEntradaNota
include CamposEntradaNota
include ApertarEnter
include BD_validacao
include EntradaProdutos
include EntradaManual
include EntradaXML
include VisaoGeral

Dado("que o usuário inicia uma nova entrada de notas") do
    visit "/server/erp/estoque/entradas/criar/manual"
end
  
  
Dado("que o usuário informou todos os dados da entrada e o tipo da nota é {string}") do |tipo_nota|
   preencherDadosIniciais_tiposNota(tipo_nota, 1010, 1)
  
    #Salvar nos dados iniciais
    sleep 2
    clicarBotaoSalvar
end
  
Dado("que os produtos adicionados nas entradas manuais são:") do |table|
    @produto_lista = table.hashes 
end

  
Dado("que confirma os dados adicionais, adiciona os produtos e confirma a visão geral") do
    #Salvar nos dados adicionais
    sleep 2
    clicarBotaoSalvar
 
    incluirProdutos(@produto_lista)
 
   #Salvar nos produtos
   sleep 2
   clicarBotaoSalvar
 
   #Salvar visão geral
   sleep 2
   clicarBotaoSalvar
end

  
Quando("finaliza a entrada") do
  clicarBotaoFinalizar
end
  
Então("a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
    mensagemExibida = find('.noty_body', wait: 200).text
    puts mensagemExibida
    expect('A entrada '+ numeroDocumento+'/'+serie+' foi efetuada com sucesso.').to include mensagemExibida
end
  
Então("a nota fiscal é registrada com status {string}") do |status_escrituracao|
    expect(status_escrituracao).to have_content consulta_escrituracao
end
  
Quando("o usuário informar na entrada o tipo de nota {string} com número do documento {string} e série {string} informando chave de acesso") do |tipo_nota, doc, serie|
    preencherDadosIniciais_tiposNota(tipo_nota, doc, serie)
    
    find('input[formcontrolname=chaveNfe]').set '42181275552133001303550040000005621722790267'
    setarDataEmissao('20122018')
    
    sleep 1
    #Salvar nos dados iniciais
    clicarBotaoSalvar
end
  
Quando("o usuário preenche os dados iniciais informando um XML") do
    importarXML('42181275552133001303550040000005621722790267.xml', 'codigo_produto', 'codigo_interno')
    preencherDadosIniciaisXML_entidade('real')

    #Salvar nos dados iniciais
    sleep 2
    clicarBotaoSalvar
end
  
Quando("que confirma a conciliação, confirma os dados adicionais, confirma os produtos e confirma a visão geral") do
   #Salvar na conciliação
   sleep 2
   clicarBotaoSalvar
  
    #Salvar nos dados adicionais
    sleep 2
    clicarBotaoSalvar
 
   #Salvar nos produtos
   clicarBotaoSalvar
 
   #Salvar visão geral
   sleep 2
   clicarBotaoSalvar
end
  

Dado("que o usuário preencheu os dados iniciais informando o {string} conciliando na nota {string} e no sistema {string}") do |nomeXML, conciliar_nota, conciliar_sistema|
    importarXML(nomeXML, conciliar_nota, conciliar_sistema)          
    preencherDadosIniciaisXML_entidade('real')
  
    #Salvar nos dados iniciais
    sleep 2
   clicarBotaoSalvar
  end

  
  Dado("que confirma a conciliação, confirma os dados adicionais e confirma os produtos") do
    #Salvar na conciliação
    sleep 2
    clicarBotaoSalvar

    #Salvar nos dados adicionais
    sleep 2
    clicarBotaoSalvar

    #Salvar nos produtos
    sleep 2
    clicarBotaoSalvar
end
  

  Quando("validar os dados da nota fiscal de entrada") do
    wait_until_load
    @alertas_visao_geral = find('app-vo-entrada-visao-geral')
    expect(@alertas_visao_geral).to have_selector('.text-center', text: 'Atenção')
  end
  

  Então("nenhuma mensagem de divergência de valores deve ser exibida") do
    wait_until_load
    expect(@alertas_visao_geral).not_to have_selector('.alert alert-danger, p', text: 'Totalizador e somatório dos itens estão com valores divergentes') 
  end
  

  Então("na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado {string}") do |xml|
    case xml

    when 'CST00_com_ICMS.xml'
      validaCST00_1
  
    when 'CST00_zerado_comBC.xml'
      validaCST00_2
  
    when 'CST10_com_ICMSST.xml'
      validaCST10_1
  
    when 'CST10_zerado_com_BCST.xml'
      validaCST10_2
  
    when 'CST30_desoneracao.xml'
      validaCST30_deson
  
    when 'CST40_deson_n_subtrai.xml'
      validaCST40_deson_n_subtrai
    
    when 'CST40_deson_subtrai.xml'
      validaCST40_deson_subtrai
  
    when 'CST51_zerado.xml', 'CST51_zerado_comBC.xml', 'CST51_sem_tags.xml', 'CST51_dif_sem_vICMS.xml'
      validaCST51
  
    when 'CST51_dif.xml'
      validaCST51_diferimento
  
  
    when 'ICMSSN101_com_ICMSSN.xml'
      validaCSOSN101
  
    when 'ICMSSN202_com_ICMSST.xml'
      validaCSOSN202
      
    else
      puts "CST/CSOSN nao previsto"
    end
    
     #Salvar na visão geral
     sleep 2
     clicarBotaoSalvar
  end
  
  Então("ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento {string} e série {string}") do |numeroDocumento, serie|
    
    steps %{
        Quando finaliza a entrada
        Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "#{numeroDocumento}" e série "#{serie}"
    }
  end

  Dado("que o parâmetro de custo do ERP está configurado para {int} casas decimais") do |qtde_decimais|
    parametro_decimais_custo(qtde_decimais)
  end
  
  Então("os centavos divergentes serão distribuídos entre os itens da nota") do
  end
  
  Então("na visão geral o valor total dos produtos é {string}") do |total_produtos|
    valida_total_produtos(total_produtos)

    #Salvar na visão geral
    clicarBotaoSalvar
  end

  
Então("na visão geral o valor total da nota fiscal é {string}") do |total_NF|
  valida_total_NF(total_NF)

   #Salvar na visão geral
   clicarBotaoSalvar
end


Dado("que informa desconto de {string} nos dados adicionais") do |desconto|
  setarDesconto(desconto)
end

Dado("que confirma os dados adicionais") do
  #Salvar nos dados adicionais
  sleep 2
  clicarBotaoSalvar
end

Dado("adiciona os produtos") do
  incluirProdutos(@produto_lista)
 
   #Salvar nos produtos
   sleep 2
   clicarBotaoSalvar
end