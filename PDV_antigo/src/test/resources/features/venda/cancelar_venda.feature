# language: pt

@PDV @venda, @cancelamento @executavel
Funcionalidade: PDV - Venda enquanto estiver em aberto ou após finalizar


Cenário: Cancelar a venda antes de finalizá-la

Dado que o usuário iniciou uma venda e já informou pagamento em dinheiro
Quando sai da venda sem finaliza-la
Então a venda é cancelada com sucesso


@cancelar_ultimo_CF
Cenário: Cancelar o último cupom emitido

Dado que o usuário realizou uma venda
Quando cancela o último CF emitido
Então a venda é cancelada com sucesso


@cancelar_relatorio_venda
Cenário: Cancelar a venda pelo relatório de vendas

Dado que o usuário realizou uma venda
Quando pesquisa pela venda efetuada
E cancela a venda
Então a venda é cancelada com sucesso



Cenário: Cancelar venda efetuada com adiantamento

Dado que o usuário realizou uma venda com plano de pagamento adiantamento
E já realizou o sincronismo da venda
Quando a venda é cancelada
E o sincronismo é efetuado
Então o saldo do adiantamento volta a ficar disponível