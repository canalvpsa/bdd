#language: pt


Funcionalidade: Validar impacto da compra de seminovo e troca nacional no PDV


@seminovo @trocaNacional
Cenário: Configurar operação de "Compra p/ revenda"
Dado que existe a operação de "compra p/ revenda" na listagem de operações de entrada
Quando eu realizar a configuração
Então a operação é exibida como configurada
E a configuração da operação é sincronizada com o ERP


@seminovo
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
   

@seminovo
Cenário: Compra de seminovo pago em dinheiro
    Dado que o usuário finalizou uma compra de seminovo no ERP em dinheiro
    E o PDV foi sincronizado
    Quando a compra estiver no ERP
    Então no ERP a despesa é exibida como sangria


@seminovo
Cenário: Compra de seminovo pago com adiantamento
    Dado que o usuário finalizou uma compra de seminovo no ERP com adiantamento
    E o PDV foi sincronizado
    Quando a compra estiver no ERP
    E na conferência de caixa o valor é somado na forma de pagamento "dinheiro"
    E o terceiro recebe valor de adiantamento a utilizar
    E o lançamento contábil será o de geração de adiantamento



@trocaNacional
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