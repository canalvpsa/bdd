#language: pt

@ERP
Funcionalidade: ERP - Plano de pagamento de compra

Contexto: Dado que o usuário acessa o plano de pagamento de compra

Cenário: Adicionar um plano de pagamento de compras
    Dado que o usuário informou a descrição, o vencimento, o valor mínimo da parcela e o número de parcelas
    Quando confirmar o cadastro
    Então é exibida a mensagem "Plano de pagamento salvo com sucesso"
    E o plano de pagamento de compras é listado na pesquisa
    

Esquema do Cenário: Adicionar um plano de pagamento de compras com vencimento fixo
    Dado que o usuário informou a descrição
    Quando informar vencimento fixo
    E informar número de parcelas
    E clicar em adicionar
    Então é necessário informar o dia do vencimento e o "<periodo>"

Exemplos:
|  periodo |
|   Anual  |
| Bimestral|
|  Mensal  |
|Trimestral|


Cenário: Adicionar um plano de pagamento de compras com vencimento variável
    Dado que o usuário informou a descrição
    Quando informar vencimento variável
    E informar número de parcelas
    E clicar em adicionar
    Então é necessário informar a quantidade de dias que serão contados da data atual até a data de vencimento


Cenário: Excluir um plano de pagamento de compras padrão do sistema
    Dado que o usuário selecionou um plano de pagamento de compras padrão do sistema
    Quando clicar em excluir
    Então é exibida a mensagem "Os planos À Vista e Avulso são planos padrões do sistema e não podem ser alterados."
    E o plano de pagamento de compras permanece exibido na pesquisa


Cenário: Excluir um plano de pagamento de compras padrão do usuário
    Dado que o usuário selecionou um plano padrão
    Quando clicar em excluir
    Então é exibida a mensagem "Plano de Pagamento padrão excluído com sucesso! Atribua um novo plano padrão"
    E o plano de pagamento de compras deixa de ser exibido na pesquisa


Cenário: Excluir em um plano de pagamento de compras diferente do padrão do sistema
    Dado que o usuário selecionou um plano diferente do padrão do sistema
    Quando clicar em excluir
    Então é exibida a mensagem "Plano de Pagamento excluído com sucesso"
    E o plano de pagamento de compras deixa de ser exibido na pesquisa


Esquema do Cenário: Excluir em um plano de pagamento de compras já utilizado
    Dado que o plano de pagamento foi utilizado na rotina "<rotina>"
    E que o usuário selecionou um plano de pagamento de compras diferente do padrão
    Quando clicar em excluir
    Então é exibida a mensagem "Plano de Pagamento padrão excluído com sucesso! Atribua um novo plano padrão"
    E o plano de pagamento de compras deixa de ser exibido na pesquisa
    E na rotina "<rotina>" o plano de pagamento continua vinculado

Exemplos:
|     rotina     |
|Pedido de Compra|
|Entrada de Notas|

Cenário: Pesquisar um plano de pagamento de compras
    Dado que o usuário informou um termo de busca
    Quando efetivar a pesquisa
    Então são exibidos os planos de pagamento de compras que contenham o termo de busca


Cenário: Alterar um plano de pagamento de compras padrão do sistema
    Dado que o usuário selecionou um plano de pagamento de compras padrão do sistema
    Quando clicar em alterar
    Então é exibida a mensagem "Os planos À Vista e Avulso são planos padrões do sistema e não podem ser alterados"
    E o plano de pagamento de compras permanece exibido na pesquisa sem alterações


Cenário: Alterar um plano de pagamento de compras diferente do padrão do sistema
    Dado que o usuário selecionou um plano de pagamento de compras diferente do padrão do sistema
    Quando clicar em alterar
    Então é exibida a mensagem "Plano de Pagamento alterado com sucesso"
    E o plano de pagamento de compras alterado é exibido na pesquisa com alterações


Cenário: Alterar um plano de pagamento de compras com vencimento fixo para variável
    Dado que o usuário selecionou um plano de pagamento de compras com vencimento fixo
    Quando alterar o vencimento para variável 
    E alterar a descrição
    E alterar número de parcelas
    E clicar em adicionar
    Então é necessário informar a quantidade de dias que serão contados da data atual até a data de vencimento


Esquema do Cenário: Alterar um plano de pagamento de pagamento de compras com vencimento variável para fixo
    Dado que o usuário selecionou um plano de pagamento de compras com vencimento variável
    Quando alterar o vencimento para fixo
    E alterar a descrição
    E alterar número de parcelas
    E clicar em adicionar
    Então é necessário informar o dia do vencimento e o "<periodo>"

Exemplos:
|  periodo |
|   Anual  |
| Bimestral|
|  Mensal  |
|Trimestral|
 