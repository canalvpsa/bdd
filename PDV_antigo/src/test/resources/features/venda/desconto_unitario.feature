# language: pt

@PDV @venda, @desconto @executavel
Funcionalidade: PDV - Venda com desconto em reais e em percentual aplicado no item a ser adicionado na venda

Contexto:
Dado que o usuário inicia uma venda

Cenário: Venda com dois itens e desconto em reais em um dos itens

Quando adiciona o item "brinco" com quantidade "2" com valor unitário de R$ "41,80" e desconto unitário de R$ "8,36"
E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
E o valor total da venda é de R$ "314,24"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso


Cenário: Venda com dois itens e desconto em percentual em um dos itens

Quando adiciona o item "brinco" com quantidade "2" com valor unitário de R$ "41,80" e desconto unitário de "10,00" %
E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
E o valor total da venda é de R$ "314,24"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso


Cenário: Venda com um item e desconto em reais no no item

Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80" e desconto unitário de R$ "4,18"
E o valor total da venda é de R$ "37,62"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso


Cenário: Venda com um item e desconto em percentual no item

Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80" e desconto unitário de "10,00" %
E o valor total da venda é de R$ "37,62"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso
