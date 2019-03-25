# language: pt

@PDV @venda @desconto @executavel
Funcionalidade: PDV - Venda com desconto em reais e em percentual aplicado no valor total da venda

Contexto:
Dado que o usuário inicia uma venda

Cenário: Venda de dois itens com desconto em reais no total

Quando adiciona o item "brinco" com quantidade "2" no valor de R$ "41,80"
E adiciona o item "calca" com quantidade "1" no valor de R$ "239,00"
E informa desconto no total de R$ "8,36"
E o valor total da venda é de R$ "314,24"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso

Cenário: Venda de dois itens com desconto em reais no total

Quando adiciona o item "brinco" com quantidade "2" no valor de R$ "41,80"
E adiciona o item "calca" com quantidade "1" no valor de R$ "239,00"
E informa desconto no total de "2,59" %
E o valor total da venda é de R$ "314,24"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso

Cenário: Venda de um item com desconto em reais no total

Quando adiciona o item "brinco" com quantidade "1" no valor de R$ "41,80"
E informa desconto no total de R$ "4,18"
E o valor total da venda é de R$ "37,62"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso

Cenário: Venda de um item com desconto em reais no total

Quando adiciona o item "brinco" com quantidade "1" no valor de R$ "41,80"
E informa desconto no total de "10,00" %
E o valor total da venda é de R$ "37,62"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso