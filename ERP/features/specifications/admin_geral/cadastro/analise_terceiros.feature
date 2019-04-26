@ERP

Funcionalidade: ERP - Terceiros [beta]

Contexto: Dado que o usuário acessa o cadastro de terceiros 

Cenário: Alterar os dados do terceiro pela análise de terceiro
    Dado que o usuário clica em "Atualizar cadastro"
    E o usuário é direcionado para a tela de terceiro
    E alterar os dados do terceiro
    Quando salvar a alteração
    Então é exibida a mensagem "Terceiro atualizado com sucesso"
    E o usuário não é direcionado novamente para a análise de terceiro

Cenário: Verifica resumo financeiro
    Dado que o terceiro analisado possui contas a receber 
    Quando o usuário clica em "Ver detalhes" 
    Então são exibidas as parcelas à receber