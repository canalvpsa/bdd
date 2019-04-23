#language: pt

@ERP
Funcionalidade: ERP - Usuário Sistema

Contexto: Dado que o usuário acessa o cadastro de usuário do sistema

Esquema do Cenário: Cadastrar um novo usuário do sistema
    Dado que o usuário seleciona o tipo "<tipo>" do usuário do sistema
    E informa todos os dados do usuário do sistema
    Quando finaliza o cadastro
    Então é exibida a mensagem "Registro adicionado com sucesso"
    E o usuário do sistema é cadastrado

Exemplos:
|          tipo         |
|  P. Física - Nacional |
|P. Física - Estrangeira|


Esquema do Cenário: Cadastrar um usuário do sistema inativado
    Dado que o usuário seleciona o "<tipo>" do usuário do sistema
    E informa os dados de um usuário do sistema inativado
    E é exibida uma mensagem solicitando confirmação para ativá-lo
    Quando confirmar que deseja ativar o usuário do sistema
    Então é exibida a mensagem "Registro alterado com sucesso"
    E o usuário do sistema é ativado no sistema
    E um e-mail é enviado para o usuário do sistema alterar a senha

Exemplos:
|          tipo         |
|  P. Física - Nacional |
|P. Física - Estrangeira|


Cenário: Pesquisar um usuário ativo do sistema
    Quando o uusário clica em pesquisar
    Então são exibidos todos os usuário do sistema


Cenário: Pesquisar usuário do sistema ativos do sistema e exportar listagem
    Dado que o usuário clica em pesquisar
    E são exibidos todos os usuários do sistema ativos
    Quando o usuário clicar em exportar
    Então é gerado um aquivo arquivo .csv para download


Cenário: Pesquisar um usuário inativo do sistema
    Dado que o usuário clica em pesquisar
    Quando o usuário clicar em "Usuário(s) Inativo(s)"
    Então serão exibidos todos os usuários do sistema inativos


Cenário: Pesquisar usuários inativos do sistema e exportar listagem
    Dado que o usuário clica em pesquisar
    E o usuário clicar em "Usuário(s) Inativo(s)"
    E são exibidos todos os usuários do sistema inativos
    Quando o usuário clicar em exportar
    Então é gerado um aquivo arquivo .csv para download


Cenário: Excluir um usuário do sistema
    Dado que o usuário pesquisou pelo usuário do sistema
    E selecinou o usuário do sistema
    Quando clicar em excluir
    E confirmar
    Então é exibida a mensagem "Registro excluído com sucesso"
    E o usuário do sistema é excluído


Cenário: Excluir um usuário do sistema cadastrado como vendedor, caixa ou gerente na rotina "Vendedor"
    Dado que o usuário pesquisou pelo usuário do sistema
    E selecinou o usuário do sistema
    Quando clicar em excluir
    E confirmar
    Então é exibida a mensagem "Não foi possível excluir usuário"
    E o usuário do sistema permanece cadastrado


Cenário: Alterar um usuário do sistema
    Dado que o usuário pesquisou pelo usuário do sistema
    E selecinou o usuário do sistema
    Quando clicar em alterar
    E alterar os dados
    E confirmar a alteração
    Então é exibida a mensagem "Registro alterado com sucesso"
    E o usuário do sistema é atualizado
