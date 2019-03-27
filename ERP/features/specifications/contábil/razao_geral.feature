#language: pt

@ERP @razao_geral
Funcionalidade: ERP - Razão geral

Contexto: Dado que o usuário acessou o razão geral


Cenário: Conta contábil informada que teve movimentação no período
    Dado que o usuário informou o período
    E informou a entidade
    E informou conta contábil
    Quando realiza a pesquisa no razão geral
    Então a conta contábil informada é listada no detalhamento com os saldos inicial, final e total nas colunas de débito, crédito e saldo
    E ao detalhar os saldos da conta contábil são listadas as datas dentro do período informado em que houve movimentação


Cenário: Conta contábil informada que não teve movimentação no período
    Dado que o usuário informou o período
    E informou a entidade
    E informou conta contábil
    Quando realiza a pesquisa no razão geral
    Então a conta contábil informada é listada no detalhamento com os saldos inicial, final e total nas colunas de débito, crédito e saldo
    E ao detalhar os saldos da conta contábil não haverá nenhuma data com movimentação


Cenário: Exibir todas as contas com movimentações e período com movimentação
    Dado que o usuário informou o período
    E informou a entidade
    E marcou a opção para exibir todas as contas com movimentações
    Quando realiza a pesquisa no razão geral
    Então todas as contas contábeis movimentadas no período para a(s) entidade(s) serão listadas no detalhamento
    E ao detalhar os saldos da conta contábil são listadas as datas dentro do período informado em que houve movimentação


Cenário: Exibir todas as contas com movimentações e período sem movimentação
    Dado que o usuário informou o período
    E informou a entidade
    E marcou a opção para exibir todas as contas com movimentações
    Quando realiza a pesquisa no razão geral
    Então é exibida a mensagem "Nenhum registro encontrado"
