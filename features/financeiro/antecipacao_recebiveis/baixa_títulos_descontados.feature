#language: pt

Funcionalidade: Cadastrar baixa de títulos descontados


Cenário: Pesquisar títulos descontados para realizar a baixa de títulos descontados
Quando o usuário lista os títulos descontados
Então serão listadas as contas a receber relacionadas aos título descontados, independente se o título foi criado em lote ou não
Mas os títulos descontados com modalidade "cartão transação" não serão listados, pois foi baixado automaticamente


Cenário: Efetuar baixa de títulos de modalidade contas a receber
Quando o usuário realiza a baixa do título de uma conta a receber ou um lote
Então será exibida mensagem de sucesso
E será registrada individualmente a baixa de cada conta a receber informada 
E o seguinte lançamento contábil será realizado para cada conta a receber:
"""
|Débito                               |Crédito          |Valor                   |
|TÍTULOS DESCONTADOS DIVERSOS (13451) |CLIENTES (13101) |Conta a receber original| -> Título gerado
"""
