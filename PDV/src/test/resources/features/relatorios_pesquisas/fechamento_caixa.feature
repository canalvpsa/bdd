# language: pt

@PDV
Funcionalidade: PDV - Fechamento de caixa

Cenário: Relatório de fechamento de caixa com adiantamento gerado pela venda
    Dado que o usuário lançou um adiantamento gerado para um cliente através de uma venda
    Quando acessar o relatório de fechamento de caixa
    Então deve ser exibida uma coluna para listar "adiantamento gerado" que será preenchida na forma de pagamento "dinheiro"
    E o adiantamento gerado não pode mais ser somado na coluna "recebimentos" na linha "dinheiro"
    E no fechamento de caixa o valor continua sendo somado ao "dinheiro"
    
 
 Cenário: Relatório de fechamento de caixa com adiantamento gerado pela compra
    Dado que o usuário lançou um adiantamento gerado para um cliente através de uma compra
    Quando acessar o relatório de fechamento de caixa
    Então deve ser exibida uma coluna para listar "adiantamento gerado" sem somar o adiantamento gerado ao total de "dinheiro"
    E no fechamento de caixa o valor também não deve ser somado ao "dinheiro"