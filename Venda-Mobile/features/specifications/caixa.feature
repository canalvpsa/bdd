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
Cenário: Sangria no caixa
    Dado que o usuário está na tela inicial do aplicativo
    E possui permissão para realizar operações no caixa
    E acessa as operações de caixa
    Quando realiza a sangria
    E informa o valor em dinheiro a ser retirado do caixa
    E o valor está disponível no caixa
    Então a sangria é efetuada com sucesso
    E o valor da sangria é subtraído do valor total em dinheiro do caixa


@suprimento_caixa
Cenário: Suprimento no caixa
    Dado que o usuário está na tela inicial do aplicativo
    E possui permissão para realizar operações no caixa
    E acessa as operações de caixa
    Quando realiza um suprimento
    E informa o valor em dinheiro a ser adicionado no caixa
    Então o suprimento é efetuada com sucesso
    E o valor do suprimento é somado no valor total em dinheiro do caixa


@fechar_caixa
Cenário: Fechamento de caixa
    Dado que o usuário está na tela inicial do aplicativo
    E possui permissão para realizar operações no caixa
    E acessa as operações de caixa
    Quando realiza o fechamento de caixa
    E informa os valores para cada plano de pagamento disponível na venda
    Então o fechamento de caixa é realizado com sucesso