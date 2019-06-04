#language: pt

 @ERP @entrada
Funcionalidade: ERP - Entrada de nota fiscal com divergência nos totais do XML

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas

#CP-719  - Valor total dos produtos não bate com a somatória dos itens devido a casas decimais do sistema
#CP-733  - Arredondamento com a aplicação da diferença dos totais dos produtos, causando divergência.
#Agora o server irá enviar 4 casas decimais ao estoque, aplicando apenas a diferença dos totais dos produtos, seja ela positiva ou negativa.
#CP-738  - Parâmetro de custo: 2 casas decimais e divergência no total dos produtos
#CP-742  - Parâmetro de custo: 4 casas decimais e a nota possui 5 casas decimais causando divergência no total dos produtos

@entrada_XML @total_produtos
Esquema do Cenário: Entrada de notas com XML e diferença de total de produtos
    Dado que o parâmetro de custo do ERP está configurado para <casas_decimais> casas decimais
    E que o usuário preencheu os dados iniciais informando o "<XML>" conciliando na nota "codigo_barras" e no sistema "codigo_barras"
    E que confirma a conciliação, confirma os dados adicionais e confirma os produtos
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E os centavos divergentes serão distribuídos entre os itens da nota
    E na visão geral o valor total dos produtos é "<total_produtos>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 

Exemplos:
|casas_decimais|            XML            |total_produtos|numeroDocumento|serie|
|       2      | total_produtos_NF13154.xml|   4.015,66   |     13154     |  1  |
|       5      | total_produtos_NF13154.xml|   4.015,66   |     13154     |  1  |
|       4      | total_produtos_NF13378.xml|   5.034,25   |     13378     |  1  |
|       2      | total_produtos_NF13378.xml|   5.034,25   |     13378     |  1  |
|       2      | total_produtos_NF13742.xml|   4.372,52   |     13742     |  1  |
|       5      | total_produtos_NF13742.xml|   4.372,52   |     13742     |  1  |
|       2      | total_produtos_NF34880.xml|    927,53    |     34880     |  1  |
|       5      | total_produtos_NF34880.xml|    927,53    |     34880     |  1  |
|       2      |total_produtos_NF109916.xml|   3.093,48   |     109916    |  1  |
|       5      |total_produtos_NF109916.xml|   3.093,48   |     109916    |  1  |