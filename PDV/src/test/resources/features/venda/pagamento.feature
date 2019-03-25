# language: pt

@PDV @venda @executavel
Funcionalidade: PDV - Venda no PDV com plano de pagamento em dinheiro, dinheiro com troco ou crediário.

Contexto:
Dado que o usuário inicia uma venda

Cenário: Venda com pagamento em dinheiro
Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
E o valor total da venda é de R$ "41,80"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso


Cenário: Venda com pagamento em dinheiro com troco
Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
E o valor total da venda é de R$ "280,80"
E adiciona o plano de pagamento dinheiro informando R$ "300,00" 
Então o usuário confirma o valor do troco
E a venda é finalizada com sucesso	
	

Cenário: Venda com pagamento no crediário
Quando o usuário altera o cliente para "ANA MARIA" 
E adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
E adiciona o plano de pagamento crediário
Então a venda é finalizada com sucesso e são exibidas as parcelas


Cenário: Venda com adiantamento gerado
    Quando o usuário efetivar uma venda
    E no pagamento informar valor maior do que o total da venda
    E informar a diferença como adiantamento
    Então a venda deve registrar o valor do adiantamento como "adiantamento gerado"


Cenário: Venda com adiantamento utilizado
    Dado que o usuário possui saldo em adiantamento
    Quando o usuário efetivar uma venda
    E no pagamento informa um adiantamento
    Então a venda deve registrar o valor do adiantamento como "adiantamento utilizado"