module VisaoGeral

   def coletarDados()
      totalizadoresNota = find('.row, app-vo-entrada-visao-geral-edicao div', text: 'BC ICMS', match: :first).text
      puts 'Totalizadores da visão geral:'"\n" + totalizadoresNota
      totalizadoresNota = totalizadoresNota.tr("\n"," ")
      return totalizadoresNota
   end

   def validaCSOSN202
      validarTotalizadores = coletarDados
      expect(validarTotalizadores).to include('BC ICMS R$ 0,00')
      expect(validarTotalizadores).to include('Valor ICMS R$ 0,00')
      expect(validarTotalizadores).to include('BC ICM ST R$ 22,60')
      expect(validarTotalizadores).to include('Valor ICMS ST R$ 1,71')
      expect(validarTotalizadores).to include('Valor total produtos R$ 17,79')
      expect(validarTotalizadores).to include('Valor total nota R$ 19,50')
   end

   def validaCST10_correto
      validarTotalizadores = coletarDados
      expect(validarTotalizadores).to include('BC ICMS R$ 8,73')
      expect(validarTotalizadores).to include('Valor ICMS R$ 0,61')
      expect(validarTotalizadores).to include('BC ICM ST R$ 11,35')
      expect(validarTotalizadores).to include('Valor ICMS ST R$ 0,18')
      expect(validarTotalizadores).to include('Valor total produtos R$ 8,73')
      expect(validarTotalizadores).to include('Valor total nota R$ 8,91')
   end

   def validaCST10_incorreto
      validarTotalizadores = coletarDados
      expect(validarTotalizadores).to include('BC ICMS R$ 8,73')
      expect(validarTotalizadores).to include('Valor ICMS R$ 0,61')
      expect(validarTotalizadores).to include('BC ICM ST R$ 11,35')
      expect(validarTotalizadores).to include('Valor ICMS ST R$ 0,00')
      expect(validarTotalizadores).to include('Valor total produtos R$ 8.73')
      expect(validarTotalizadores).to include('Valor total nota R$ 8,91')
   end

   def validaCST51_correto
      validarTotalizadores = coletarDados
      expect(validarTotalizadores).to include('BC ICMS R$ 0,00')
      expect(validarTotalizadores).to include('Valor ICMS R$ 0,00')
      expect(validarTotalizadores).to include('BC ICM ST R$ 0,00')
      expect(validarTotalizadores).to include('Valor ICMS ST R$ 0,00')
      expect(validarTotalizadores).to include('Valor total produtos R$ 8.73')
      expect(validarTotalizadores).to include('Valor total nota R$ 8.73')
   end
end