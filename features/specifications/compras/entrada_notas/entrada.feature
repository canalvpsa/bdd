#language: pt

 @entrada
Funcionalidade: Entrada de nota fiscal no ERP

Contexto: Dado que o usuário inicia uma nova entrada de notas

@tipo_nota
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


@tipo_nota @entrada_manual
Cenário: Entrada de notas com tipo de nota 55 com chave
    Quando o usuário informar na entrada o tipo de nota "55"
    E informar chave de acesso
    E finalizar a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@entrada_XML
Cenário: Entrada de notas com XML
    Quando o usuário preenche os dados iniciais informando um XML
    E finalizar a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@entrada_XML
Esquema do Cenário: Entrada de notas com XML preenchido com determinado CST/CSOSN
    Dado que o usuário preencheu os dados iniciais informando o "<XML>"
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado "<XML>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 

    Exemplos:
|         XML         |numeroDocumento|serie|baseICMS|valorICMS|baseICMSST|valorICMSST|BaseICMSSN|valorICMSSN|totalProduto|totalNota|
|  CST00_correto.xml  |       17      |  10 |  8,73  |   1,05  |   0,00   |    0,00   |     -    |     -     |    8,73    |   8,80  |
| CST00_incorreto.xml |       17      |  10 |  0,00  |   0,00  |   0,00   |    0,00   |     -    |     -     |    8,73    |   8,80  |
|  CST10_correto.xml  |       8       |  10 |  8,73  |   0,61  |   11,35  |    0,18   |     -    |     -     |    8,73    |   8,91  |
| CST10_incorreto.xml |       9       |  10 |  8,73  |   0,61  |   0,00   |    0,00   |   0,00   |    0,00   |    8,73    |   8,73  |
|  CST51_correto.xml  |       6       |  10 |  0,00  |   0,00  |   0,00   |    0,00   |     -    |     -     |    8,73    |   8,73  |
|ICMSSN101_correto.xml|      105      |  2  |  0,00  |   0,00  |   0,00   |    0,00   |   26,52  |    5,04   |    26,52   |  26,52  |
|ICMSSN202_correto.xml|      101      |  2  |  0,00  |   0,00  |   22,60  |    1,72   |     -    |     -     |    17,79   |  19,50  |


@entrada_XML @custo
Esquema do Cenário: Entrada de notas com XML e validação de custo do produto
    Dado que o usuário preencheu os dados iniciais informando um XML e a entidade é do sistema de tributacao "<tributacao>"
    Quando confirma os produtos
    Então ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "22" e série "10" 
    E a fórmula para geração do custo do produto para a "<tributacao>" será o valor total do item + "<soma_custo>" + "<subtrai_custo>"
    E o custo unitário é a divisão do custo total pela quantidade

Exemplos:
|tributacao|                     soma_custo                     |  subtrai_custo |
|   real   |+IPI+ST+frete+seguro+outras_despesas+custo_adicional|-ICMS-PIS-COFINS|
| presumido|+IPI+ST+frete+seguro+outras_despesas+custo_adicional|      -ICMS     |
|  simples |+IPI+ST+frete+seguro+outras_despesas+custo_adicional|        -       |



@entrada_XML @custo
Esquema do Cenário: Entrada de notas com XML e custo adicional
    Dado que o usuário preencheu os dados iniciais informando um XML e a entidade é do sistema de tributacao "<tributacao>"
    Quando informa custo adicional de "5,00" nos dados adicionais
    E confirma os produtos
    Então na visão geral é exibido custo adicional de "5,00"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "22" e série "10" 
    E o item "<codigo_item>" com quantidade "<quantidade>" e valor total "<valor_total_item>" utilizará a fórmula: "<soma_custo>" + "<subtrai_custo>" para chegar ao custo final "<custo_produto>" 

Exemplos:
|codigo_item|tributacao|valor_total_item|           soma_custo           |           subtrai_custo          |custo_produto|quantidade|custo_unitario|
|  2541.001 |   real   |      17,46     |+IPI(0,14)+custo_adicional(3,29)|-ICMS(1,22)-PIS(0,29)-COFINS(1,33)|    18,05    |     2    |     9,03     |
|  2541.002 |   real   |      9,06      |+IPI(0,07)+custo_adicional(1,71)|-ICMS(0,63)-PIS(0,15)-COFINS(0,69)|     9,37    |     1    |     9,37     |
|  2541.001 | presumido|      17,46     |+IPI(0,14)+custo_adicional(3,29)|            -ICMS(1,22)           |    19,67    |     2    |     9,84     |
|  2541.002 | presumido|      9,06      |+IPI(0,07)+custo_adicional(1,71)|            -ICMS(0,63)           |    10,21    |     1    |     10,21    |
|  2541.001 |  simples |      17,46     |+IPI(0,14)+custo_adicional(3,29)|                 -                |    20,89    |     2    |     10,45    |
|  2541.002 |  simples |      9,06      |+IPI(0,07)+custo_adicional(1,71)|                 -                |    10,84    |     1    |     10,45    |



@entrada_manual @custo
Esquema do Cenário: Entrada de notas manual e custo adicional
    Dado que o usuário preencheu manualmente os dados iniciais da entrada e a entidade é do sistema de tributacao "<tributacao>"
    Quando informa custo adicional de "5,00" nos dados adicionais
    E inclui os produtos para compra
    Então na visão geral é exibido custo adicional de "5,00"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    E o item "<codigo_item>" com valor total do produto "<valor_total_item>" utilizará a fórmula: "<soma_custo>" + "<subtrai_custo>" para chegar ao custo final "<custo_produto>" 
    E o custo unitário é a divisão do "<custo_produto>" pela quantidade "<quantidade>"

Exemplos:
|codigo_item|tributacao|valor_total_item|      soma_custo      |           subtrai_custo          |custo_produto|quantidade|custo_unitario|
|  2541.001 |   real   |      20,00     |+custo_adicional(3,33)|-ICMS(2,40)-PIS(0,33)-COFINS(1,52)|    19,08    |     2    |     9,54     |
|  2541.002 |   real   |      10,00     |+custo_adicional(1,67)|-ICMS(1,20)-PIS(0,16)-COFINS(0,76)|     9,55    |     1    |     9,55     |
|  2541.001 | presumido|      20,00     |+custo_adicional(3,33)|            -ICMS(2,40)           |    20,93    |     2    |     10,47    |
|  2541.002 | presumido|      10,00     |+custo_adicional(1,67)|           -ICMS(1,20))           |    10,47    |     1    |     10,47    |
|  2541.001 |  simples |      20,00     |+custo_adicional(3,33)|                 -                |    23,33    |     2    |     11,67    |
|  2541.002 |  simples |      10,00     |+custo_adicional(1,67)|                 -                |    11,67    |     1    |     11,67    |