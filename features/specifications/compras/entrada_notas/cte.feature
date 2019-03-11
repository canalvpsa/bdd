#language: pt

@ERP @cte
Funcionalidade: ERP - CTE na entrada

Contexto: Dado que o usuário inicia uma nova entrada de notas

Esquema do Cenário: Entrada com CTE para entidades de diferentes modalidades de tributação
    Quando preenche os dados iniciais da entrada e a entidade é da modalidade de tributação "<modalidade_tributacao>"
    E realiza o preenchimento dos dados do CTE
    Então será possível preencher os impostos
    E os lançamentos contábeis dos "<impostos>" relacionados ao CTe devem ser efetuados

Exemplos:
|modalidade_tributacao|    impostos   |
|   simples_nacional  |     nenhum    |
|   lucro_presumido   |      icms     |
|      lucro_real     |icms,pis,cofins|


Esquema do Cenário: Escrituração de CTe com status "não escrituradas"
    Quando o usuário informa todos os dados da entrada e o tipo da nota é "<tipo_nota>"
    E finalizar a entrada associando um CTE
    Então a entrada é concluida
    E além da nota fiscal, será registrada CTE com status "NAO_ESCRITURADA"

   Exemplos:
|tipoNota|
|   1A   |
|    1   |
|   55   |
|    4   |


Cenário: Entrada de notas com XML e CTe
    Quando preenche os dados iniciais informando um XML
    E informa os dados do CTE
    E finaliza a entrada
    Então a entrada é concluida
    E além da nota fiscal, será registrada CTE com status "NAO_ESCRITURADA"


Cenário: Entrada de notas com tipo de nota 55 com chave e CTe
    Quando o usuário informar na entrada o tipo de nota "55"
    E informar chave de acesso
    E informa os dados do CTE
    E finalizar a entrada
    Então a entrada é concluida
    E além da nota fiscal, será registrada CTE com status "NAO_ESCRITURADA"