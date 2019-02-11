#language:pt
@login
Funcionalidade: realizar login como administrador
Para validar a autenticação no ERP da Varejonline
Como usuário do sistema
Eu quero logar e validar as permissões de administrador.

Contexto: possibilidade de acessar o sistema
Dado que usuário possa acessar a tela de login do sistema

Esquema do Cenário: realizar login no sistema com usuários válidos
Quando informar um <usuario> válido
E inserir uma <senha> válida
E inserir um <CNPJ> válido
Então o sistema deve permitir a autenticação do usuário

Exemplos:
| usuario | senha  | CNPJ|
| "admin" | "varejonline" |"qa_vpsa"|