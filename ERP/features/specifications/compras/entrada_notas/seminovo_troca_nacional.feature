
#language: pt

 @ERP @entrada
Funcionalidade: ERP - Pesquisa de seminovo e troca nacional nas entradas

Contexto: Pesquisa entrada de notas
Dado que o usuário está na pesquisa de entrada de notas

@seminovo @troca_nacional
Esquema do Cenário: Excluir entrada de notas de seminovo ou troca nacional
    Dado que no PDV foi efetuada uma compra com o tipo "<tipo>"
    E a compra estiver no ERP
    E que o usuário pesquisou as entrada de notas do período
    Quando excluir a entrada com o "<tipo>"
    Então a entrada não será excluída
    E será exibida mensagem: "<mensagem>"

Exemplos:
|     tipo     |                             mensagem                             |
|   seminovo   |Não é possível cancelar uma entrada referente à compra de seminovo|
|troca_nacional|  Não é possível cancelar uma entrada referente à troca nacional  |


