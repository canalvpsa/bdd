#language: pt

Funcionalidade: Cadastrar entrada de notas

Cenário: Efetuar entrada com diferentes operações
Dado que o usuário informou a conta bancária que recebeu o desconto de títulos
E filtrou pela modalidade "contas a receber" 
Quando seleciona um título a receber
E informa um valor líquido a receber
E confirmar
Então será exibida mensagem de sucesso
E será criada uma conta a receber para o título descontado, no valor líquido informado
E a conta a receber gerada será recebida na conta bancária
E a conta a receber original permanecerá em aberto
E os seguintes lançamentos contábeis serão realizados:
"""
|Débito                    |Crédito                             |Valor                           |
|CT - DIV (70001)          |TÍTULOS DESCONTADOS DIVERSOS (13451)|Conta a receber original        | -> Título gerado
|C/C selecionada           |CT - DIV (70001)                    |Valor líquido recebido          | -> Baixa do título gerado
|DESPESAS BANCÁRIAS (63503)|CT - DIV (70001)                    |(valor receber - valor recebido)| -> Encargos gerados
"""
