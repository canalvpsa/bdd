
#language: pt

Funcionalidade: Pesquisa de lançamentos contábeis

@troca_nacional @seminovo
Esquema do Cenário: Lançamentos contábeis de troca nacional ou compra de seminovos
    Dado que o usuário finalizou uma troca nacional ou uma compra de seminovos no PDV
    E o PDV foi sincronizado
    Quando a compra estiver no ERP
    Então é realizado lançamento contábil da compra na conta débito "<compra_debito>" e na conta crédito "<compra_credito>"
    E é realizado lançamento contábil da compra na conta débito "<estoque_debito>" e na conta crédito "<estoque_credito>" 
    E o histórico destes lançamentos contábeis é : "<operacao>" + "nroCompra (substituir por NFe quando emitida)"
    
    Exemplos:
|   operacao   |
|   seminovo   |
|troca_nacional|

Exemplos:
|                    compra_debito                   |                     compra_credito                     |
|Conta de COMPRA da classificação contábil do produto|Conta de FORNECEDOR da classificação contábil do produto|

Exemplos:
|                    estoque_debito                   |        estoque_credito       |
|Conta de ESTOQUE da classificação contábil do produto|TRANSF. MERCADORIAS P/ ESTOQUE|



@troca_nacional @seminovo
Cenário: Lançamento contábil de despesa efetuada no PDV
    Dado que o usuário efetuou um lançamento de despesa no PDV
    E o PDV foi sincronizado
    Quando a despesa (sangria) é exibida no ERP
    Então é realizado lançamento contábil de despesa na conta débito "<despesa_debito>" e na conta crédito "<despesa_credito>"
    E o histórico do lançamento é exibido conforme o motivo informado no PDV

    Exemplos:
|          despesa_debito         |despesa_credito|
|Conta de DESPESA informada no PDV|     Caixa     |

