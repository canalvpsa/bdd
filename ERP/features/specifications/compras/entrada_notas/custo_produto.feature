#language: pt

 @ERP @custo
Funcionalidade: ERP - Entrada de nota fiscal no ERP e validação do custo dos produtos

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas
E que os produtos adicionados nas entradas manuais são:
|codigo_item|quantidade|valor_unitario|valor_total|
|  2541.001 |     2    |     10,00    |   20,00   |
|  2541.002 |     1    |     10,00    |   10,00   |
E a fórmula do custo médio para cada tributação é:
|tributacao|                     soma_custo                     |  subtrai_custo |
|   real   |+IPI+ST+frete+seguro+outras_despesas+custo_adicional|-ICMS-PIS-COFINS|
| presumido|+IPI+ST+frete+seguro+outras_despesas+custo_adicional|      -ICMS     |
|  simples |+IPI+ST+frete+seguro+outras_despesas+custo_adicional|        -       |

#Mas se o parâmetro de estoque denominado "Impostos creditáveis não são descontados no custo médio" está habilitado
#Então o comportamento para todas as tributações será igual ao "simples"


@entrada_XML @custo
Esquema do Cenário: Entrada de notas com XML e custo adicional
    Dado que o usuário preencheu os dados iniciais informando um XML e a entidade é do sistema de tributacao "<tributacao>"
    E o parâmetro de estoque denominado "Impostos creditáveis não são descontados no custo médio" está desabilitado
    E que confirma a conciliação
|tributacao|codigo_item1|        soma_custo_item1        |        subtrai_custo_item1       |codigo_item2|        soma_custo_item2        |        subtrai_custo_item2       |
|   real   |  2541.001  |+IPI(0,14)+custo_adicional(3,29)|-ICMS(1,22)-PIS(0,29)-COFINS(1,33)|  2541.002  |+IPI(0,07)+custo_adicional(1,71)|-ICMS(0,63)-PIS(0,15)-COFINS(0,69)|
| presumido|  2541.001  |+IPI(0,14)+custo_adicional(3,29)|            -ICMS(1,22)           |  2541.002  |+IPI(0,07)+custo_adicional(1,71)|            -ICMS(0,63)           |
|  simples |  2541.001  |+IPI(0,14)+custo_adicional(3,29)|                 -                |  2541.002  |+IPI(0,07)+custo_adicional(1,71)|                 -                |
    E que informa custo adicional de "5,00" nos dados adicionais
    Quando confirma os produtos
    Então na visão geral é exibido custo adicional de "5,00"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "22" e série "10" 
    E o custo total do "<codigo_item1>" é "<custo_total_item1>" e do "<codigo_item2>" é "<custo_total_item2>"
    E o custo unitário é a divisão do custo do produto pela quantidade

    Exemplos:
|tributacao|codigo_item|qtde|valor_total_item|           soma_custo           |           subtrai_custo          |custo_produto|custo_unitario|
|   real   |  2541.001 |  2 |      17,46     |+IPI(0,14)+custo_adicional(3,29)|-ICMS(1,22)-PIS(0,29)-COFINS(1,33)|    18,05    |     9,03     |
| presumido|  2541.001 |  2 |      17,46     |+IPI(0,14)+custo_adicional(3,29)|            -ICMS(1,22)           |    19,67    |     9,84     |
|  simples |  2541.001 |  2 |      17,46     |                -               |                 -                |    20,89    |     10,45    |
|   real   |  2541.002 |  1 |      9,06      |+IPI(0,07)+custo_adicional(1,71)|-ICMS(0,63)-PIS(0,15)-COFINS(0,69)|     9,37    |     9,37     |
| presumido|  2541.002 |  1 |      9,06      |+IPI(0,07)+custo_adicional(1,71)|            -ICMS(0,63)           |    10,21    |     10,21    |
|  simples |  2541.002 |  1 |      9,06      |+IPI(0,07)+custo_adicional(1,71)|                 -                |    10,84    |     10,45    |


@entrada_manual @custo
Esquema do Cenário: Entrada de notas manual e custo adicional
    Dado que o usuário preencheu manualmente os dados iniciais da entrada e a entidade é do sistema de tributacao "<tributacao>"
|trib_entidade|codigo_item1|   soma_custo_item1   |        subtrai_custo_item1       |codigo_item2|   soma_custo_item2   |        subtrai_custo_item2       |
|     real    |  2541.001  |+custo_adicional(3,33)|-ICMS(2,40)-PIS(0,33)-COFINS(1,52)|  2541.002  |+custo_adicional(1,67)|-ICMS(1,20)-PIS(0,16)-COFINS(0,76)|
|  presumido  |  2541.001  |+custo_adicional(3,33)|            -ICMS(2,40)           |  2541.002  |+custo_adicional(1,67)|            -ICMS(1,20)           |
|   simples   |  2541.001  |+custo_adicional(3,33)|                 -                |  2541.002  |+custo_adicional(1,67)|                 -                |
    E o parâmetro de estoque denominado "Impostos creditáveis não são descontados no custo médio" está desabilitado
    E que informa custo adicional de "5,00" nos dados adicionais
    Quando adiciona e confirma os produtos
    Então na visão geral é exibido custo adicional de "5,00"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    E o custo total do "<codigo_item1>" é "<custo_total_item1>" e do "<codigo_item2>" é "<custo_total_item2>"
    E o custo unitário é a divisão do custo do produto pela quantidade

    Exemplos:
|tributacao|codigo_item1|custo_unit_item1|custo_total_item1|codigo_item2|custo_uni_item2|custo_total_item2|
|   real   |  2541.001  |      9,03      |      19,08      |  2541.002  |      9,03     |       9,03      |
| presumido|  2541.001  |      9,84      |      20,93      |  2541.002  |      9,84     |       9,84      |
|  simples |  2541.001  |      10,45     |      23,33      |  2541.002  |     10,45     |      10,45      |
