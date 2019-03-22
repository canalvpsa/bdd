# language: pt

@PDV @venda @executavel
Funcionalidade: PDV - Venda inserindo produto utilizando a pesquisa ou informando o nome inteiro do produto

Contexto:
Dado que o usuário inicia uma venda

		
Cenário: Venda com inclusão de item informando o nome inteiro do produto
Quando adiciona o item informando o nome completo do item "brinco" com quantidade "1" com valor unitário de R$ "41,80" 
E o valor total da venda é de R$ "41,80"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso
	
	
Cenário: Venda com inclusão pesquisando o produto para a inclusão
Quando adiciona o item utilizando a pesquisa informando o item "brinco" com quantidade "1" com valor unitário de R$ "41,80" 
E o valor total da venda é de R$ "41,80"
E adiciona o plano de pagamento dinheiro
Então a venda é finalizada com sucesso
