 #language: pt

@PedidoVenda
Funcionalidade: Pedido de venda
  Testar api de pedido de venda
  
  @pedidoVendaAvista
  Cenário: Pedido de venda a vista

    Dado que eu já preenchi o cabeçalho da venda
    E já informei os produtos
    E eu preencher o plano de pagamento a vista
    Então o pedido de venda ficará com uma parcela apenas no valor de 25.29
    E com vencimento na data atual


  @pedidoVendaDescontoItem
	 Cenário: Pedido de venda com item que possui desconto
	 
	 Dado que eu já preenchi o cabeçalho da venda
	 E informei um item de 9.99 com desconto de 1.00
	 E informei um item de 15.30
	 Então o total da venda será de 24.29
	 

	 @pedidoVendaDescontoDoisItens
	 Cenário: Pedido de venda com dois itens, ambos com desconto
	 
	 Dado que eu já preenchi o cabeçalho da venda
	 E informei um item de 9.99 com desconto de 1.00
	 E informei um item de 15.30 com desconto de 1.00
	 Então o total da venda será de 23.29
	 
	 