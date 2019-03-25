#language: pt

@caixa
Funcionalidade: Caixa


Cenário: Operações de caixa para usuário com permissão para movimentar caixa
    Dado que o usuário está na tela inicial do aplicativo
    E possui permissão para realizar operações no caixa
    Quando acessa as operações de caixa
    Então é possível realizar abertura de caixa, fechamento de caixa, suprimento e sangria


@abrir_caixa
Cenário: Abertura de caixa
    Dado que o usuário está na tela inicial do aplicativo
    E possui permissão para realizar operações no caixa
    E acessa as operações de caixa
    Quando realiza a abertura do caixa
    E informa o valor em dinheiro do caixa
    Então a abertura de caixa é efetuada com sucesso


@sangria_caixa
Esquema do Cenário: Sangria no caixa
    Dado que o usuário está na tela inicial do aplicativo
    E possui permissão para realizar operações no caixa
    E acessa as operações de caixa
    Quando realiza a sangria
    E informa o valor em dinheiro a ser retirado do caixa
    E informa o motivo da retirada "<motivo>"
    E informa uma observação
    E o valor está disponível no caixa
    Então a sangria é efetuada com sucesso
    E o valor da sangria é subtraído do valor total em dinheiro do caixa

Exemplos:
|      motivo      |
|Troco dia seguinte|
|     Retirada     |


@suprimento_caixa
Esquema do Cenário: Suprimento no caixa
    Dado que o usuário está na tela inicial do aplicativo
    E possui permissão para realizar operações no caixa
    E acessa as operações de caixa
    Quando realiza um suprimento
    E informa o valor em dinheiro a ser adicionado no caixa
    E informa o motivo do suprimento "<motivo>"
    E informa uma observação
    Então o suprimento é efetuada com sucesso
    E o valor do suprimento é somado no valor total em dinheiro do caixa

Exemplos:
|     motivo     |
|  Saldo Inicial |
|Reforço de caixa|


@fechar_caixa
Esquema do Cenário: Fechamento de caixa
    Dado que o usuário está na tela inicial do aplicativo
    E possui permissão para realizar operações no caixa
    E acessa as operações de caixa
    Quando realiza o fechamento de caixa
    E informa os valores disponíveis no caixa para cada forma de pagamento "<forma_pagamento>"
    Então o fechamento de caixa é realizado com sucesso

Exemplos:
| forma_pagamento|
|    Dinheiro    |
|  Cheque_vista  |
|  Cheque_prazo  |
|  Cartao_debito |
| Cartao_credito |
| Outros_cartoes |
|    Crediario   |
|   Vale_troca   |
|Adiant_realizado|
 