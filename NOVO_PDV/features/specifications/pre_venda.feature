#language: pt

@PreVenda

Funcionalidade: Pré venda

Contexto: Dado que o usuário acessa o PDV

Cenário: Pré venda para cliente diversos
    Dado que o cliente padrão é o diversos
    E o usuário adicionou um produto na pré venda
    Quando salvar pré venda
    Então é exibida a mensagem "Atendimento salvo com sucesso"



Cenário: Pré venda para cliente diferente de diversos



Cenário: Pré venda para cliente diferente de diversos



Cenário: Pré venda para cliente diferente de diversos



Cenário: Pré venda sem informar itens
    Quando salvar pré venda
    Então é exibida a mensagem "Insira pelo menos 1 item"



Cenário: Pré venda adicionando produto através da pesquisa


Cenário: Pré venda adicionando produto através da inserção rápida


Cenário: Pré venda com desconto em percentual no item


Cenário: Pré venda adicionando quantidades para produto através da tela de itens inseridos


Cenário: Pré venda adicionando quantidades para produto adicionando pela pesquisa de produto

Cenário: Pré venda adicionando quantidades para produto adicionando pela inserção rápida



Cenário: Pré venda diminuindo quantidade para produto



Cenário: Pré venda removendo produto da listagem



Cenário: Pré venda com desconto em reais no total


Cenário: Pré venda com desconto em percentual no total


