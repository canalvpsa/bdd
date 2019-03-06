#language: pt

@produto
Funcionalidade: Cadastro, alteração, cópia e pesquisa de produtos

Cenário: Acesso ao cadastro de produto
    Quando o usuário acessa o cadastro de produto
    Então nos dados do produto é possível vincular o produto original
    E nos itens de grade também é possível realizar o vínculo através das opções dos itens


Cenário: Cadastrar produto novo simples
    Dado que o usuário está no cadastro do produto
    Quando realiza o preenchimento dos campos
    E não informa um produto original
    Então o preenchimento do produto original não deve ser obrigatório
    E o produto é cadastrado como novo


Cenário: Cadastrar produto novo com grade
    Dado que o usuário está no cadastro do produto
    Quando realiza o preenchimento dos campos
    E informa grade para o produto
    E não informa um produto original
    Então o preenchimento do produto original não deve ser obrigatório
    E os produtos são cadastrados como novos


Cenário: Cadastrar produto seminovo simples
    Dado que o usuário está no cadastro do produto
    Quando realiza o preenchimento dos campos
    E informa um produto original
    Então o produto é cadastrado como seminovo recebendo o vínculo com produto original


Cenário: Cadastrar produto seminovo com grade
    Dado que o usuário está no cadastro do produto
    Quando realiza o preenchimento dos campos
    E informa grade para o produto
    E informa um produto original para os itens de grade
    Então os produtos são cadastrados como seminovos recebendo o vínculo com produto original


Cenário: Alterar produto simples vinculando produto original
    Dado que o usuário está na alteração do produto
    E que o produto não possui vínculo com produto original
    Quando informa um produto original
    Então o produto é salvo como seminovo recebendo o vínculo com produto original


Cenário: Alterar produto vinculando produto original de item de grade
    Dado que o usuário está na alteração do produto
    E o produto possui itens de grade
    E que o produto não possui vínculo com produto original
    Quando informa um produto original em um item de grade
    Então o produto item de grade é salvo como seminovo recebendo o vínculo com produto original


Cenário: Vincular produto original em produto simples pelas opções do cadastro do produto
    Dado que o usuário está na pesquisa do cadastro de produto
    E que o produto não possui vínculo com produto original
    Quando informa um produto original nas opções do produto
    Então o produto é salvo como seminovo recebendo o vínculo com produto original


Cenário: Vincular produto original em item de grade pelas opções do cadastro do produto
    Dado que o usuário está na pesquisa do cadastro de produto
    E o produto é um item de grade
    E que o produto não possui vínculo com produto original
    Quando informa um produto original nas opções do produto
    Então o produto item de grade é salvo como seminovo recebendo o vínculo com produto original


Cenário: Vincular o próprio produto como produto original
    Quando o usuário vincula o próprio produto como produto original
    Então é exibida mensagem "Produto original deve ser diferente do seminovo"
    E a alteração não é salva

@copiar_produto
Cenário: Copiar produto e opção de seminovo desabilitado
    Dado que o usuário está na pesquisa do cadastro de produto
    Quando copia um produto sem marcar opção de seminovo
    Então a sugestão do novo produto não exibe vínculo com produto original
    E o preço de custo e preço de venda sugeridos são iguais ao do produto copiado


@copiar_produto
Cenário: Copiar produto e opção de seminovo desabilitado vinculando manualmente produto original
    Dado que o usuário está na pesquisa do cadastro de produto
    Quando copia um produto sem marcar opção de seminovo
    E informa um produto original
    Então o produto é cadastrado como seminovo recebendo o vínculo com produto original


@copiar_produto
Cenário: Copiar produto e opção de seminovo habilitado
    Dado que o usuário está na pesquisa do cadastro de produto
    Quando copia um produto marcando opção de seminovo
    Então a sugestão do novo produto exibe o produto vinculado como produto original
    E o preço de custo e preço de venda ficam zerados necessitando de preenchimento manual


Cenário: Alterar produto simples removendo vínculo do produto original
    Dado que o usuário está na alteração do produto
    E que o produto possui vínculo com produto original
    Quando remove o vínculo com produto original
    Então o produto é salvo como novo removendo o vínculo com produto original


Cenário: Alterar produto removendo produto original de item de grade
    Dado que o usuário está na alteração do produto
    E o produto possui itens de grade
    E que o produto possui vínculo com produto original
    Quando remove o vínculo com produto original
    Então o produto item de grade é salvo como novo removendo o vínculo com produto original


Cenário: Remover produto original de produto simples pelas opções do cadastro do produto
    Dado que o usuário está na pesquisa do cadastro de produto
    E que o produto possui vínculo com produto original
    Quando remove o vínculo com produto original nas opções do produto
    Então o produto é salvo como novo removendo o vínculo com produto original


Cenário: Remover produto original em item de grade pelas opções do cadastro do produto
    Dado que o usuário está na pesquisa do cadastro de produto
    E o produto é um item de grade
    E que o produto possui vínculo com produto original
    Quando remove o vínculo com produto original nas opções do produto
    Então o produto é salvo como novo removendo o vínculo com produto original

