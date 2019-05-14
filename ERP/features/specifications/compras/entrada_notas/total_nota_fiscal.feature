#language: pt

 @ERP @entrada
Funcionalidade: ERP - Entrada de nota fiscal com divergência nos totais do XML

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas

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

 