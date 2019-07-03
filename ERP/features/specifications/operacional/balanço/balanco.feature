#language: pt

@ERP @balanco
Funcionalidade: ERP - Balanço de estoque


Contexto: Ajuste de estoque em produto 
    Dado que o usuário acessa o balanço de estoque


Cenário: Validar permissão de usuário para cadastrar balanço
    Dado que o usuário possui permissão para cadastrar balanço de estoque
    Quando o usuário acessa o balanço de estoque
    Então é exibida opção para criar, alterar e excluir balanço
   

Cenário: Validar permissão de usuário para processar no cadastro do balanço
    Dado que o usuário possui permissão para cadastrar e para processar balanço
    E que o usuário informou os dados iniciais e os produtos
    Quando acessa a conferência do balanço
    Então pode processar o balanço, além de salvar


Cenário: Processar balanço na pesquisa do balanço
    Dado que o usuário possui permissão para processar balanço
    E que o usuário selecionou balanço com status "pendente"
    Quando processar o balanço
    Então o balanço será processado


Esquema do Cenário: Alterar balanço não processado
    Dado que o usuário selecionou balanço com status "<status>"
    Quando acessa a edição do balanço
    Então o balanço é exibido para edição
Exemplos:
| status |
|Pendente|
|  Erro  |


Esquema do Cenário: Alterar balanço processado
    Dado que o usuário selecionou balanço com status "<status>"
    Quando acessa a edição do balanço
    Então é exibida a mensagem "Não é possível editar balanço de estoque processado"
Exemplos:
|     status     |
|    Pendente    |
|Em processamento|


Cenário: Excluir balanço não processado
    Dado que o usuário possui permissão para cadastrar balanço de estoque
    E que o usuário selecionou balanço com status "pendente"
    Quando acessa a exclusão do balanço
    Então o balanço é excluído com sucesso
    E uma mensagem é exibida


Cenário: Excluir balanço processado sem permissão para processar balanço
    Dado que o usuário possui permissão para cadastrar balanço de estoque
    E que o usuário selecionou balanço com status "Processado"
    Quando o usuário excluir balanço
    Então é exibida a mensagem "Usuário sem permissão para excluir balanço processado"


Cenário: Excluir balanço processado sem nota(s) fiscal(is) emitida(s)
    Dado que o usuário possui permissão para processar balanço de estoque
    E que o usuário selecionou balanço com status "Processado"
    Quando o usuário excluir balanço
    E confirmar a exclusão
    Então é exibida mensagem de sucesso
    E as movimentações de estoque são excluídas
    E os lançamentos contábeis são excluídos
    E o balanço de estoque é excluído


Cenário: Excluir balanço processado com nota(s) fiscal(is) emitida(s)
Dado que o usuário possui permissão para processar balanço de estoque
    E que o usuário selecionou balanço com status "Processado"
    E o balanço selecionado possui nota fiscal emitida
    Quando o usuário excluir balanço
    E confirmar a exclusão
    Então é efetuada tentativa de cancelamento da(s) nota(s) fiscal(s) da nota mais antiga para a mais nova
    E é exibida mensagem de sucesso
    E as movimentações de estoque são excluídas
    E os lançamentos contábeis são excluídos
    E o balanço de estoque é excluído
    Mas se alguma das notas fiscais não puder ser cancelada
    Então uma mensagem é exibida e nenhuma exclusão é realizada



Cenário: Validar impactos do processamento de balanço
    Dado que o usuário cadastrou um balanço com os produtos "<codigo_produto>" com as quantidades "<novo_saldo>"
|codigo_produto|saldo_atual|novo_saldo|diferença|movimentação|
| BAV15106.0016|     17    |    19    |    2    |   entrada  |
| V12BL150.0002|     7     |     6    |    -1   |    saida   |
|  V11S01.0005 |     4     |     0    |    -4   |    saida   |
| V11TOP02.0002|     -2    |     0    |    2    |   entrada  |
| V11TOP02.0003|     -2    |     3    |    5    |   entrada  |
|  V11S01.0002 |     2     |     2    |    0    |      -     |
    E acionou o processamento do balanço
    Quando confirma o processamento do balanço
    Então ocorre uma movimentação de "<movimentação>" com quantidade "<diferença>" no produto "<codigo_produto>"
    E é realizado um lançamento contábil totalizando as entradas na conta débito "14201 MERCADORIAS" e conta crédito "51401 CUSTO DAS MERCADORIAS VENDIDAS" no valor total de  "298,68"
    E é realizado um lançamento contábil totalizando as saidas na conta débito "51401 CUSTO DAS MERCADORIAS VENDIDAS" e conta crédito "14201 MERCADORIAS" no valor total de "109,23"
    E são geradas duas pendências de nota fiscal na funcionalidade "ajuste de estoque", uma para entrada e outra para saída