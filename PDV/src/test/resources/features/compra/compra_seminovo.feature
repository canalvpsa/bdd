# language: pt

@PDV @seminovo
Funcionalidade: PDV - Compra de seminovos

Dado que o usuário configou a operação de "Compra para revenda"
E na compra informou compra de seminovos


Esquema do Cenário: Pesquisa de itens para inclusão na compra
    Quando o usuário pesquisar pelo produto seminovo pesquisando por "<pesquisa>"
    Então são listados todos os produtos seminovos relacionados ao produto informado
    E é possível adicionar somente itens que são seminovos (possuem seminovo_base_id)

    |   pesquisa   |
    | codigo_barras|
    | nome_produto |
    |codigo_interno|
    |codigo_sistema|
    |      tag     |


Cenário: Inclusão de itens na compra
    Dado que o usuário realizou a pesquisa de produtos
    E o usuário pesquisar pelo produto seminovo pesquisando por "<pesquisa>"
    E são listados todos os produtos seminovos relacionados ao produto informado
    Quando o usuário inclui o produto para compra
    Então o produto é inserido utilizando o custo referencial do produto
    Mas se existem tabelas de preço de compra vigentes
    Então serão listadas tabelas de preço
    E o usuário deve escolher a tabela de preço que será aplicada


Esquema do Cenário: Pagamento de compra
    Dado que o usuário já inseriu os itens para compra
    Quando o usuário finalizar a compra
    Então escolhe como o pagamento será efetuado ao cliente "<pagamento>"

    |pagamento|
    |adiantamento|
    |dinheiro|


Cenário: Finalização com pagamento em dinheiro
    Dado que o usuário escolheu pagamento em dinheiro
    Quando finalizar a compra
    Então é exibida tela de lançamento de despesa
    E já existe preenchimento prévio para terceiro, valor total, tipo documento (recibo) e número documento
    E não é possível alterar o valor total
    E o motivo é "Compra de seminovo + nro Compra"
    E o usuário informa o tipo de despesa
    E no conferência de caixa é registrada uma sangria


Cenário: Finalização com pagamento em dinheiro informando valor maior que valor total
    Dado que o usuário escolheu pagamento em dinheiro e informar valor maior que total da compra
    Quando finalizar a compra
    Então é exibida mensagem:
    """
    Troco R$ XX,XX. O lançamento da sangria será R$ XX,XX.
    """
    E o usuário confirma o lançamento do troco
    E o total da sangria será igual ao total da compra
    Mas se o usuário não confirmar o lançamento do troco
    Então o usuário pode alterar as dados de pagamento


Cenário: Finalização com pagamento em adiantamento
    Dado que o usuário escolheu pagamento adiantamento
    Quando finalizar a compra
    Então será exibida mensagem de sucesso
    E no relatório de fechamento de caixa é registrado um "adiantamento gerado"
    E na conferência de caixa o valor é somado na forma de pagamento "dinheiro"
    E o terceiro recebe um saldo a utilizar de adiantamento