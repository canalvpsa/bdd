#language: pt

 @entrada
Funcionalidade: Entrada de nota fiscal no ERP

Contexto: Dado que o usuário inicia uma nova entrada de notas

@tipoNota
Esquema do Cenário: Tipos de nota que ficam "não escrituradas"
    Dado que o usuário informou todos os dados da entrada e o tipo da nota é "<tipoNota>"
    Quando finalizar a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    E a nota fiscal é registrada com status "<statusEscrituracao>"

    Exemplos:
    |tipoNota|statusEscrituracao |
    |1A      |ESCRITURADA_EXTERNO|
    |1       |ESCRITURADA_EXTERNO|
    |55      |NAO_ESCRITURADA    |
    |4       |ESCRITURADA_EXTERNO|

@tipoNota
Cenário: Entrada de notas com tipo de nota 55 com chave
Quando o usuário informar na entrada o tipo de nota "55"
E informar chave de acesso
E finalizar a entrada
Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@tipoNota
Cenário: Entrada de notas com XML
Quando preenche os dados iniciais informando um XML
E finalizar a entrada
Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@xmlEntrada
Esquema do Cenário: Entrada de notas com XML preenchido com determinado CST/CSOSN
    Dado que o usuário preenche os dados iniciais informando o "<XML>"
    Quando validar os dados da nota fiscal de entrada
    Então na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado "<XML>"
    E nenhuma mensagem de divergência de valores deve ser exibida
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 
  

    Exemplos:
    |XML                  |numeroDocumento|serie|
    |CST10_correto.xml    |8              |10   |
    |CST10_incorreto.xml  |9              |10   |
    |CST51_correto.xml    |6              |10   |
    |ICMSSN202_correto.xml|101            |2    |