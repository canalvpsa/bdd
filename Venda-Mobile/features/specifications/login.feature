#language: pt

Funcionalidade: Realizar login no aplicativo

Contexto: Dado que o usuário inicia o aplicativo


Esquema do Cenário: Login no aplicativo com sucesso
    Dado que o usuário informa seu usuário e senha
    E o seu login está ativo no cadastro de vendedor do ERP
    E o seu perfil de vendedor é "<perfil>"
    Quando realiza o login
    Então é exibida a tela inicial do aplicativo

    Exemplos:
    | perfil |
    |vendedor|
    |  caixa |
    | gerente|



Cenário: Login não efetivado devido a usuário não associado a vendedor
    Dado que o usuário informa seu usuário e senha
    E seu usuário não estiver associado com vendedor no ERP
    Quando realiza o login
    Então é exibida mensagem de alerta 
    E o login não é efetivado


Cenário: Login não efetivado devido a dados incorretos
    Dado que o usuário informa seu usuário e senha incorretamente
    Quando realiza o login
    Então é exibida mensagem de alerta 
    E o login não é efetivado


Cenário: Recuperação de senha
    Dado que o usuário acessa a recuperação de senha
    E informa usuário, senha e CNPJ da empresa
    Quando efetiva a recuperação
    Então é exibida mensagem de sucesso
    E o usuário recebe email de recuperação de senha

