# language: pt

@PDV @venda @executavel
Funcionalidade: PDV - Venda alterando cliente e/ou vendedor

Contexto:
Dado que o usuário inicia uma venda

Cenário: Venda com vendedor diferente do vendedor logado

Quando o usuário altera para vendedor "USUARIO PADRAO"
E adiciona um item
E adiciona o plano de pagamento
Então a venda é finalizada com sucesso
E a venda fica registrada para o vendedor "USUARIO PADRAO" e cliente "DIVERSOS"
	
	
Cenário: Venda com cliente diferente de diversos

Quando o usuário altera o cliente para "ANA MARIA" 
E adiciona um item
E adiciona o plano de pagamento
Então a venda é finalizada com sucesso
E a venda fica registrada para o vendedor "CAIXA BARBARELLA - SÃO ROQUE" e cliente "ANA MARIA"
	
	
	
Cenário: Venda com cliente diferente de diversos e vendedor diferente do logado

Quando o usuário altera o cliente para "ANA MARIA" o vendedor "USUARIO PADRAO"
E adiciona um item
E adiciona o plano de pagamento
Então a venda é finalizada com sucesso
E a venda fica registrada para o vendedor "USUARIO PADRAO" e cliente "ANA MARIA"