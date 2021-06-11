# language: pt

@PDV
Funcionalidade: PDV - Compra no PDV

Esquema do Cenário: Compra no PDV
  Quando o usuário acessa a compra no PDV
  Então é necessário escolher a "operacao" que deseja realizar
	E vincular um terceiro com documento válido
  |operacao|
  |seminovo|
  |troca_nacional|


Cenário: Compra para o terceiro "diversos"
  Quando uma compra de seminovo ou troca nacional é direcionada para o pagamento
  E o terceiro vinculado a compra é o "diversos"
  Então será exibida mensagem ao usuário não permitindo prosseguir até que seja vinculado um cliente válido