#language: pt

@ERP @balanco
Funcionalidade: ERP - Cadastro, alteração e processamento de balanço de estoque

Contexto: Produtos no balanço de estoque
Dado que o usuário acessa o cadastro de balanço

@balanco_arquivo
Esquema do Cenário: Criar balanço via importação de arquivo informando tipo de código
    Dado que o usuário inicia um novo balanço
    Quando aciona a importação de arquivo
    E informa o tipo de código a ser conciliado "<tipo_codigo>"
    E importa um arquivo de balanço
    Então os itens são listados de acordo com tipo de código informado
Exemplos:
|   tipo_codigo  |
|código de barras|
| código interno |
|       sku      |


@balanco_arquivo
Cenário: Criar balanço via importação de arquivo sem informar tipo de código
    Dado que o usuário inicia um novo balanço
    Quando aciona a importação de arquivo
    E não informa um tipo de código
    E um texto com a precedência é exibido para o usuário
    E importa um arquivo de balanço
    Então os itens são localizados seguindo a precedência: Código de barras, código interno e SKU



@balanco_arquivo
Esquema do Cenário: Criar balanço com itens não encontrados
    Dado que o usuário está na inserção de itens do balanço 
    E está utilizando o tipo de inserção "<inclusao_produto>"
    E a inserção é realizada
    Quando é informado um código de produto não cadastrado no ERP
    Então deve ser exibido um quadro de "produtos não localizados"
    E cada item não localizado deve exibir a mensagem "<mensagem>"
    E permitir que o usuário acesse o cadastro de produtos pelo balanço
    Exemplos:    
|     inclusao_produto     |                  mensagem                  |
|          arquivo         |   Linha 1 código: 254.61151 quantidade: 5  |
|  manual_insercao_rapida  |       Sequencia: 4 código: 254.61151       |
|manual_sem_insercao_rapida|Sequencia: 4 código: 254.61151 quantidade: 3|



Esquema do Cenário: Validar listagem de itens do balanço com importação de arquivo
    Dado que o usuário informou os dados iniciais do balanço
    E está utilizando o tipo de inserção "arquivo":           
|    CÓDIGO   |QUANTIDADE AFERIDA|
|V12BL150.0002|         6        |
| V11S01.0005 |         0        |
|V11TOP02.0002|         0        |
|V11TOP02.0003|         3        |
    Quando confirmar o arquivo
    Então os produtos devem ser exibidos desta forma na listagem:
|sequencia|     sku     |codigo_barras|codigo_interno|     nome_produto    |
|    1    |V12BL150.0002|      -      |       -      |  REGATA PELE BEGE M |
|    2    |V12BL150.0002|      -      |       -      |  REGATA PELE BEGE M |
|    3    |V12BL150.0002|      -      |       -      |  REGATA PELE BEGE M |
|    4    |V12BL150.0002|      -      |       -      |  REGATA PELE BEGE M |
|    5    |V12BL150.0002|      -      |       -      |  REGATA PELE BEGE M |
|    6    |V12BL150.0002|      -      |       -      |  REGATA PELE BEGE M |
|    7    | V11S01.0005 |      -      |       -      |SAIA PREGAS BRANCO PP|
|    8    |V11TOP02.0002|      -      |       -      | TOP NAVY LISTRADO G |
|    9    |V11TOP02.0003|      -      |       -      | TOP NAVY LISTRADO M |
    Quando é verificada a listagem de produtos
    Então devo ver todos os produtos desta lista na ordem informada
    E os produtos com quantidade zerada devem ser exibidos com texto taxado


Esquema do Cenário: Validar listagem de itens do balanço com inserção manual e inserção rápida desabilitada
    Dado que o usuário informou os dados iniciais do balanço
    E está utilizando o tipo de inserção "manual com inserção rápida"
    E informa o "<codigo_produto>" e a quantidade "<quantidade>" nesta ordem:
|codigo_produto|           nome_produto          |quantidade|
| V14BL800.0012|  REGATA CETIM BASICA SALMÃO 36  |     3    |
| 8010690164557|     PEITORAL CRICKET M AZUL     |     1    |
| V14BL800.0012|  REGATA CETIM BASICA SALMÃO 36  |     2    |
| 7890460353098|SAPATO SALTO OURO PRETO / OURO 39|     0    |
    Quando é verificada a listagem de produtos
    Então devo ver todos os produtos desta lista na ordem informada
    E os produtos com quantidade zerada devem ser exibidos com texto taxado
|sequencia|     sku     |codigo_barras|codigo_interno|           nome_produto          |
|    1    |V14BL800.0012|      -      |   2541.011   |  REGATA CETIM BASICA SALMÃO 36  |
|    2    |V14BL800.0012|      -      |   2541.011   |  REGATA CETIM BASICA SALMÃO 36  |
|    3    |V14BL800.0012|      -      |   2541.011   |  REGATA CETIM BASICA SALMÃO 36  |
|    4    | 1053.M.AZUL |8010690164557|       -      |     PEITORAL CRICKET M AZUL     |
|    5    |V14BL800.0012|      -      |   2541.011   |  REGATA CETIM BASICA SALMÃO 36  |
|    6    |V14BL800.0012|      -      |   2541.011   |  REGATA CETIM BASICA SALMÃO 36  |
|    7    | 9705625.0012|7890460353098|       -      |SAPATO SALTO OURO PRETO / OURO 39|
    


Cenário: Validar listagem de itens do balanço com inserção manual e inserção rápida habilitada
    Dado que o usuário informou os dados iniciais do balanço
    E está utilizando o tipo de inserção "manual sem inserção rápida"
    E informa o "<codigo_produto>" nesta ordem:
|sequencia|     sku     |codigo_barras|codigo_interno|         nome_produto        |
|    1    |V14BL800.0012|      -      |   2541.011   |REGATA CETIM BASICA SALMÃO 36|
|    2    |V14BL800.0012|      -      |   2541.011   |REGATA CETIM BASICA SALMÃO 36|
|    3    | 1053.M.AZUL |8010690164557|       -      |   PEITORAL CRICKET M AZUL   |
|    4    |V14BL800.0012|      -      |   2541.011   |REGATA CETIM BASICA SALMÃO 36|
    Quando é verificada a listagem de produtos
    Então devo ver todos os produtos desta lista na ordem informada



Cenário: Criar balanço via importação de arquivo com lote e série
    Dado que o usuário inicia um novo balanço
    Quando importa um arquivo que contém código de produto em série ou lote
    E o arquivo nao tem coluna de lote preenchida
    Então o produto e listado como

Cenário: Criar balanço sem itens com opção de zerar estoque habilitado
    Dado que o usuário inicia um novo balanço
    E a opção para zerar estoque está habilitada
    Quando acessa os produtos do balanço
    Então não é possível prosseguir sem informar ao menos um produto


===================================================================================

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
 