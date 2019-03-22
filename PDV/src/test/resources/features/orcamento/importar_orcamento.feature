# language: pt

@PDV @importarOrcamento @executavel
Funcionalidade: PDV - Orçamento com item cancelado e importação para venda

Contexto:
Dado que o usuário inicia um orcamento
E o usuário altera o cliente para "ANA MARIA"

@cancelamento @orcamento
Cenário: Orçamento com item cancelado

Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
E o item "calca" é excluído do orcamento
E o valor total do orçamento é de R$ "41,80"
E o orçamento é salvo
E o vendedor importa o orçamento na venda
Então o item "brinco" é carregado na venda com quantidade "1"
E o valor total da venda é de R$ "41,80"
E adiciona o plano de pagamento dinheiro
E a venda é finalizada com sucesso

@cancelamento @venda
Cenário: Item cancelado após importar para venda

Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
E o valor total do orçamento é de R$ "280,80"
E o orçamento é salvo
E o vendedor importa o orçamento na venda
Então o item "brinco" é carregado na venda com quantidade "1"
E o item "calca" é carregado na venda com quantidade "1"
Mas o item "calca" é excluído da venda
Então o valor total da venda é de R$ "41,80"
E adiciona o plano de pagamento dinheiro
E a venda é finalizada com sucesso