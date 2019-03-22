#language: pt

Funcionalidade: Consultar produtos e serviços


Cenário: Bipar código de barras na consulta de produtos
    Quando o usuário acessa a consulta de produto
    Então é possível utilizar o dispositivo móvel para leitura do código de barras 
    E é exibido o detalhe do produto listando o preço e a quantidade disponível na loja

Esquema do Cenário: Digitar na consulta de produtos/serviços
    Dado que o usuário acessa a consulta de produto/serviço
    E pesquisa por produto digitando na pesquisa: "<pesquisa>"
    Então é exibido o detalhe do produto listando o preço e a quantidade disponível na loja
    Mas se a pesquisa resultar em produto base
    Então deve ser possível detalhar a grade
    E para cada item de grade listar os preços e quantidades disponíveis na loja

    Exemplos:
    |   pesquisa   |
    | código_barras|
    |código_interno|
    |  código_base |
    |  código_sku  | 
    |      tag     |
    |     nome     |


Esquema do Cenário: Digitar na consulta de produtos/serviços
    Dado que o usuário acessa a consulta de produto/serviço
    E pesquisa por serviço digitando na pesquisa: "<pesquisa>"
    Então é exibido o detalhe do serviço listando o preço

    Exemplos:
    |   pesquisa   |
    |código_interno|
    |  código_base |
    |     nome     |   



Cenário: Incluir serviço no carrinho
    Dado que o usuário está em atendimento
    Quando consulta por determinado serviço
    Então o usuário adiciona o item no carrinho com quantidade 1


Cenário: Validar parâmetro de saldo negativo ao incluir item
    Dado que o usuário está na inclusão de item
    E o parâmetro de permitir saldo negativo está habilitado na entidade
    Quando adiciona produto sem saldo disponível
    Então o usuário adiciona o item no carrinho com quantidade 1


Cenário: Validar parâmetro de saldo negativo ao incluir item
    Dado que o usuário está na inclusão de item
    E o parâmetro de permitir saldo negativo está desabilitado na entidade
    Quando adiciona produto sem saldo disponível
    Então é exibida mensagem de alerta
    E o produto não é inserido no carrinho


Cenário: Incluir produto no carrinho e atendimento iniciado
    Dado que o usuário iniciou atendimento
    Quando consulta por determinado produto
    Então o usuário adiciona o item no carrinho com quantidade 1


Cenário: Incluir produto no carrinho e atendimento não iniciado
    Dado que o usuário não iniciou atendimento
    Quando consulta por determinado produto
    Então não será possível adicionar item ao carrinho


Cenário: Consultar por saldo em outras lojas
    Dado que o usuário consultou um produto
    Quando detalha o produto para visualizar saldo disponível e preço
    Então é possível consultar o saldo disponível do produto em outras lojas também


Cenário: Solicitar transferência de outras lojas
    Dado que o usuário detalhou o saldo disponível e o preço do produto em outras lojas
    Quando o usuário solicitar transferência
    Então informa a loja que terá a solicitação enviada
    E informa a quantidade a ser solicitada
    E finaliza a solicitação de transferência
    