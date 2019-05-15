#language: pt

@ERP @balanco @ajuste_estoque
Funcionalidade: ERP - Ajuste de saldo (cadastro de produto)


Contexto: Ajuste de estoque em produto 
    Dado que o usuário acessa o cadastro de produto



Cenário: Validar permissão de usuário para realizar ajuste de estoque no produto
    Dado que o usuário possui permissão para ajustar estoque no produto
    Quando o usuário acessa as opções em determinado produto
    Então a opção de "Ajuste de estoque" é ser exibida


Cenário: Validar permissão de usuário na entidade a ser ajustada
    Quando o usuário acessa o ajuste de estoque em determinado produto
    Então deve ser possível selecionar somente as entidades que o usuário tem acesso



Esquema do Cenário: Realizar ajuste de estoque no cadastro de um produto simples ou item de grade
    Quando o usuário acessa o ajuste de estoque do produto "<codigo_produto>"
    E informa a entidade que deseja realizar o ajuste
    E verifica o saldo atual do produto "<saldo_atual>"
    E informa o novo saldo do produto "<novo_saldo>"
    Então é criado um balanço de estoque
    E ocorre uma movimentação de estoque no produto com a diferença "<diferença>"
    E é realizado lançamento contábil na conta débito "<conta_debito>" e conta crédito "<conta_credito>" no valor total de "<valor>" (quantidade X custo médio)
    E é gerada uma pendência de nota fiscal na funcionalidade "ajuste de estoque"
    Exemplos:
|codigo_produto|saldo_atual|novo_saldo|diferença|            conta_debito            |            conta_credito           | valor|
| BAV15106.0016|     17    |    19    |    2    |          14201 MERCADORIAS         |51401 CUSTO DAS MERCADORIAS VENDIDAS|193,68|
| V12BL150.0002|     7     |     6    |    -1   |51401 CUSTO DAS MERCADORIAS VENDIDAS|          14201 MERCADORIAS         | 9,23 |
|  V11S01.0005 |     4     |     0    |    -4   |51401 CUSTO DAS MERCADORIAS VENDIDAS|          14201 MERCADORIAS         |100,00|
| V11TOP02.0002|     -2    |     0    |    2    |          14201 MERCADORIAS         |51401 CUSTO DAS MERCADORIAS VENDIDAS| 30,00|
| V11TOP02.0003|     -2    |     3    |    5    |          14201 MERCADORIAS         |51401 CUSTO DAS MERCADORIAS VENDIDAS| 75,00|


Cenário: Realizar ajuste de estoque no cadastro de um produto informando quantidade igual ao saldo atual
    Dado que o usuário informou os dados iniciais do ajuste de estoque no produto "BAV15106.0016"
    Quando informa um "novo saldo" igual ao "saldo atual" do produto na entidade
    Então é exibida mensagem "O novo saldo informado é igual ao saldo atual do produto"


Esquema do Cenário: Validar informações de ajuste de estoque no cadastro de um produto permitido
    Dado que o usuário acessa o ajuste de estoque no produto "<codigo_produto>"
    Quando informa a entidade que deseja realizar o ajuste
    Então é exibido o nome do produto, unidade (padrão) "<unidade_padrao>"
    E é exibido o saldo atual na entidade informada "<saldo_unidade_padrao>" considerando a unidade padrão 
    E o "<unidade_padrao>" e o "novo saldo" devem considerar a quantidade de decimais da unidade "<unidade_padrao>"
    Exemplos:
|codigo_produto|saldo_unidade_padrao|unidade_padrao|
| BAV15106.0016|         17         |      UN      |
|     1182     |         60         |      UN      |
|     1183     |          5         |      CX      |


Esquema do Cenário: Realizar ajuste de estoque para produto não permitido
    Quando o usuário informou os dados iniciais do ajuste de estoque no produto "<codigo_produto>"
    Então é exibida mensagem "<mensagem>"
    Exemplos:
|produto_não_permitido|codigo_sistema|                         mensagem                        |
|         base        |   BAV15106   |     Não é possível adicionar saldo para produto base    |
|    não estocável    |  UNICAL02.E  |Não é possível adicionar saldo para produto não estocável|


Esquema do Cenário: Realizar ajuste de estoque acrescentando saldo em produto de lote
    Dado que o usuário acessa o ajuste de estoque no produto "<codigo_produto>"
    Quando informa a entidade que deseja fazer o ajuste
    Então é listado o saldo atual por lote cadastrado
    E a quantidade por lote pode ser editada
    E um novo lote pode ser adicionado
    E um totalizador com o novo saldo total é exibido
    Exemplos:
|codigo_produto|
|     1184     |
|     1185     |

Esquema do Cenário: Realizar ajuste de estoque diminuindo saldo em produto de lote
    Dado que o usuário acessa o ajuste de estoque no produto "<codigo_produto>"
    Quando informa a entidade que deseja fazer o ajuste
    Então é listado o saldo atual por lote cadastrado
    E a quantidade por lote pode ser editada
    E o(s) lote(s) pode(m) ser excluído(s)
    E um totalizador com o novo saldo total é exibido
    Exemplos:
|codigo_produto|
|     1184     |

    
Esquema do Cenário: Realizar ajuste de estoque em produto de série
    Dado que o usuário acessa o ajuste de estoque no produto "<codigo_produto>"
    Quando informa a entidade que deseja fazer o ajuste
    Então é listada uma série para cada quantidade existente no estoque
    E é possível adicionar série
    E é possível excluir série existente
    E um totalizador com o novo saldo total é exibido
    Exemplos:
|codigo_produto|
|     1186     |
|     1187     |
 