#language: pt

@ERP @balanco
Funcionalidade: ERP - Cadastro, alteração e processamento de balanço de estoque

Contexto: Dado que o usuário acessa o balanço de estoque

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


Cenário: Criar balanço via importação de arquivo sem informar tipo de código
    Dado que o usuário inicia um novo balanço
    Quando aciona a importação de arquivo
    E não informa um tipo de código
    E um texto com a precedência é exibido para o usuário
    E importa um arquivo de balanço
    Então os itens são localizados seguindo a precedência: Código de barras, código interno e SKU



Cenário: Criar balanço com itens não encontrados
    Dado que o usuário está na inserção de itens do balanço 
    E está utilizando o tipo de inserção "<inserção>"
    Quando é informado no balanço um produto não cadastrado no ERP
    Então deve ser exibido um quadro de "produtos não localizados"
    E cada item não localizado deve exibir a mensagem "<mensagem>"
    E permitir que o usuário acesse o cadastro de produtos pelo balanço
|         inserção         |                  mensagem                  |
|          arquivo         |   Linha 1 código: 254.61151 quantidade: 5  |
|  manual_insercao_rapida  |       Sequencia: 4 código: 254.61151       |
|manual_sem_insercao_rapida|Sequencia: 4 código: 254.61151 quantidade: 3|


Cenário: Criar balanço via importação de arquivo e itens com quantidade maior que 1
    Dado que o usuário inicia um novo balanço
    E informa o tipo de código a ser conciliado
    E informa um arquivo preenchido com quantidade maior que 1 e linhas repetidas para o mesmo código de produto:
| CÓDIGO|QUANTIDADE AFERIDA|
|g2.0010|         3        |
|g2.0022|         1        |
|g2.0010|         2        |
    Quando importar o arquivo
    Então os itens localizados são listados no balanço
    E para cada quantidade o produto é listado uma vez
    E as seguintes colunas são exibidas na listagem
    
|sequencia|sku|codigo_barras|codigo_interno|        nome_produto       |
|    1    | - |      -      |    g2.0010   |  Blusa manga curta P azul |
|    2    | - |      -      |    g2.0010   |  Blusa manga curta P azul |
|    3    | - |      -      |    g2.0010   |  Blusa manga curta P azul |
|    4    | - |      -      |    g2.0022   |Blusa manga curta M amarelo|
|    5    | - |      -      |    g2.0010   |  Blusa manga curta P azul |
|    6    | - |      -      |    g2.0010   |  Blusa manga curta P azul |


Cenário: Criar balanço com inserção manual e opção de inserção rápida habilitado
    Dado que o usuário inicia um novo balanço e informa os dados iniciais do balanço
    Quando adicionar um item ao balanço
    Então o item é exibido na listagem com o código de sequencia em que foi adicionado
    Quando importar o arquivo
    Então os itens localizados são listados no balanço
    E para cada quantidade o produto é listado uma vez
    E as seguintes colunas são exibidas na listagem
  
|sequencia|sku|codigo_barras|codigo_interno|        nome_produto       |
|    1    | - |      -      |    g2.0010   |  Blusa manga curta P azul |
|    2    | - |      -      |    g2.0010   |  Blusa manga curta P azul |
|    3    | - |      -      |    g2.0010   |  Blusa manga curta P azul |
|    4    | - |      -      |    g2.0022   |Blusa manga curta M amarelo|
|    5    | - |      -      |    g2.0010   |  Blusa manga curta P azul |
|    6    | - |      -      |    g2.0010   |  Blusa manga curta P azul |

Cenário: Criar balanço via importação de arquivo com lote e série
    Dado que o usuário inicia um novo balanço
    Quando importa um arquivo que contém código de produto em série ou lote
    Então antes de salvar o balanço é obrigatório preencher a série e/ou lote dos produtos


Cenário: Criar balanço com inserção manual
    Dado que o usuário inicia um novo balanço
    E informa entidade
    E informa descrição do balanço
    E informa data da contagem


Cenário: Alterar balanço

Cenário: Validar listagem do balanço

Cenário: Processar balanço


Esquema do Cenário: Excluir balanço não processado
    Dado que o usuário possui permissão apenas para cadastrar e editar balanços
    Quando o usuário excluir balanço com status "<status>"
    Então o balanço é excluído com sucesso e uma mensagem é exibida

Exemplos:
| status |
|Pendente|


Esquema do Cenário: Excluir balanço processado
    Dado que o usuário possui permissão apenas para processar balanços
    Quando o usuário excluir balanço com status "<status>"
    Então o balanço é excluído com sucesso e uma mensagem é exibida

Exemplos:
|     status     |
|    Pendente    |
|Em processamento|
|      Erro      |
|   Processado   |
