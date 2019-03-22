#language: pt

Funcionalidade: Minhas vendas


Cenário: Minhas vendas para vendedor com perfil de gerente
    Dado que o usuário está logado
    Quando o usuário acessa as "minhas vendas"
    Então serão listadas as vendas realizadas pelo vendedor logado
    E serão listadas as vendas de todos os vendedores da loja


Cenário: Minhas vendas para vendedor com perfil de vendedor/caixa
    Dado que o usuário com perfil de vendedor ou caixa está logado
    Quando o usuário acessa as "minhas vendas"
    Então serão listadas as vendas realizadas pelo usuário logado


Cenário: Informações das minhas vendas
    Dado que o usuário acessa as "minhas vendas"
    Quando lista as vendas realizadas 
    Então é possível visualizar o número da venda gerada, valor total, acréscimo e desconto


Cenário: Detalhar minhas vendas
    Dado que o usuário listou as "minhas vendas"
    Quando ele detalha uma das vendas
    Então é possível visualizar o cabeçalho da venda com data, nome do cliente, plano de pagamento e os itens vendidos (produtos e serviços), quantidade, valor unitário e desconto

    
