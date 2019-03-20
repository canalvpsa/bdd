module VisaoGeral

   @validarTotalizadores

   def coletarDados()
      totalizadoresNota = find('.row, app-vo-entrada-visao-geral-edicao div', text: 'BC ICMS', match: :first).text
      #puts 'Totalizadores da visão geral:'"\n" + totalizadoresNota
      totalizadoresNota = totalizadoresNota.tr("\n"," ")
      return totalizadoresNota+"\n\n"
   end

   def validarCampos(campo, valor)
      case campo

      when 'base_icms'
         expect(@validarTotalizadores).to include('BC ICMS R$ '+valor)
      
      when 'valor_icms'
          expect(@validarTotalizadores).to include('Valor ICMS R$ '+valor)
      
      when 'base_icms_st'
         expect(@validarTotalizadores).to include('BC ICMS ST R$ '+valor)
      
      when 'valor_icms_st'
         expect(@validarTotalizadores).to include('Valor ICMS ST R$ '+valor)

      when 'base_icms_sn'
         expect(@validarTotalizadores).to include('BC ICMS SN R$ '+valor)
      
      when 'valor_icms_sn'
         expect(@validarTotalizadores).to include('Valor ICMS SN R$ '+valor)
      
      when 'total_produto'
         expect(@validarTotalizadores).to include('Valor total produtos R$ '+valor)

      when 'total_nota'
         expect(@validarTotalizadores).to include('Valor total nota R$ '+valor)

      when 'custo_adicional'
         expect(@validarTotalizadores).to include('Valor custo adicional R$ '+valor)
      
      else
         puts "Campo para validacao nao previsto"
       end
   end


   def valida_total_produtos(total_produtos)
      @validarTotalizadores = coletarDados
      validarCampos('total_produto', total_produtos)
   end


   def validaCST00_1
     @validarTotalizadores = coletarDados
      validarCampos('base_icms','8,73')
      validarCampos('valor_icms','1,05')
      validarCampos('base_icms_st','0,00')
      validarCampos('valor_icms_st','0,00')
      validarCampos('total_produto','8,73')
      validarCampos('total_nota','8,80')
   end

   def validaCST00_2
      @validarTotalizadores = coletarDados
      validarCampos('base_icms','0,00')
      validarCampos('valor_icms','0,00')
      validarCampos('base_icms_st','0,00')
      validarCampos('valor_icms_st','0,00')
      validarCampos('total_produto','8,73')
      validarCampos('total_nota','8,80')
   end

   def validaCST10_1
      @validarTotalizadores = coletarDados
      validarCampos('base_icms','8,73')
      validarCampos('valor_icms','0,61')
      validarCampos('base_icms_st','11,35')
      validarCampos('valor_icms_st','0,18')
      validarCampos('total_produto','8,73')
      validarCampos('total_nota','8,91')
   end

   def validaCST10_2
      @validarTotalizadores = coletarDados
      validarCampos('base_icms','8,73')
      validarCampos('valor_icms','0,61')
      validarCampos('base_icms_st','0,00')
      validarCampos('valor_icms_st','0,00')
      validarCampos('total_produto','8,73')
      validarCampos('total_nota','8,73')
   end

   def validaCST51
      @validarTotalizadores = coletarDados
      validarCampos('base_icms','0,00')
      validarCampos('valor_icms','0,00')
      validarCampos('base_icms_st','0,00')
      validarCampos('valor_icms_st','0,00')
      validarCampos('total_produto','8,73')
      validarCampos('total_nota','8,73')
   end

   def validaCST51_diferimento
      @validarTotalizadores = coletarDados
      validarCampos('base_icms','8.73')
      validarCampos('valor_icms','0.79')
      validarCampos('base_icms_st','0.00')
      validarCampos('valor_icms_st','0.00')
      validarCampos('total_produto','8,73')
      validarCampos('total_nota','8,73')
   end

   def validaCSOSN101
      @validarTotalizadores = coletarDados
      validarCampos('base_icms','0,00')
      validarCampos('valor_icms','0,00')
      validarCampos('base_icms_st','0,00')
      validarCampos('valor_icms_st','0,00')
      validarCampos('base_icms_sn','26,52')
      validarCampos('valor_icms_sn','5,04')
      validarCampos('total_produto','26,52')
      validarCampos('total_nota','26,52')
   end

   def validaCSOSN202
      @validarTotalizadores = coletarDados
      validarCampos('base_icms','0,00')
      validarCampos('valor_icms','0,00')
      validarCampos('base_icms_st','22,60')
      validarCampos('valor_icms_st','1,71')
      validarCampos('total_produto','17,79')
      validarCampos('total_nota','19,50')
   end

   def validaCustoAdicional(valor)
      @validarTotalizadores = coletarDados
      validarCampos('custo_adicional',valor)
   end
end