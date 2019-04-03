# language: pt

@PDV @orcamento @sem_saldo @executavel
Funcionalidade: PDV - Orçamento com item sem saldo

@saldo_negativo
Cenário: Orçamento com item sem saldo e não permitir saldo negativo

Dado que o parâmetro para permitir saldo negativo está desabilitado
E que o usuário inicia um orcamento
Quando adiciona o item "regata" com quantidade "1" com valor unitário de R$ "59,90"
Então será exibida mensagem de saldo insuficiente


@importarOrcamento @saldo_negativo_prosseguir
Cenário: Orçamento com item sem saldo e permite saldo negativo

Dado que o parâmetro para permitir saldo negativo está habilitado
E que o usuário inicia um orcamento
E o usuário altera o cliente para "ANA MARIA"
Quando adiciona o item "regata" com quantidade "1" com valor unitário de R$ "59,90"
E o valor total do orçamento é de R$ "59,90"
E o orçamento é salvo
E o vendedor importa o orçamento na venda
Então o item "regata" é carregado na venda com quantidade "1"
E o valor total da venda é de R$ "59,90"
E adiciona o plano de pagamento dinheiro
E a venda é finalizada com sucesso