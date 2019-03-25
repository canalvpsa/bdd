# language: pt

@PDV @venda
Funcionalidade: PDV - Venda com troca

Contexto:
Dado que o usuário inicia uma venda

Cenário: Venda com troca que o item trocado tem o mesmo valor do item vendido
Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
E adiciona o item "brinco" para troca com quantidade "1" e valor unitário de R$ "41,80"
E o valor total da venda é de R$ "0,00"
E será carregado o plano de pagamento "troca" no valor de R$ "41,80"
Então a venda é finalizada com sucesso