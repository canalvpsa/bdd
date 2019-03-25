# language: pt

@PDV @venda, @cancelamento @executavel
Funcionalidade: PDV - Venda e exclui item da venda

Contexto:
Dado que o usuário inicia uma venda

Cenário: Venda com item cancelado

Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
E o item "calca" é excluído da venda
E o valor total da venda é de R$ "41,80"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso