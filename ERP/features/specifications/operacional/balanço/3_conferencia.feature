
#language: pt

@ERP @balanco
Funcionalidade: ERP - Cadastro, alteração e processamento de balanço de estoque

Contexto: Balanço de estoque
Dado que o usuário acessa o cadastro de balanço


Esquema do Cenário: Validar custo na conferência do balanço
    Dado que o usuário informou os dados iniciais com a opção de custo "<tipo_custo>" e informou os produtos no balanço
    Quando acessa a conferência do balanço
    Então o "<tipo_custo>" exibido de acordo com o "<custo>" considerando a data da contagem informada
    E o "custo_total" será igual ao custo X diferença
    Exemplos:
| tipo_custo|       custo       |
|referencial|  cadastro_produto |
|   medio   |custo_medio_produto|


Cenário: Validar estoque original na conferência do balanço
    Dado que o usuário informou os dados iniciais e os produtos no balanço
    Quando acessa a conferência do balanço
    Então o estoque original dos pordutos é exibido conforme o saldo final no dia da contagem
    


Cenário: Validar preço de venda na conferência do balanço
    Dado que o usuário informou os dados iniciais e os produtos no balanço
    Quando acessa a conferência do balanço
    Então o preço de venda exibido na conferência será igual ao da tabela de padrão
    E o "preço_total" será igual ao preço de venda X diferença