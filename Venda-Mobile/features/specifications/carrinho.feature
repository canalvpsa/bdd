#language: pt

Funcionalidade: Venda-Mobile Carrinho

Cenário: Carrinho não disponível para atendimento não iniciado
    Dado que o usuário não está em atendimento
    Então o carrinho não está disponível para acesso


Cenário: Carrinho disponível para atendimento iniciado
    Dado que o usuário está em atendimento
    Quando o usuário acessar o carrinho
    Então ele pode consultar produtos
    E pode efetuar uma troca
    E pode incluir item no carrinho
    E pode finalizar a venda


Cenário: Carregar orçamento cadastrado
    Dado que o cliente possui orçamento em aberto
    Quando o usuário clica para carregar orçamento
    Então os dados ?????


Esquema do Cenário: Incluir item para venda
    Dado que o usuário está no carrinho
    Quando acessa a inclusão de itens
    Então deve ser possível realizar a inclusão de item das seguinte maneiras: "<incluirItem>" para item do tipo "<tipoItem>"

    Exemplos:
        |bipar_Codigo_barras|    produto    |
        |   digitar_codigo  |produto/serviço|
        |  consulta_produto |produto/serviço|



Cenário: Incluir item para venda bipando código de barras
    Dado que o usuário está no carrinho
    Quando acessa a inclusão de itens
    E utiliza o leitor de código de barras do dispositivo móvel
    Então o produto é adicionado no carrinho com quantidade 1
    Mas se o produto não tiver saldo na loja
    E o parâmetro para permitir saldo negativo estiver desabilitado
    Então deve ser exibida mensagem de alerta
    E o produto não será inserido na venda


Esquema do Cenário: Incluir item para venda informando código do produto
    Dado que o usuário está no carrinho
    Quando acessa a inclusão de itens
    E digita o "<codigo>"
    Então o produto ou serviço é adicionado no carrinho com quantidade 1
    Mas se o produto não tiver saldo na loja
    E o parâmetro para permitir saldo negativo estiver desabilitado
    Então deve ser exibida mensagem de alerta
    E o produto não será inserido na venda

    Exemplos:
        |    codigo    |
        | código_barras|
        |código_interno|
        |  código_sku  |
        |      tag     |


Cenário: Excluir item do carrinho
    Dado que o usuário está no carrinho
    Quando excluir um item do carrinho
    Então o item é excluído com sucesso
    E o desconto no total será recalculado



Cenário: Alterar quantidade dos itens no carrinho
    Dado que o usuário está no carrinho
    Quando altera a quantidade do item
    Então a quantidade é atualizada
    E o valor total do item é ajustado
    E o desconto no item é ajustado
    E o desconto total é recalculado


Esquema do Cenário: Conceder desconto unitário
    Dado que o usuário está no carrinho
    Quando incluir desconto para uma item
    Então deve ser possível informar o desconto do "<tipoDesconto>"

    Exemplos:
        |tipoDesconto|
        | percentual |
        |    real    |


Cenário: Conceder desconto no total
    Dado que o usuário está no carrinho
    Quando incluir desconto no total da venda
    Então deve ser possível informar o desconto em "percentual" ou "reais"


Cenário: Informar desconto maior que permitido para vendedor
    Dado que o usuário está no carrinho
    Quando informar desconto maior que o percentual configurado para o seu vendedor
    Então é exibida mensagem ao usuário para confirmar o envio de autorização de desconto ao gerente
    

Cenário: Solicitar desconto maior que permitido para vendedor
    Dado que foi exibida mensagem para envio de autorização de desconto ao gerente
    Quando quando usuário confirmar envio de autorização ao gerente
    Então o desconto ficará pendente até a autorização do gerente
    E uma notificação é enviada para o gerente autorizar o desconto
    Mas o usuário poderá cancelar o pedido de autorização até a autorização do gerente


Cenário: Receber confirmação de autorização de desconto do gerente
    Dado que o usuário enviou pedido de autorização de desconto ao gerente
    Quando o gerente autorizar o desconto
    Então uma notificação de é exibida para o usuário
    E o desconto é aplicado no item ou no total
    E os totais são recalculados


Cenário: Receber rejeição de autorização de desconto do gerente
    Dado que o usuário enviou pedido de autorização de desconto ao gerente
    Quando o gerente negar o desconto
    Então uma notificação é exibida para o usuário
    E o desconto não é aplicado 