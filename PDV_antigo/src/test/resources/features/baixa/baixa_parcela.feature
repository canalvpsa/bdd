# language: pt

@PDV @baixaParcela
Funcionalidade: PDV - Baixar parcela de crediário

Cenário: Baixa de uma parcela parcial

Dado que o cliente já possui parcela de crediário em aberto
E o vendedor lista as parcelas do cliente
Quando o vendedor seleciona a parcela "1" para pagamento no valor de R$ "140,40"
E adiciona o plano de pagamento dinheiro informando valor parcial R$ "100,00" na baixa de parcela
Então a baixa parcial é realizada com sucesso
E uma nova parcela é criada no valor de R$ "40,40"


Cenário: Baixa de uma parcela total

Dado que o cliente já possui parcela de crediário em aberto
E o vendedor lista as parcelas do cliente
Quando o vendedor seleciona a parcela "2" para pagamento no valor de R$ "140,40"
E adiciona o plano de pagamento dinheiro na baixa de parcela informando R$ "140,40"
Então a baixa é realizada com sucesso


Cenário: Baixa de duas parcelas

Dado que o cliente já possui parcela de crediário em aberto
E o vendedor lista as parcelas do cliente
Quando o vendedor seleciona as parcelas "1" e "2" para pagamento no valor de R$ "140,40" cada
E adiciona o plano de pagamento dinheiro na baixa de parcela informando R$ "180,80" 
Então a baixa é realizada com sucesso


Cenário: Baixar parcela gerada através de baixa parcial anterior
Dado que o cliente já possui parcela de crediário em aberto no valor de R$ "40,40" gerada por baixa parcial
E o vendedor lista as parcelas do cliente
Quando o vendedor seleciona a parcela "1" para pagamento no valor de R$ "40,40"
E adiciona o plano de pagamento dinheiro na baixa de parcela informando R$ "40,40" 
Então a baixa é realizada com sucesso