#language: pt

Funcionalidade: Atendimento

@atendimento
Cenário: Iniciar atendimento
    Dado que o usuário está na tela inicial do aplicativo
    Quando inicia um atendimento
    Então deve identificar o cliente através do CPF ou nome
    E são listados os orçamentos registrados para o cliente


@atendimento
Cenário: Iniciar atendimento a partir de orçamento
    Dado que o usuário está na tela inicial do aplicativo
    E iniciou um atendimento
    Quando marca um orçamento
    E confirma 
    Então os dados do orçamento são carregados



@identificar_cliente
Cenário: Cadastrar novo cliente no atendimento
    Dado que o usuário está na tela inicial do aplicativo
    Quando inicia atendimento
    E informa os dados de um cliente ainda não cadastrado
    Então deve ser possível realizar o cadastro do cliente


@identificar_cliente
Cenário: Identificar cliente no atendimento
    Dado que o usuário está em atendimento
    Quando o usuário identifica o cliente no atendimento
    Então o carrinho deve ficar disponível para acesso


@atendimento
Cenário: Finalizar atendimento não convertido em venda
    Quando o usuário finaliza atendimento sem finalizar uma venda
    Então o atendimento deve ser contabilizado para o vendedor
    E fica disponível para o usuário a possibilidade de iniciar um novo atendimento