#language: pt

Funcionalidade: Cadastro, alteração, exclusão e pesquisa de tabela de preço

Esquema do Cenário: Cadastrar tabela de preço com utilizações e tipos de configuração
    Dado que o usuário está cadastrando uma tabela de preço
    Quando informa utilização "<tipo>" e "<tipo_configuracao>"
    E adiciona produtos na tabela de preço
    Então o produto fica vinculado a tabela de preço

    Exemplos:
|       tipo      |tipo_configuracao|
| compra_seminovo |  preço_produto  |
|   venda_normal  |  preço_produto  |
|venda_promocional|  preço_produto  |
| compra_seminovo |   configuracao  |
|   venda_normal  |   configuracao  |
|venda_promocional|   configuracao  |


Esquema do Cenário: Cadastrar tabela de preço com vigência permanente e por período
    Dado que o usuário está cadastrando uma tabela de preço
    Quando informa vigência "<vigencia>"
    E adiciona produtos na tabela de preço
    Então o produto fica vinculado a tabela de preço

    Exemplos:
|     vigencia    |
|    permanente   |
| periodo_expirado|
|periodo_andamento|


Cenário: Cadastrar tabela de preço que possui tabela referência
    Dado que o usuário está cadastrando uma tabela de preço
    E o tipo de cálculo é diferente de markup
    Quando informa uma tabela de referência
    E lista os produtos da tabela de preço
    Então todos os produtos da tabela referência devem ser listados na tabela nova
    E os preços sugeridos também serão os mesmos


Cenário: Alterar tabela de preço que é referência em outra tabela
    Dado que a tabela de preço está associada como referência em outra tabela de preço
    Quando inclui ou exclui produtos
    E altera o preço da tabela de preço
    Então as tabelas de preço do tipo de cálculo "preço por produto" relacionadas não sofrem qualquer alteração, pois são apenas cópias
    Mas se a tabela de preço for do tipo de cálculo "<configuração>"
    Então a tabela de preço tem os percentuais recalculados em cima dos produtos e valores da tabela referência alterada

Exemplos:
|configuração|
|  desconto  |
|  acrescimo |
|   markup   |


Cenário: Inativar tabela de preço que é referência em outra tabela de preço
    Dado que a tabela de preço está associada como referência em outra tabela de preço
    Quando inativar a tabela de preço
    Então as tabelas de preço do tipo de cálculo "preço por produto" relacionadas não sofrem qualquer alteração, pois são apenas cópias
    Mas se a tabela de preço for do tipo de cálculo "<configuração>"
    Então a tabela de preço tem os percentuais recalculados com base nos produtos e valores da tabela referência alterada

Exemplos:
|configuração|
|  desconto  |
|  acrescimo |
|   markup   |


Cenário: Cadastrar tabela de preço de configuração por markup e tabela referência
    Dado que o usuário está cadastrando uma tabela de preço
    Quando informa o tipo de cálculo "markup"
    Então não é possível associar tabela de preço referência
    E a configuração se aplica a todos os produtos do sistema utilizando o percentual no custo do produto da entidade


Esquema do Cenário: Cadastrar tabela de preço de configuração por desconto ou acréscimo e tabela referência
    Dado que o usuário está cadastrando uma tabela de preço
    E informou uma tabela referência
    Quando informa o tipo de cálculo "<tipo_calculo>"
    Então é possível definir um percentual de desconto
    E o percentual é aplicado para todos os produtos relacionados a tabela de referência
    Mas se um nível de categoria for informado
    Então são listadas todas as categorias do nível para configuração individual
    E o percentual é aplicado para todos os produtos relacionados a tabela de referência que estão associados a categoria configurada
    E para cada categoria configurada é criada uma tabela de preço com o nome informado acompanhado do nome da categoria

Exemplos:
|tipo_calculo|
|  desconto  |
|  acrescimo |


Esquema do Cenário: Cadastrar tabela de preço de configuração de desconto ou acréscimo sem tabela referência
    Dado que o usuário está cadastrando uma tabela de preço
    E a tabela de preço não possui associação com tabela de referência
    Quando informa o tipo de cálculo "<tipo_calculo>"
    E realiza a configuração do percentual para todas as categorias ou somente para uma categoria
    Então será obrigatório informar uma tabela referência

    Exemplos:
|tipo_calculo|
|  desconto  |
|  acrescimo |
 