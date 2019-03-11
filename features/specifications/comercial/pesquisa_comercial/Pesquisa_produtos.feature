#language: pt

Funcionalidade: ERP - Pesquisa de produtos

@preço @tabelaPreço
Cenário: Validação de preço de venda para uma entidade
    Dado que o usuário pesquisou por determinado produto em uma entidade
    E não informou uma tabela de preço
    Quando realiza a pesquisa de produtos
    Então o preço de venda listado é a média dos preços de tabelas de preço vigentes para a entidade, ignorando as tabelas de compra seminovos
    Mas se não houver tabela de preço vigente
    Então o preço de venda listado é a média dos preços de tabelas de preço permanentes para a entidade, ignorando as tabelas de compra seminovos
    Mas se não houver nenhuma tabela de preço para o produto
    Então o preço de venda listado é igual ao preço na tabela padrão


@preço @tabelaPreço
Cenário: Validação de preço de venda para várias entidades
    Dado que o usuário pesquisou por determinado produto em várias entidades
    E não informou uma tabela de preço
    Quando realiza a pesquisa de produtos
    Então cada entidade terá a sua média calculada conforme descrito no cenário específico
    E o preço de venda listado será a média de cada entidade será somada e dividida pela quantidade de entidades informadas na pesquisa