module VisaoGeral

   @validarTotalizadores

   def coletarDados()
      totalizadoresNota = find('.row, app-vo-entrada-visao-geral-edicao div', text: 'BC ICMS', match: :first).text
      #puts 'Totalizadores da visão geral:'"\n" + totalizadoresNota
      totalizadoresNota = totalizadoresNota.tr("\n"," ")
      return totalizadoresNota+"\n\n"
   end

   def validaXML(xml)
      @validarTotalizadores = coletarDados
      file = YAML.load_file(File.join(Dir.pwd, 'features/support/fixtures/imposto.yaml'))
      imposto = file[xml]
      #puts imposto

      expect(@validarTotalizadores). to have_text('BC ICMS R$ '+imposto["bICMS"])
      expect(@validarTotalizadores). to have_text('Valor ICMS R$ '+imposto["vICMS"])
      expect(@validarTotalizadores). to have_text('BC ICMS ST R$ '+imposto["bICMSST"])
      expect(@validarTotalizadores). to have_text('Valor ICMS ST R$ '+imposto["vICMSST"])
      if !imposto["bICMSSN"].nil?
         expect(@validarTotalizadores). to have_text('BC ICMS SN R$ '+imposto["bICMSSN"])
         expect(@validarTotalizadores). to have_text('Valor ICMS SN R$ '+imposto["vICMSSN"])
      end
      expect(@validarTotalizadores). to have_text('Valor IPI R$ '+imposto["vIPI"])
      expect(@validarTotalizadores). to have_text('Valor total produtos R$ '+imposto["total_produto"])
      expect(@validarTotalizadores). to have_text('Valor total nota R$ '+imposto["total_NF"])
   end


   def valida_total_produtos(total_produtos)
      expect(coletarDados). to have_text('Valor total produtos R$ '+total_produtos)
   end

   def valida_total_desconto(total_desconto)
      expect(coletarDados). to have_text('Valor desconto R$ '+total_desconto)
   end

   def valida_total_NF(total_NF)
      expect(coletarDados). to have_text('Valor total nota R$ '+total_NF)
   end

   def validaCustoAdicional(valor)
      expect(coletarDados). to have_text('Valor custo adicional R$ '+valor)
   end
end