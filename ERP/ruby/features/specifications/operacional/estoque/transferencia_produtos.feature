#language: pt

@ERP @transferencia
Funcionalidade: ERP - Transferência de produtos

Contexto: Nova transferência de produtos
Dado que o usuário inicia uma transferência de produtos
E que as entidades envolvidas são:
|entidade_origem|entidade_destino|    vínculo    |
|  guaramirim2  |     tuareg     | matriz_filial |
|  guaramirim2  |     parfois    |  cnpj_iguais  |
|  guaramirim2  |     simples    |cpnj_diferentes|


Esquema do Cenário: Transferência de produtos utilizando custo médio ou preço de venda
    Dado que o usuário informa a entidade origem "<entidade_origem>" e a entidade destino "<entidade_destino>"
    E que o vínculo entre elas é "<vínculo>"
    Quando informa o tipo de valor "<tipo_valor>"
    E adiciona os produtos
    E confere a transferência
    Então o valor dos produtos na saída da entidade origem e na entrada da entidade destino será igual ao "<tipo_valor>"
Exemplos:
| tipo_valor|
|custo_medio|
|preco_venda|


Esquema do Cenário: Validar valor entrada com diferentes configurações de conferências de produtos
    Dado que a entidade está configurada com conferência de produtos igual a "<conferencia_produto>"
    E a transferência de produtos foi realizada com o tipo de valor é "<tipo_valor>"
    E a entidade destino está configurada como "<conferencia_produto>"
    E os produtos já foram disponibilizados na entrada
    Quando conferir os valores da entrada na entidade de destino
    Então os valores estarão iguais ao "<tipo_valor>" da entidade de origem
    Exemplos:
|conferencia_produto| tipo_valor|
|     automatica    |custo_medio|
|     automatica    |preco_venda|
|       geral       |custo_medio|
|       geral       |preco_venda|
|      produto      |custo_medio|
|      produto      |preco_venda|
|        cega       |custo_medio|
|        cega       |preco_venda|
 