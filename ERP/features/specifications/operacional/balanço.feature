#language: pt

@ERP @produto
Funcionalidade: ERP - Cadastro, alteração e processamento de balanço de estoque

Contexto: Dado que o usuário acessa o balanço de estoque

Esquema do Cenário: Excluir balanço não processado
    Dado que o usuário possui permissão apenas para cadastrar e editar balanços
    Quando o usuário excluir balanço com status "<status>"
    Então o balanço é excluído com sucesso e uma mensagem é exibida

Exemplos:
|  status |
|Em aberto|


Esquema do Cenário: Excluir balanço processado
    Dado que o usuário possui permissão apenas para processar balanços
    Quando o usuário excluir balanço com status "<status>"
    Então o balanço é excluído com sucesso e uma mensagem é exibida

Exemplos:
|     status     |
|    Em aberto   |
|Em processamento|
|      Erro      |
|   Processado   |


Esquema do Cenário: Criar balanço via importação de arquivo informando tipo de código
    Dado que o usuário inicia um novo balanço
    Quando aciona a importação de arquivo
    E informa o tipo de código a ser conciliado "<tipo_codigo>"
    E importa um arquivo de balanço
    Então os itens são listados de acordo com tipo de código informado

Exemplos:
|tipo_codigo|
|código de barras|
|código interno|
|sku|


Cenário: Criar balanço via importação de arquivo sem informar tipo de código
    Dado que o usuário inicia um novo balanço
    Quando aciona a importação de arquivo
    E não informa um tipo de código
    E um texto com a precedência é exibido para o usuário
    E importa um arquivo de balanço
    Então os itens são localizados seguindo a precedência: Código de barras, código interno e SKU


Cenário: Criar balanço via importação de arquivo e itens não encontrados
    Dado que o usuário inicia um novo balanço
    E informa o tipo de código a ser conciliado "<tipo_codigo>"
    E importa um arquivo que contém códigos não cadastrados no ERP
    Quando importa um arquivo que contém códigos não cadastrados no ERP
    Então os itens localizados são listados no balanço
    E uma mensagem de itens não localizados devem ser exibida
    E deve ser possível exportar arquivo com os itens não localizados
    E o balanço pode ser processado


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