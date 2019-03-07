#language: pt

 @entrada
Funcionalidade: Entrada de nota fiscal no ERP

Contexto: Dado que o usuário inicia uma nova entrada de notas

@tipo_nota @modelo_nota
Esquema do Cenário: Entrada de notas manual validando status da escrituração para modelo 1A, 1, 55 e 4
    Dado que o usuário informou todos os dados da entrada e o tipo da nota é "<tipoNota>"
    E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
    Quando finaliza a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    E a nota fiscal é registrada com status "<statusEscrituracao>"

    Exemplos:
|tipoNota| statusEscrituracao|
|   1A   |ESCRITURADA_EXTERNO|
|    1   |ESCRITURADA_EXTERNO|
|   55   |  NAO_ESCRITURADA  |
|    4   |ESCRITURADA_EXTERNO|


@tipo_nota @entrada_manual @modelo_nota
Cenário: Entrada de notas manual validando status da escrituração para modelo 55 e chave de acesso
    Quando o usuário informar na entrada o tipo de nota "55" com número do documento "562" e série "4" informando chave de acesso
    E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
    E finaliza a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@entrada_XML @modelo_nota
Cenário: Entrada de notas com XML validando status da escrituração
    Quando o usuário preenche os dados iniciais informando um XML
    E que confirma a conciliação, confirma os dados adicionais, confirma os produtos e confirma a visão geral
    E finaliza a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@entrada_XML
Esquema do Cenário: Entrada de notas com XML preenchido com determinado CST/CSOSN
    Dado que o usuário preencheu os dados iniciais informando o "<XML>"
    E que confirma a conciliação, confirma os dados adicionais e confirma os produtos
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
    E que confirma a conciliação, confirma os dados adicionais e confirma os produtos
    E que confirma a visão geral
    Quando finaliza a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "22" e série "10" 
    E a fórmula para geração do custo do produto para a "<tributacao>" será o valor total do item + "<soma_custo>" + "<subtrai_custo>"
    E o custo unitário é a divisão do custo total pela quantidade

Exemplos:
|tributacao|                     soma_custo                     |  subtrai_custo |
|   real   |+IPI+ST+frete+seguro+outras_despesas+custo_adicional|-ICMS-PIS-COFINS|
| presumido|+IPI+ST+frete+seguro+outras_despesas+custo_adicional|      -ICMS     |
|  simples |+IPI+ST+frete+seguro+outras_despesas+custo_adicional|                |



@entrada_XML @custo
Esquema do Cenário: Entrada de notas com XML e custo adicional
    Dado que o usuário preencheu os dados iniciais informando um XML e a entidade é do sistema de tributacao "<tributacao>"
    E que confirma a conciliação
    E que informa custo adicional de "5,00" nos dados adicionais
    Quando confirma os produtos
    Então na visão geral é exibido custo adicional de "5,00"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "22" e série "10" 
    E o item de código "<codigo_item>" com quantidade "<qtde>" e valor total "<valor_total_item1>" utilizará a fórmula: "<soma_custo1>" + "<subtrai_custo>" para chegar ao custo final "<custo_produto>" 

Exemplos:
| tributacao | codigo_item | qtde | valor_total_item | soma_custo                       | subtrai_custo                      | custo_produto | custo_unitario |
| real       | 2541.001    | 2    | 17,46            | +IPI(0,14)+custo_adicional(3,29) | -ICMS(1,22)-PIS(0,29)-COFINS(1,33) | 18,05         | 9,03           |
| presumido  | 2541.001    | 2    | 17,46            | +IPI(0,14)+custo_adicional(3,29) | -ICMS(1,22)                        | 19,67         | 9,84           |
| simples    | 2541.001    | 2    | 17,46            | +IPI(0,14)+custo_adicional(3,29) |                                    | 20,89         | 10,45          |
| real       | 2541.002    | 1    | 9,06             | +IPI(0,07)+custo_adicional(1,71) | -ICMS(0,63)-PIS(0,15)-COFINS(0,69) | 9,37          | 9,37           |
| presumido  | 2541.002    | 1    | 9,06             | +IPI(0,07)+custo_adicional(1,71) | -ICMS(0,63)                        | 10,21         | 10,21          |
| simples    | 2541.002    | 1    | 9,06             | +IPI(0,07)+custo_adicional(1,71) |                                    | 10,84         | 10,45          |



@entrada_manual @custo
Esquema do Cenário: Entrada de notas manual e custo adicional
    Dado que o usuário preencheu manualmente os dados iniciais da entrada e a entidade é do sistema de tributacao "<tributacao>"
    E que informa custo adicional de "5,00" nos dados adicionais
    Quando adiciona e confirma os produtos
    Então na visão geral é exibido custo adicional de "5,00"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    E o item "<codigo_item>" com quantidade "<qtde>" e com valor total do produto "<valor_total_item>" utilizará a fórmula: "<soma_custo>" + "<subtrai_custo>" para chegar ao custo final "<custo_produto>" 
    E o custo unitário é a divisão do custo do produto pela quantidade

Exemplos:
|tributacao|codigo_item|qtde|valor_total_item |      soma_custo        |           subtrai_custo          |custo_produto|custo_unitario |
|   real   |2541.001   |2   |      20,00      |+custo_adicional(3,33)  |-ICMS(2,40)-PIS(0,33)-COFINS(1,52)|    19,08    |     9,54      |
| presumido|2541.001   |2   |      20,00      |+custo_adicional(3,33)  |            -ICMS(2,40)           |    20,93    |     10,47     |
|  simples |2541.001   |2   |      20,00      |+custo_adicional(3,33)  |                                  |    23,33    |     11,67     |
|   real   |2541.002   |1   |      10,00      |+custo_adicional(1,67)  |-ICMS(1,20)-PIS(0,16)-COFINS(0,76)|     9,55    |     9,55      |
| presumido|2541.002   |1   |      10,00      |+custo_adicional(1,67)  |           -ICMS(1,20)            |    10,47    |     10,47     |
|  simples |2541.002   |1   |      10,00      |+custo_adicional(1,67)  |                                  |    11,67    |     11,67     |