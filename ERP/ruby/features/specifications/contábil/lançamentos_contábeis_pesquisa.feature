
#language: pt
@ERP
Funcionalidade: ERP - Pesquisa de lançamentos contábeis

@troca_nacional @seminovo
Esquema do Cenário: Lançamentos contábeis de troca nacional ou compra de seminovos
    Dado que o usuário finalizou uma troca nacional ou uma compra de seminovos no PDV
|tipo_lançamento|                     conta_debito                    |                      conta_credito                     |
|     compra    | Conta de COMPRA da classificação contábil do produto|Conta de FORNECEDOR da classificação contábil do produto|
|    estoque    |Conta de ESTOQUE da classificação contábil do produto|             TRANSF. MERCADORIAS P/ ESTOQUE             |

    E o PDV foi sincronizado
    Quando a compra estiver no ERP
    Então é realizado lançamento contábil do tipo "<tipo_lançamento>" na conta débito "<conta_debito>" e na conta crédito "<conta_credito>"
    E o histórico destes lançamentos contábeis é : "<operacao>" + "nroCompra (substituir por NFe quando emitida)"
    
    Exemplos:
|   operacao   |
|   seminovo   |
|troca_nacional|


@troca_nacional @seminovo
Esquema do Cenário: Lançamento contábil de despesa efetuada no PDV
    Dado que o usuário efetuou um lançamento de despesa no PDV
    E o PDV foi sincronizado
    Quando a despesa (sangria) é exibida no ERP
    Então é realizado lançamento contábil de despesa na conta débito "<despesa_debito>" e na conta crédito "<despesa_credito>"
    E o histórico do lançamento é exibido conforme o motivo informado no PDV

    Exemplos:
|          despesa_debito         |despesa_credito|
|Conta de DESPESA informada no PDV|     Caixa     |


@troca_nacional @seminovo @adiantamento
Esquema do Cenário: Lançamento contábil de adiantamento gerado em compra do PDV
    Dado que o usuário efetuou uma compra com pagamento em adiantamento
    E o PDV foi sincronizado
    Quando a compra é exibida no ERP
    Então é realizado lançamento contábil da geração do adiantamento na conta débito "<conta_debito>" e na conta crédito "<conta_credito>" com valor "<valor>"
    E o documento é do tipo "sinal" e o número do documento é igual ao número da compra
Exemplos:
|conta_debito|          conta_credito          |    valor   |
|    Caixa   |ADIANTAMENTO DE CLIENTES DIVERSOS|valor_compra|



 @simples_faturamento @entrega_futura @faturamento_antecipado
Esquema do Cenário: Lançamento contábil de simples faturamento
    Dado que o usuário efetuou uma venda de simples faturamento
    E todos os produtos reservam saldo atual (entrega futura)
    Quando a venda é confirmada
    Então é realizado lançamento contábil do tipo "<tipo_lançamento>" na conta débito "<conta_debito>" e na conta crédito "<conta_credito>"
|tipo_lançamento|                         conta_debito                        |                       conta_credito                       |
|   financeiro  |Conta de CONTA A RECEBER da classificação contábil do produto|Conta de CONTA RECEITA da classificação contábil do produto|


 @simples_faturamento @entrega_futura @faturamento_antecipado
Esquema do Cenário: Lançamento contábil de simples faturamento
    Dado que o usuário efetuou uma venda de simples faturamento
    E todos os produtos não reservam saldo atual (faturamento antecipado)
    Quando a venda é confirmada
    Então é realizado lançamento contábil do tipo "<tipo_lançamento>" na conta débito "<conta_debito>" e na conta crédito "<conta_credito>"
|tipo_lançamento|                              conta_debito                              |                       conta_credito                       |
|   financeiro  |Conta de FATURAMENTO ANTECIPADO informado no parâmetro de conta contábil|Conta de CONTA RECEITA da classificação contábil do produto|
