#language: pt

Funcionalidade: Venda-Mobile - Gerar venda

Contexto: Dado que o usuário está em atendimento


Cenário: Gerar venda para carrinho vazio
    Quando o usuário acessa o carrinho no atendimento
    E o carrinho está vazio
    Então o usuário não pode gerar uma venda


Cenário: Gerar venda para carrinho com produtos
    Quando o usuário acessa o carrinho
    E o carrinho possui itens adicionados
    Então o usuário pode finalizar a venda


Cenário: Gerar venda para carrinho com troca
    Quando o usuário acessa o carrinho
    E o carrinho possui item para devolução
    E produto para venda
    Então o usuário pode finalizar a venda


Cenário: Excluir item da venda
    Dado que o usuário gerou a venda
    Quando remover item da venda
    Então o item é removido com sucesso
    E o total da venda é atualizado
    E se houver desconto no total, o desconto deve ser atualizado
    E se houver pagamento já informado, o valor deve ser atualizado