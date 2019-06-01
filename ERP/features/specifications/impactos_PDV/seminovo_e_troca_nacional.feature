#language: pt

@ERP @nao_automatizado
Funcionalidade: ERP - Impactos da compra de seminovo e troca nacional no PDV


@seminovo @troca_nacional
Cenário: Configurar operação de "Compra p/ revenda"
    Dado que existe a operação de "compra p/ revenda" na listagem de operações de entrada
    Quando o usuário realizar a configuração
    Então a operação é exibida como configurada
    E a configuração da operação é sincronizada com o ERP


@seminovo @troca_nacional
Cenário: Terceiro sem endereço configurado
    Dado que foi efetuada compra no PDV informando terceiro sem endereço configurado
    Quando a compra estiver no ERP
    Então os impostos são lançados utilizando a configuração interna da operação (UF da entidade)


@seminovo @custo
Cenário: Impactos no ERP do seminovo efetuado no PDV
    Dado que o usuário finalizou uma compra de seminovo no ERP
    E o PDV foi sincronizado
    Quando a compra estiver no ERP
    Então é lançada nota de entrada com operação "compra p/ revenda"
    E a nota não é exibida na movimentação de entrada até que tenha sido emitida a nota fiscal
    E o produto entra no estoque da entidade
    E o custo médio do produto será calculado com base no valor da entrada
    E é gerada pendência de nota fiscal na funcionalidade "entrada mov. própria"
    E nenhum lançamento de contas a pagar ou a receber é realizado
    E a compra é exibida na pesquisa de "entrada de notas" destacando o tipo "compra de seminovo"
   

@seminovo @troca_nacional
Cenário: Compra no PDV pago em dinheiro
    Dado que o usuário finalizou uma compra de seminovo ou troca nacional no PDV em dinheiro
    E o PDV foi sincronizado
    Quando a compra estiver no ERP
    Então no ERP a despesa é exibida como sangria
    E um lançamento contábil de sangria é realizado


@seminovo @troca_nacional
Cenário: Compra no PDV pago com adiantamento
    Dado que o usuário finalizou uma compra de seminovo ou troca nacional no PDV com adiantamento
    E o PDV foi sincronizado
    Quando a compra estiver no ERP
    E na conferência de caixa o valor não é somado na forma de pagamento "dinheiro"
    E o terceiro recebe valor de adiantamento a utilizar
    E o lançamento contábil será o de geração de adiantamento



@troca_nacional @custo
Cenário: Impactos no ERP da troca nacional efetuado no PDV
    Dado que o usuário finalizou uma troca nacional no ERP
    E o PDV foi sincronizado
    Quando a compra estiver no ERP
    Então é lançada nota de entrada com operação "compra p/ revenda"
    E a nota não é exibida na movimentação de entrada até que tenha sido emitida a nota fiscal
    E o produto entra no estoque da entidade
    E o custo médio do produto não deve sofrer alteração
    E é gerada pendência de nota fiscal na funcionalidade "entrada mov. própria"
    E nenhum lançamento de contas a pagar ou a receber é realizado
    E a compra é exibida na pesquisa de "entrada de notas" destacando o tipo "Troca nacional"


@seminovo @troca_nacional @cancelamento_seminovo @cancelamento_troca_nacional
Cenário: Cancelamento de compra do PDV com adiantamento em aberto
    Dado que foi realizada uma compra de seminovo ou troca nacional no PDV
    E o pagamento foi efetuado com adiantamento
    E que foi emitida nota fiscal desta compra no ERP
    E o adiantamento está em aberto
    E o cancelamento é realizado no PDV
    Quando o ERP recebe o pedido de cancelamento
    Então verifica a situação do adiantamento
    E realiza o cancelamento da NFe
    E realiza a exclusão dos lançamentos contábeis de compra e de estoque
    E realiza o cancelamento do adiantamento em aberto
    E envia o cancelamento para o PDV


@seminovo @cancelamento_seminovo @cancelamento_seminovo @cancelamento_troca_nacional
Cenário: Cancelamento de compra do PDV com adiantamento utilizado
    Dado que foi realizada uma compra de seminovo ou troca nacional no PDV
    E o pagamento foi efetuado com adiantamento
    E que foi emitida nota fiscal desta compra no ERP
    E o adiantamento já foi utilizado
    E o cancelamento é realizado no PDV
    Quando o ERP recebe o pedido de cancelamento
    Então verifica a situação do adiantamento
    E o ERP retorna ao PDV que a venda não pode ser cancelada porque o adiantamento já foi utilizado



@seminovo @cancelamento_seminovo 
Cenário: Cancelamento de compra de seminovo com pagamento em dinheiro
    Dado que foi realizada uma compra de seminovo no PDV
    E o pagamento foi efetuado com dinheiro
    E que foi emitida nota fiscal desta compra no ERP
    E o cancelamento é realizado no PDV
    Quando o ERP recebe o pedido de cancelamento
    Então nenhuma operação é realizada com relação ao pagamento em dinheiro



@seminovo @cancelamento_seminovo @cancelamento_seminovo @cancelamento_troca_nacional
Cenário: Cancelamento de compra do PDV com NFe emitida e erro no cancelamento
    Dado que foi realizada uma compra de seminovo ou troca nacional no PDV
    E que foi emitida nota fiscal desta compra no ERP
    E o cancelamento é realizado no PDV
    E o ERP recebe o pedido de cancelamento
    Quando o ERP realiza o cancelamento da NFe
    E ocorre erro ao realizar cancelamento
    Então o ERP retorna ao PDV que a venda não pode ser cancelada exibindo a mensagem exibida para cancelamento da NFe