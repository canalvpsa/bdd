#language: pt

@ERP
Funcionalidade: ERP - Conferência de caixa


Cenário: Detalhar venda efetuada no ERP com recebimento efetuado
Dado que o usuário efetuou uma venda no ERP
E efetuou o recebimento no caixa com qualquer forma de pagamento
Quando o usuário detalha as vendas na data da venda
Então a venda do ERP é listada na forma de pagamento "Valor Crediário"


Cenário: Detalhar venda efetuada no ERP sem recebimento
Dado que o usuário efetuou uma venda no ERP
E o recebimento não foi efetuado
Quando o usuário detalha as vendas na data da venda
Então a venda do ERP é listada na forma de pagamento "Valor Crediário"



Cenário: Detalhar venda efetuada no aplicativo de sugestão de compra e venda
Dado que o usuário efetuou uma venda no aplicativo de sugestão de compra e venda
E efetuou o recebimento no caixa com qualquer forma de pagamento
Quando o usuário detalha as vendas na data da venda
Então a venda do ERP é listada na forma de pagamento "Valor Crediário"