@ERP
Funcionalidade: ERP - Usuário Sistema

Contexto: Dado que o usuário acessa o cadastro de usuário do sistema

Esquema do Cenário: Cadastrar um novo usuário
    Dado que o usuário seleciona o tipo "<tipo>" do usuário do sistema
    E informa todos os dados do usuário do sistema
    Quando finaliza o cadastro
    Então é exibida a mensagem "Registro adicionado com sucesso"
    E o usuário do sistema é cadastrado

Exemplos:
|          tipo         |
|  P. Física - Nacional |
|P. Física - Estrangeira|


Esquema do Cenário: Cadastrar um usuário antigo novamente
    Dado que o usuário seleciona o "<tipo>" do usuário
    E informa os dados de um antigo usuário do sistema
    E é exibida a mensagem solicitando confirmação para ativá-lo
    Quando confirma que deseja ativar o usuário inativado
    Então é exibida a mensagem "Registro alterado com sucesso"
    E o usuário é cadastrado novamente no sistema
    E um e-mail é enviado para o usuário cadastrar uma nova senha

Exemplos:
|          tipo         |
|  P. Física - Nacional |
|P. Física - Estrangeira|


Cenário: Pesquisar um usuário ativo do sistema
    Quando o cliente clica em pesquisar
    Então são exibidos todos os usuários ativos cadastrados


Cenário: Pesquisar usuários ativos do sistema e exportar listagem
    Dado que o usuário clica em pesquisar
    E serão exibidos todos os usuários ativos ainda cadastrados no sistema
    Quando o usuário clicar em exportar
    Então é gerado um aquivo arquivo para download com todos os usuários ativos


Cenário: Pesquisar um usuário inativo do sistema
    Dado que o usuário clica em pesquisar
    Quando o usuário clicar em "Usuário(s) Inativo(s)"
    Então serão exibidos todos os usuários inativos ainda cadastrados no sistema


Cenário: Pesquisar usuários inativos do sistema e exportar listagem
    Dado que o usuário clica em pesquisar
    E o usuário clicar em "Usuário(s) Inativo(s)"
    E serão exibidos todos os usuários inativos ainda cadastrados no sistema
    Quando o usuário clicar em exportar
    Então é gerado um aquivo arquivo para download com todos os usuários inativos


Cenário: Excluir um usuário do sistema
    Dado que o usuário pesquisou pelo usuário do sistema a ser excluído
    E selecinou o usuário do sistema
    Quando clicar em excluir
    E confirmar que deseja excluir o registro
    Então é exibida a mensagem "Registro excluído com sucesso"
    E o usuário deixa de acessar o sistema
    E deixa de ser exibido na pesquisa de usuários do sistema


Cenário: Excluir um usuário do sistema cadastrado como vendedor, caixa ou gerente
    Dado que o usuário pesquisou pelo usuário do sistema a ser excluído
    E selecinou o usuário do sistema cadastrado como vendedor, caixa ou gerente na rotina "Vendedor"
    Quando clicar em excluir
    E confirmar que deseja excluir o registro
    Então é exibida a mensagem "Não foi possível excluir usuário"
    E o usuário permanece cadastrado no sistema


Cenário: Alterar um usuário do sistema
    Dado que o usuário pesquisou pelo usuário do sistema a ser alterado
    E selecinou o usuário do sistema
    Quando clicar em alterar
    E alterar os dados do usuário do sistema
    E confirmar a alteração
    Então é exibida a mensagem "Registro alterado com sucesso"
    E o usuário do sistema é atualizado




