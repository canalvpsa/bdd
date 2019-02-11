#language: pt

@tipoNota
Funcionalidade: Entrada de nota fiscal no ERP

Contexto: Dado que o usuário inicia uma nova entrada de notas

Esquema do Cenário: Tipos de nota que ficam "não escrituradas"
    Dado que o usuário informou todos os dados da entrada e o tipo da nota é "<tipoNota>"
    Quando finalizar a entrada
    Então a entrada é concluida com a mensagem:
    """
   A entrada 1010/1 foi efetuada com sucesso.
    """
    E a nota fiscal será registrada com status "NAO_ESCRITURADA"

    Exemplos:
    |tipoNota|
    |1A      |
    |1       |
    |55      |
    |4       |


Cenário: Entrada de notas com XML
Quando preenche os dados iniciais informando um XML
E finalizar a entrada
Então a entrada é concluida com a mensagem:
    """
    A entrada 1010/1 foi efetuada com sucesso.
    """
 E a nota fiscal será registrada com status "ESCRITURADA_EXTERNO"

Cenário: Entrada de notas com tipo de nota 55 com chave
Quando o usuário informar na entrada o tipo de nota "55"
E informar chave de acesso
E finalizar a entrada
Então a entrada é concluida com a mensagem:
    """
    A entrada "1010/1" foi efetuada com sucesso.
    """
 E a nota fiscal será registrada com status "ESCRITURADA_EXTERNO"