#language: pt
@ERP
Funcionalidade: ERP - Pedido de venda

Contexto:
Dado que o usuário inicia uma venda


@limite_credito
 Cenário: Venda com limite mensal máximo
    Dado que o cliente possui limite de crédito mensal configurado
    Quando realizada venda com plano de pagamento "crediário"
    Então são somados os valores das parcelas que vencem no mesmo período de acordo com a configuração do limite de crédito
    E são somados os valores das parcelas já emitidas com data de vencimento no mesmo período para o cliente, considerando somente as parcelas em aberto
    E é verificado se as novas parcelas somadas com as parcelas existentes no mesmo período, ultrapassam o limite mensal máximo do cliente


@limite_credito
 Cenário: Venda com limite mensal máximo permitido
    Dado que o cliente possui limite de crédito mensal configurado
    E está realizando uma venda com plano de pagamento "crediário"
    E a data de vencimento de cada parcela é comparada com o período relacionado
    Quando é verificado que a venda não ultrapassa o limite mensal máximo do cliente
    Então a venda é realizada com sucesso


@limite_credito
 Cenário: Venda com limite mensal máximo não permitido e gerente não autorizado a liberar a venda
    Dado que o cliente possui limite de crédito mensal configurado
    E a configuração de limite para "Permitir liberar valor excedente" está desabilitada
    E está realizando uma venda com plano de pagamento "crediário"
    E a data de vencimento de cada parcela é comparada com o período relacionado
    Quando é verificado que a venda ultrapassa o limite mensal máximo do cliente
    Então é exibida mensagem ao usuário "Limite máximo mensal do cliente foi atingido para o período dd/mm até dd/mm. Valor em aberto (R$ x,xx) +  novas parcelas (R$ x,xx) é maior que o limite mensal de R$ xx,xx"
    E a venda não pode ser finalizada
    E não há uma forma para liberar a venda sem alterar os dados do plano de pagamento


@limite_credito
 Cenário: Venda com limite mensal máximo não permitido e gerente não autoriza a venda
    Dado que o cliente possui limite de crédito mensal configurado
    E a configuração de limite para "Permitir liberar valor excedente" está habilitada
    E está realizando uma venda com plano de pagamento "crediário"
    E a data de vencimento de cada parcela é comparada com o período relacionado
    E é verificado que a venda ultrapassa o limite mensal máximo do cliente
    E é exibida mensagem ao usuário "Limite máximo mensal do cliente foi atingido para o período dd/mm até dd/mm. Valor em aberto (R$ x,xx) +  novas parcelas (R$ x,xx) é maior que o limite mensal de R$ xx,xx. Deseja realmente confirmar o pedido"
    Quando clica em "Não"
    Então a venda não pode ser finalizada


@limite_credito
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