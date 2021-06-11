# language: pt

@PDV @troca_nacional
Funcionalidade: PDV - Troca nacional

Dado que o usuário configou a operação de "Compra para revenda"
E na compra informou troca nacional

Esquema do Cenário: Pesquisa de itens para inclusão na compra
    Quando o usuário pesquisar pelo produto pesquisando por "<pesquisa>"
    Então são listados os produtos de acordo com o termo pesquisado

    |   pesquisa   |
    | codigo_barras|
    | nome_produto |
    |codigo_interno|
    |codigo_sistema|
    |      tag     |


Cenário: Inclusão de itens na compra
    Dado que o usuário realizou a pesquisa de produtos
    Quando o usuário inclui o produto para compra
    Então o produto é inserido utilizando o preço de venda da tabela de preço vigente respeitando a precedência atual


Cenário: Finalização da troca nacional
    Quando finalizar a troca nacional
    Então será exibida mensagem de sucesso
    E no relatório de fechamento de caixa é registrado um "adiantamento gerado"
    E na conferência de caixa o valor é somado na forma de pagamento "dinheiro"
    E o terceiro recebe um saldo a utilizar de adiantamento