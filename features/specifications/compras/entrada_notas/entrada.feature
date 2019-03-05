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
|tipoNota| statusEscrituracao|
|   1A   |ESCRITURADA_EXTERNO|
|    1   |ESCRITURADA_EXTERNO|
|   55   |  NAO_ESCRITURADA  |
|    4   |ESCRITURADA_EXTERNO|


@tipoNota @entradaManual
Cenário: Entrada de notas com tipo de nota 55 com chave
    Quando o usuário informar na entrada o tipo de nota "55"
    E informar chave de acesso
    E finalizar a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@entradaXML
Cenário: Entrada de notas com XML
    Quando o usuário preenche os dados iniciais informando um XML
    E finalizar a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@entradaXML
Esquema do Cenário: Entrada de notas com XML preenchido com determinado CST/CSOSN
    Dado que o usuário preencheu os dados iniciais informando o "<XML>"
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado "<XML>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 

    Exemplos:
|         XML         |numeroDocumento|serie|
|  CST00_correto.xml  |       17      |  10 |
| CST00_incorreto.xml |       17      |  10 |
|  CST10_correto.xml  |       8       |  10 |
| CST10_incorreto.xml |       9       |  10 |
|  CST51_correto.xml  |       6       |  10 |
|ICMSSN101_correto.xml|      105      |  2  |
|ICMSSN202_correto.xml|      101      |  2  |


@entradaXML @custoEntrada
Esquema do Cenário: Entrada de notas com XML e custo adicional
    Dado que o usuário preencheu os dados iniciais informando um XML e a entidade é do sistema de tributacao "<tributacao>"
    Quando informa custo adicional de "5,00" nos dados adicionais
    E confirma os produtos
    Então na visão geral é exibido custo adicional de "5,00"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "22" e série "10" 
    E o custo total do item "2541.001" da nota será "<custoItem1>"
    E o custo total do item "2541.002" da nota será "<custoItem2>"

    Exemplos:
|tributacao|custoItem1|custoItem2|
|   real   |   18,05  |   9,37   |
| presumido|   19,67  |   10,21  |
|  simples |   20,89  |   10,84  |


@entradaManual @custoEntrada
Esquema do Cenário: Entrada de notas manual e custo adicional
    Dado que o usuário preencheu manualmente os dados iniciais da entrada e a entidade é do sistema de tributacao "<tributacao>"
    Quando informa custo adicional de "5,00" nos dados adicionais
    E inclui os produtos para compra
    Então na visão geral é exibido custo adicional de "5,00"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    E o custo total do item "2541.001" da nota será "<custoItem1>"
    E o custo total do item "2541.002" da nota será "<custoItem2>"

    Exemplos:
|tributacao|custoItem1|custoItem2|
|   real   |   19,08  |   9,55   |
| presumido|   20,93  |   10,47  |
|  simples |   23,33  |   11,67  |
 