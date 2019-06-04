#language: pt

 @ERP @entrada
Funcionalidade: ERP - Entrada de nota fiscal com divergência nos totais do XML

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas
E que os produtos adicionados nas entradas manuais são:
|codigo_item|quantidade|
|  2541.001 |     2    |
|  2541.002 |     1    |

#CP-726  - Validação do total da nota fiscal somando VFCPST que não está nos itens (possui apenas percentual)
#CP-734  - Validação do total da nota fiscal somando VFCPST que está nos totais nos itens

@entrada_XML @total_nota_fiscal
Esquema do Cenário: Entrada de notas com XML com valor que somam no total da nota fiscal
    E que o usuário preencheu os dados iniciais informando o "<XML>" conciliando na nota "codigo_barras" e no sistema "codigo_barras"
    E que confirma a conciliação, confirma os dados adicionais e confirma os produtos
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E os centavos divergentes serão distribuídos entre os itens da nota
    E na visão geral o valor total da nota fiscal é "<total_NF>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 

Exemplos:
|        XML       |numeroDocumento|serie|total_produtos|  tags_somam_nesta_NF  |total_NF|
|vFCPST_NF11805.xml|     11805     |  1  |    2117,82   |vST:376,35 vFCPST:72,39|2.566,56|
|vFCPST_NF11896.xml|     11896     |  1  |    800,70    |vST:137,27 vFCPST:25,26| 963,23 |

 #CP-741  - Desconto aplicado no total era exibido rateado na visão geral dos itens mas exibia divergência acusando somatória dos itens 0
@entrada_XML @total_nota_fiscal @desconto
Esquema do Cenário: Entrada de notas com XML com desconto em um item e em todos os itens
    E que o usuário preencheu os dados iniciais informando o "<XML>" conciliando na nota "codigo_produto" e no sistema "codigo_interno"
    E que confirma a conciliação, confirma os dados adicionais e confirma os produtos
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E na visão geral o valor total dos produtos é "<total_produtos>"
    E na visão geral o valor total do desconto é "<desconto>"
    E na visão geral o valor total da nota fiscal é "<total_NF>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 

Exemplos:
|        XML       |numeroDocumento|serie|total_produtos|desconto|total_NF|
|desconto_NF172.xml|      172      |  10 |     29,95    |  1,94  |  28,25 |

 

#CP-741  - Desconto aplicado no total era exibido rateado na visão geral dos itens mas exibia divergência acusando somatória dos itens 0
@entrada_manual @desconto
Esquema do Cenário: Entrada de notas manual com desconto
    Dado que o usuário informou todos os dados da entrada e o tipo da nota é "1"
    E que informa desconto de "<desconto>" nos dados adicionais
    E que confirma os dados adicionais
    E adiciona os produtos
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E na visão geral o valor total dos produtos é "<total_produtos>"
    E na visão geral o valor total do desconto é "<desconto>"
    E na visão geral o valor total da nota fiscal é "<total_NF>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    Exemplos:
|total_produtos|desconto|total_NF|
|     30,00    |  18,16 |  11,84 |
 