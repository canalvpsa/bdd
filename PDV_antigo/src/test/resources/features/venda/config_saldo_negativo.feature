# language: pt

@PDV @venda @sem_saldo @executavel
Funcionalidade: PDV - Venda com item sem saldo

@saldo_negativo
Cenário: Venda com item sem saldo e não permite saldo negativo
Dado que o parâmetro para permitir saldo negativo está desabilitado
E que o usuário inicia uma venda
Quando adiciona o item "regata" com quantidade "1" com valor unitário de R$ "59,90"
Então será exibida mensagem de saldo insuficiente


@saldo_negativo_prosseguir
Cenário: Venda com item sem saldo e permite saldo negativo
Dado que o parâmetro para permitir saldo negativo está habilitado
E que o usuário inicia uma venda
E o usuário altera o cliente para "ANA MARIA"
Quando adiciona o item "regata" com quantidade "1" com valor unitário de R$ "59,90"
E o valor total da venda é de R$ "59,90"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso