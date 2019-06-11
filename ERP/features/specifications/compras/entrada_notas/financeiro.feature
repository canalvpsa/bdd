#language: pt

@ERP @entrada
Funcionalidade: ERP - Plano de pagamento na entrada de notas

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas

Esquema do Cenário: Entrada de notas manual sem informações de pagamento
   Dado que o usuário informou todos os dados da entrada e o tipo da nota é "1"
    E que informa desconto de "<desconto>" nos dados adicionais
    E que confirma os dados adicionais
    E adiciona os produtos
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida