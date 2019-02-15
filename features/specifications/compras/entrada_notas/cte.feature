#language: pt


Funcionalidade: Registrar um CTe no ERP

Contexto: Dado que o usuário inicia uma nova entrada de notas

Cenário: Entrada com CTE para entidades de diferentes modalidades de tributação
    Quando preenche os dados iniciais da entrada e a entidade é da modalidade de tributação "<modalidade_tributacao>"
    E realiza o preenchimento dos dados do CTE
    Então será possível preencher os impostos
    E os lançamentos contábeis dos "<impostos>" relacionados ao CTe devem ser efetuados

|modalidade_tributacao|impostos       |
|simples_nacional     |nenhum         |
|lucro_presumido      |icms           |
|lucro_real           |icms,pis,cofins|


Cenário: Tipos de nota que ficam "não escrituradas"
    Quando o usuário informa todos os dados da entrada e o tipo da nota é "<tipo_nota>"
    E finalizar a entrada associando um CTE
    Então a entrada é concluida
    E além da nota fiscal, será registrada CTE com status "NAO_ESCRITURADA"
   

    |tipo_nota|
    |1A       |
    |1        |
    |55       |
    |4        |


Cenário: Entrada de notas com XML
    Quando preenche os dados iniciais informando um XML
    E finaliza a entrada
    Então a entrada é concluida
    E além da nota fiscal, será registrada CTE com status "NAO_ESCRITURADA"


Cenário: Entrada de notas com tipo de nota 55 com chave
    Quando o usuário informar na entrada o tipo de nota "55"
    E informar chave de acesso
    E finalizar a entrada
    Então a entrada é concluida
    E além da nota fiscal, será registrada CTE com status "NAO_ESCRITURADA"