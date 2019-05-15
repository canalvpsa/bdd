#language: pt

@ERP @balanco
Funcionalidade: ERP - Cadastro, alteração e processamento de balanço de estoque

Contexto: Balanço de estoque
    Dado que o usuário acessa o cadastro de balanço



Cenário: Balanço de estoque com data de contagem retroativa


Cenário: Balanço de estoque com zeramento por nível de categoria
    Dado que o usuário acessa o cadastro de balanço
    E marca a opção para zerar estoque
    E informa um nível de categoria a ser zerado
    E informa a(s) categoria(s) relacionadas ao nível
    Quando acessar a conferência do balanço
    Então os produtos relacionados a categoria no nível informado devem ser listados para zeramento