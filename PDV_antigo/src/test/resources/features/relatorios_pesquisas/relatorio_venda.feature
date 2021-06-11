# language: pt

@PDV
Funcionalidade: PDV - Relatório de vendas do PDV

Cenário: Validar adiantamento utilizado no relatório de vendas
    Dado que o usuário realizou uma venda com plano de pagamento "adiantamento utilizado"
    Quando acessar o relatório de vendas
    Então deve ser exibida uma coluna para listar "adiantamento utilizado"