#language: pt

 @ERP @entrada @escrituracao_entrada
Funcionalidade: ERP - Entrada de nota fiscal no ERP e validação do status da escrituração

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas
E que os produtos adicionados nas entradas manuais são:
    |codigo_item|quantidade| 
    |  2541.001 |     2    |
    |  2541.002 |     1    |


@tipo_nota @modelo_nota
Esquema do Cenário: Entrada de notas manual validando status da escrituração para modelo 1A, 1, 55 e 4
    Dado que o usuário informou todos os dados da entrada e o tipo da nota é "<tipoNota>"
    E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
    Quando finaliza a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    E a nota fiscal é registrada com status "<statusEscrituracao>"

    Exemplos:
|tipoNota| statusEscrituracao|
|   1A   |ESCRITURADA_EXTERNO|
|    1   |ESCRITURADA_EXTERNO|
|   55   |  NAO_ESCRITURADA  |
|    4   |ESCRITURADA_EXTERNO|


@tipo_nota @entrada_manual @modelo_nota
Cenário: Entrada de notas manual validando status da escrituração para modelo 55 e chave de acesso
    Quando o usuário informar na entrada o tipo de nota "55" com número do documento "562" e série "4" informando chave de acesso
    E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
    E finaliza a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@entrada_XML @modelo_nota
Cenário: Entrada de notas com XML validando status da escrituração
    Quando o usuário preenche os dados iniciais informando um XML
    E que confirma a conciliação, confirma os dados adicionais, confirma os produtos e confirma a visão geral
    E finaliza a entrada
    Então a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    E a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"