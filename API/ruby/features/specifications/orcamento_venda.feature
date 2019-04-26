#language: pt
@ERP
Funcionalidade: API - Orçamento de venda


Cenário: Cadastrar orçamento de venda de produto
    Dado o endereço da API para cadastrar orçamento de venda
    Quando realizar uma requisição para cadastar um orçamento de venda
    Então a API retorna os dados do cadastro do orçamento de venda respondendo o código 200

    