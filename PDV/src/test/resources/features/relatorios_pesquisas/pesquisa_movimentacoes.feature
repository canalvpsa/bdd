# language: pt

@PDV
Funcionalidade: PDV - Pesquisa de movimentações do PDV



Cenário: Pesquisar por vendas
	Dado que o usuário acessou o relatório de movimentações
	Quando valida a listagem
	Então é exibida as vendas no período consultado


Cenário: Pesquisar por compras
	Dado que o usuário acessou o relatório de movimentações
	E pesquisa por compras
	Quando valida a listagem
	Então é exibida as compras no período consultado


@cancelamento_seminovo @cancelamento_troca_nacional
Cenário: Cancelamento de compra e PDV sem acesso a internet
    Dado que o usuário efetuou uma compra de seminovo no PDV
    E detalhou a compra na pesquisa de movimentações
    E o PDV está sem acesso a internet
    Quando o usuário efetua o cancelamento da compra 
    Então o PDV não cancela a compra
    E a seguinte mensagem é exibida ao usuário:
    """
    Houve uma falha ao tentar cancelar a compra 24-20 no ERP, verifique sua conexão com a internet.
    """

@cancelamento_seminovo @cancelamento_troca_nacional @adiantamento
 Cenário: Cancelamento de compra e adiantamento já utilizado
    Dado que o usuário efetuou uma compra de seminovo no PDV
    E realizou o pagamento em adiantamento
    E o adiantamento já foi utilizado
    E detalhou a compra na pesquisa de movimentações
    E o usuário efetua o cancelamento da compra 
    Quando o ERP identifica que o adiantamento já foi utilizado
    Então o PDV não cancela a compra
    E a seguinte mensagem é exibida ao usuário:
    """
    O adiantamento gerado já foi utilizado. Para realizar a exclusão exclua a baixa referente a sua utilização.
    """


@cancelamento_seminovo
 Cenário: Cancelamento de compra de seminovo com pagamento em dinheiro
    Dado que o usuário efetuou uma compra de seminovo no PDV
    E detalhou a compra na pesquisa de movimentações
    E o usuário efetua o cancelamento da compra 
    E é enviado um pedido de cancelamento ao ERP
    Quando o PDV recebe retorno de sucesso de cancelamento no ERP
    Então o PDV deve exibir mensagem de sucesso ao usuário
    E o PDV efetua um lançamento de suprimento no caixa com o motivo "Cancelamento da compra  + nro_compra"
   

@cancelamento_seminovo @cancelamento_troca_nacional
 Cenário: Cancelamento de compra de seminovo ou troca nacional e pagamento em adiantamento
    Dado que o usuário efetuou uma compra de seminovo no PDV
    E detalhou a compra na pesquisa de movimentações
    E o usuário efetua o cancelamento da compra 
    E é enviado um pedido de cancelamento ao ERP
    Quando o PDV recebe retorno de sucesso de cancelamento no ERP
    Então o PDV deve exibir mensagem de sucesso ao usuário
    E o sincronismo atualiza o adiantamento gerado para status de cancelado
   

@cancelamento_seminovo @cancelamento_troca_nacional
Cenário: Cancelamento de compra não permitido
	Dado que o usuário acessou o detalhamento de uma compra
	E cancelou uma compra
	Quando o cancelamento é enviado para o ERP
	E o ERP recusou o cancelamento
	Então é exibida mensagem ao usuário e a compra não é cancelada

    
@adiantamento
Cenário: Detalhamento da venda com adiantamento gerado
    Dado que o usuário efetivou uma venda com adiantamento gerado
    Quando acessar o detalhamento da venda
    Então o valor da venda continuará sendo o valor final da venda sem o adiantamento
    E é exibido campo para listar o valor "adiantamento gerado"


@adiantamento
Cenário: Detalhamento da venda com adiantamento utilizado
    Dado que o usuário efetivou uma venda com adiantamento utilizado
    Quando acessar o detalhamento da venda
    Então o valor da venda continuará sendo o valor final da venda sem o adiantamento
    E também é exibido campo para o valor "adiantamento utilizado"

