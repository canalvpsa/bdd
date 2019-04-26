#language: pt
@ERP @comissao
Funcionalidade: ERP - Apuração de comissão


Cenário: Apuração de comissão com tipo de apuração por vendedor e comissionamento sobre valor faturado
    Dado que o vendedor possui limite de crédito maior que zero
    E a comissão é sobre vendas próprias
    E o tipo da comissão é sobre o valor faturado
    E pelo menos um dos produtos vendidos possui percentual de comissão configurado
    E a venda está conferida no conferência de caixa
    Quando o usuário efetua venda vinculando o vendedor
    Então na apuração de comissão é exibido o produto comissionado e os valores relacionados na coluna de "faturado"
    E o valor da conta a pagar relacionado a comissão é atualizado


Cenário: Apuração de comissão com tipo de apuração por vendedor e comissionamento sobre valor realizado
    Dado que o vendedor possui limite de crédito maior que zero
    E a comissão é sobre vendas próprias
    E o tipo da comissão é sobre o valor faturado
    E pelo menos um dos produtos vendidos possui percentual de comissão configurado
    E a venda está conferida no conferência de caixa
    Quando o usuário efetua venda vinculando o vendedor
    E pelo menos uma das parcelas da venda estiver baixada
    Então na apuração de comissão é exibido o produto comissionado e os valores relacionados de forma proporcional às parcelas pagas na coluna de "realizado"
    Mas se nenhuma parcela da venda estiver paga
    Então na apuração de comissão o produto não é listado


Esquema do Cenário: Validação de percentual de comissão aplicado
    Dado que o produto está configurado com o percentual de "<percentual_produto>"
    E o vendedor está configurado com o percentual de "<percentual_vendedor>"
    Quando é realizada a venda do produto vinculando o vendedor
    Então é aplicado na venda o menor percentual entre eles: "<percentual_comisssão>"

Exemplos:
|percentual_produto|percentual_vendedor|percentual_comisssão|
|        0%        |         2%        |         0%         |
|        2%        |         0%        |         0%         |
|        2%        |         5%        |         2%         |
|        2%        |         1%        |         1%         |
|        2%        |         2%        |         2%         |
 

Cenário: Ajuste de vendedor na venda e vendedor por item habilitado
    Dado que o parâmetro de vendedor por item está habilitado
    E que o vendedor possui comissão para determinado item da venda
    Quando é realizado ajuste de vendedor pela conferência de caixa
    E a venda é associada a outro vendedor
    Então nenhum ajuste é realizado nas comissões dos vendedores


Cenário: Ajuste de vendedor no item e vendedor por item habilitado
    Dado que o parâmetro de vendedor por item está habilitado
    E que o vendedor possui comissão para determinado item da venda
    Quando é realizado ajuste de vendedor pela conferência de caixa
    E um dos itens é associado a outro vendedor
    Então a comissão do item deixa de ser do primeiro vendedor e passa a ser somado na comissão do vendedor informado


Cenário: Ajuste de vendedor e vendedor por item desabilitado
    Dado que o parâmetro de vendedor por item está desabilitado
    E que o vendedor possui comissão para determinado item da venda
    Quando é realizado ajuste de vendedor pela conferência de caixa
    E a venda é associada a outro vendedor
    Então a comissão de todos os itens da venda deixa de ser do primeiro vendedor e passa a ser somado na comissão do vendedor informado


Cenário: Venda pendente de conferência de caixa
    Dado que o plano de pagamento utilizado na venda está configurado para conferência manual
    E que o vendedor possui comissão para determinado item da venda
    Quando é realizada a venda do produto vinculando o vendedor
    Então a venda fica com status "não conferida"
    E a comissão não é gerada para o vendedor


Cenário: Validar conta a pagar para vendedor faturado
    Dado que a venda foi efetuada para o vendedor e foi gerada comissão
    Quando pesquisadas as contas a pagar
    Então será listada uma conta a pagar com a soma de todas as comissões para o vendedor
    E a data de vencimento está de acordo com a configuração do vendedor
    E a conta débito da conta a pagar é "60901 - COMISSÕES E CORRETAGENS"
    E a conta crédito da conta a pagar é "22653 - COMISSÕES E CORRETAGENS A PAGAR"


Cenário: Validar conta a pagar para vendedor realizado com parcelas em aberto
    Dado que a venda foi efetuada para o vendedor e foi gerada comissão
    E as parcelas da venda não foram baixadas
    Quando pesquisadas as contas a pagar
    Então será listada uma conta a pagar com a soma de todas as comissões para o vendedor
    E a data de vencimento está de acordo com a configuração do vendedor
    E a conta débito da conta a pagar é "60901 - COMISSÕES E CORRETAGENS"
    E a conta crédito da conta a pagar é "22651 - COMISSÕES E CORRETAGENS A REALIZAR"


Cenário: Validar conta a pagar para vendedor realizado com parcelas baixadas
    Dado que a venda foi efetuada para o vendedor e foi gerada comissão
    E as parcelas da venda não foram baixadas
    Quando pesquisadas as contas a pagar
    Então será listada uma conta a pagar com a soma de todas as comissões para o vendedor
    E a data de vencimento está de acordo com a configuração do vendedor
    E a conta débito da conta a pagar é "60901 - COMISSÕES E CORRETAGENS"
    E a conta crédito da conta a pagar é "22653 - COMISSÕES E CORRETAGENS A PAGAR"

