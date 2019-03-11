#language: pt

@desconto_titulos
Funcionalidade: ERP - Pesquisa de títulos descontados


Cenário: Pesquisar título descontado que possui baixa
Dado que o usuário filtrar pelo tipo "titulos descontados" 
Quando o desconto de títulos possui baixa ou o desconto de título for de "cartão transação"
Então o desconto de títulos não será listado


Cenário: Excluir título descontado que não possui baixa
Dado que o usuário filtrar pelo tipo "titulos descontados" 
Quando realizar a exclusão 
Então a exclusão será realizada com sucesso
E os lançamentos contábeis são excluídos


Cenário: Excluir título descontado criado em lote
Dado que o usuário criou desconto de títulos informando mais de uma conta a receber
E que o usuário filtrar pelo tipo "titulos descontados" 
Quando realizar a exclusão de uma conta a receber relacionada ao lote 
Então será exibida a mensagem:
"""
Todos os títulos descontados associados serão excluídos. Deseja prosseguir ? 
"""
E após confirmar, a exclusão de todo o lote será realizado com sucesso
E todas as contas a receber associadas ficarão disponíveis para um novo desconto de títulos
E os lançamentos contábeis são excluídos


Cenário: Excluir título descontado que uma das contas contas a receber possui baixa
Dado que o usuário criou desconto de títulos informando mais de uma conta a receber
E que uma das contas a receber possui baixa de títulos
E que o usuário filtrar pelo tipo "titulos descontados" 
Quando realizar a exclusão de uma conta a receber relacionada ao lote 
Então será exibida a mensagem:
"""
Existe(m) baixa(s) relacionada(s) ao(s) título(s) deste lote. Exclua-o(s) primeiramente.
"""


Cenário: Excluir títulos baixados
Dado que o usuário criou desconto de títulos informando uma ou mais de uma conta a receber
E uma ou mais contas a receber 
Quando realizar a exclusão de uma das conta recebida
Então a baixa da conta a receber é excluída
E a conta a receber ficará disponível para uma nova baixa
E a conta a receber continua com o título descontado associado


Cenário: Excluir títulos baixados de cartão transação
Quando o usuário filtrar pelo tipo "titulos baixados"
E clica para efetuar a exclusão
Então a exclusão é efetuada com sucesso
E a baixa da conta a receber de cartão é excluída
E a conta a receber de cartão volta a ficar disponível para um novo desconto de título


Cenário: Excluir títulos baixados de contas a receber
Quando o usuário filtrar pelo tipo "titulos baixados"
E clica para efetuar a exclusão
Então a exclusão é efetuada com sucesso
E a baixa da conta a receber de cartão é excluída
E a conta a receber fica disponível para uma nova baixa de títulos descontados
E a conta a receber continua com o título descontado associado