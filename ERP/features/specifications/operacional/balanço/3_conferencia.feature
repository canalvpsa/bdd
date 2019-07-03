
#language: pt

@ERP @balanco
Funcionalidade: ERP - balanço de estoque (conferência)

Contexto: Balanço de estoque
Dado que o usuário acessa o cadastro de balanço


Esquema do Cenário: Validar custo na conferência do balanço
    Dado que o usuário informou os dados iniciais com a opção de custo "<tipo_custo>" e informou os produtos no balanço
    Quando acessa a conferência do balanço
    Então o "<tipo_custo>" exibido de acordo com o "<custo>" considerando a "<regra>"
    E o "custo_total" será igual ao custo X diferença
    Exemplos:
| tipo_custo|       custo       |      regra     |
|referencial|  cadastro_produto |   data atual   |
|   medio   |custo_medio_produto|data da contagem|


Cenário: Validar estoque original na conferência do balanço
    Dado que o usuário informou os dados iniciais e os produtos no balanço
    Quando acessa a conferência do balanço
    Então a "quantidade original" dos produtos é a quantidade em estoque no final do dia da contagem


Cenário: Validar preço de venda na conferência do balanço
    Dado que o usuário informou os dados iniciais e os produtos no balanço
    Quando acessa a conferência do balanço
    Então o "preço de venda" dos produtos é o preço de venda na tabela padrão na data atual
    E o "preço_total" será igual ao preço de venda X diferença