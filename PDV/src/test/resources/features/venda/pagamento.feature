# language: pt

@PDV @venda @executavel
Funcionalidade: PDV - Venda no PDV com plano de pagamento em dinheiro, dinheiro com troco ou crediário.

Contexto:
Dado que o usuário inicia uma venda

@dinheiro 
Cenário: Venda com pagamento em dinheiro
    Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
    E o valor total da venda é de R$ "41,80"
    E adiciona o plano de pagamento dinheiro
    Então a venda é finalizada com sucesso

@dinheiro_troco
    Cenário: Venda com pagamento em dinheiro com troco
    Quando adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
    E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
    E o valor total da venda é de R$ "280,80"
    E adiciona o plano de pagamento dinheiro informando R$ "300,00" 
    Então o usuário confirma o valor do troco
    E a venda é finalizada com sucesso	
	
@crediario
Cenário: Venda com pagamento no crediário
    Quando o usuário altera o cliente para "ANA MARIA" 
    E adiciona o item "brinco" com quantidade "1" com valor unitário de R$ "41,80"
    E adiciona o item "calca" com quantidade "1" com valor unitário de R$ "239,00"
    E adiciona o plano de pagamento crediário
    Então a venda é finalizada com sucesso e são exibidas as parcelas


@adiantamento_gerado @ignore
Cenário: Venda com adiantamento gerado
    Quando o usuário efetivar uma venda
    E no pagamento informar valor maior do que o total da venda
    E informar a diferença como adiantamento
    Então a venda deve registrar o valor do adiantamento como "adiantamento gerado"


@adiantamento_utilizado @ignore
Cenário: Venda com adiantamento utilizado
    Dado que o usuário possui saldo em adiantamento
    Quando o usuário efetivar uma venda
    E no pagamento informa um adiantamento
    Então a venda deve registrar o valor do adiantamento como "adiantamento utilizado"


@limite_credito @crediario @ignore
 Cenário: Venda com limite mensal máximo
    Dado que o cliente possui limite de crédito mensal configurado
    Quando realizada venda com plano de pagamento "crediário"
    Então são somados os valores das parcelas que vencem no mesmo período de acordo com a configuração do limite de crédito
    E são somados os valores das parcelas já emitidas com data de vencimento no mesmo período para o cliente, considerando somente as parcelas em aberto
    E é verificado se as novas parcelas somadas com as parcelas existentes no mesmo período, ultrapassam o limite mensal máximo do cliente


@limite_credito @crediario @ignore
 Cenário: Venda com limite mensal máximo permitido
    Dado que o cliente possui limite de crédito mensal configurado
    E está realizando uma venda com plano de pagamento "crediário"
    E a data de vencimento de cada parcela é comparada com o período relacionado
    Quando é verificado que a venda não ultrapassa o limite mensal máximo do cliente
    Então a venda é realizada com sucesso


@limite_credito @crediario @ignore
 Cenário: Venda com limite mensal máximo não permitido e gerente não autorizado a liberar a venda
    Dado que o cliente possui limite de crédito mensal configurado
    E a configuração de limite para "Permitir liberar valor excedente" está desabilitada
    E está realizando uma venda com plano de pagamento "crediário"
    E a data de vencimento de cada parcela é comparada com o período relacionado
    Quando é verificado que a venda ultrapassa o limite mensal máximo do cliente
    Então é exibida mensagem ao usuário "Limite máximo mensal do cliente foi atingido para o período dd/mm até dd/mm. Valor em aberto (R$ x,xx) +  novas parcelas (R$ x,xx) é maior que o limite mensal de R$ xx,xx"
    E a venda não pode ser finalizada
    E não há uma forma para liberar a venda sem alterar os dados do plano de pagamento


@limite_credito @crediario @ignore
 Cenário: Venda com limite mensal máximo não permitido e gerente não autoriza a venda
    Dado que o cliente possui limite de crédito mensal configurado
    E a configuração de limite para "Permitir liberar valor excedente" está habilitada
    E está realizando uma venda com plano de pagamento "crediário"
    E a data de vencimento de cada parcela é comparada com o período relacionado
    E é verificado que a venda ultrapassa o limite mensal máximo do cliente
    E é exibida mensagem ao usuário "Limite máximo mensal do cliente foi atingido para o período dd/mm até dd/mm. Valor em aberto (R$ x,xx) +  novas parcelas (R$ x,xx) é maior que o limite mensal de R$ xx,xx. Deseja realmente confirmar o pedido"
    Quando clica em "Não"
    Então a venda não pode ser finalizada


@limite_credito @crediario @ignore
 Cenário: Venda com limite mensal máximo não permitido e gerente autoriza a venda
    Dado que o cliente possui limite de crédito mensal configurado
    E a configuração de limite para "Permitir liberar valor excedente" está habilitada
    E está realizando uma venda com plano de pagamento "crediário"
    E a data de vencimento de cada parcela é comparada com o período relacionado
    E é verificado que a venda ultrapassa o limite mensal máximo do cliente
    E é exibida mensagem ao usuário "Limite máximo mensal do cliente foi atingido para o período dd/mm até dd/mm. Valor em aberto (R$ x,xx) +  novas parcelas (R$ x,xx) é maior que o limite mensal de R$ xx,xx. Deseja realmente confirmar o pedido"
    Quando clica em "Sim"
    Então o gerente informa seu usuário e senha
    E a venda é finalizada com sucesso