 @ERP @entrada @escrituracao_entrada
Feature: ERP - Entrada de nota fiscal no ERP e validação do status da escrituração

Background: Nova entrada de notas
Given que o usuário inicia uma nova entrada de notas
And que os produtos adicionados nas entradas manuais são:
|codigo_item|quantidade| 
|  2541.001 |     2    |
|  2541.002 |     1    |


@tipo_nota @modelo_nota
Scenario Outline: Entrada de notas manual validando status da escrituração para modelo 1A, 1, 55 e 4
    Given que o usuário informou todos os dados da entrada e o tipo da nota é "<tipoNota>"
    And que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
    When finaliza a entrada
    Then a entrada é realizada com sucesso exibindo a mensagem com o número do documento "1010" e série "1" 
    And a nota fiscal é registrada com status "<statusEscrituracao>"
|tipoNota| statusEscrituracao|
|   1A   |ESCRITURADA_EXTERNO|
|    1   |ESCRITURADA_EXTERNO|
|   55   |  NAO_ESCRITURADA  |
|    4   |ESCRITURADA_EXTERNO|


@tipo_nota @entrada_manual @modelo_nota
Scenario: Entrada de notas manual validando status da escrituração para modelo 55 e chave de acesso
    When o usuário informar na entrada o tipo de nota "55" com número do documento "562" e série "4" informando chave de acesso
    And que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
    And finaliza a entrada
    Then a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    And a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"


@entrada_XML @modelo_nota
Scenario: Entrada de notas com XML validando status da escrituração
    When o usuário preenche os dados iniciais informando um XML
    And que confirma a conciliação, confirma os dados adicionais, confirma os produtos e confirma a visão geral
    And finaliza a entrada
    Then a entrada é realizada com sucesso exibindo a mensagem com o número do documento "562" e série "4" 
    And a nota fiscal é registrada com status "ESCRITURADA_EXTERNO"