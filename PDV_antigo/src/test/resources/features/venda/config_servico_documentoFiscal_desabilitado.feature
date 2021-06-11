# language: pt

@PDV @venda @servico @executavel
Funcionalidade: PDV - Venda no PDV com servico parâmetro desabilitado para enviar ao doc fiscal

Contexto:
Dado que o parâmetro de enviar serviço para documento fiscal está desabilitado
E que o usuário inicia uma venda


Cenário: Venda apenas de serviço
Quando adiciona o item "servico" com quantidade "1" com valor unitário de R$ "50,00"
E o valor total da venda é de R$ "50,00"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso


#nao permite venda conjugada neste terminal
@ignore
Cenário: Venda de serviço com produto e envia serviço para documento fiscal

Quando adiciona o item "servico" com quantidade "1" com valor unitário de R$ "50,00"
E adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
E o valor total da venda é de R$ "91,80"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso