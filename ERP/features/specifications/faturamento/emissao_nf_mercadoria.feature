#language: pt


@ERP @Emissao_NF
Funcionalidade: ERP - Emissão de NF de Mercadoria

Contexto: Dado que o usuário acessa a emissão de nf de mercadoria


Cenário: Filtrar notas fiscais emitidas por um período
    Quando o usuário informa um período
    E clica em pesquisar
    Então é exibido o resultado do filtro


Cenário: Detalhar uma nota fiscal
    Dado que o usuário faz uma pesquisa
    E faz uma seleção
    Quando clicar em "Detalhe"
    Então são exibidos detalhes da nota fiscal


Cenário: Cancelar uma nota fiscal modelo NF-e
    Dado que o usuário faz uma pesquisa
    E seleciona uma nota fiscal
    Quando clicar em "Cancelar"
    E informar o motivo
    E confirmar
    Então é exibida a mensagem "Nota Fiscal cancelada com sucesso"
    E a situação da nota fiscal é alterada para "Cancelada"
    Mas se a situação da nota fiscal for diferente de "Autorizada"
    Então é exibida a mensagem "Nota Fiscal precisa estar Autorizada"
    E a nota fiscal permanece no sistema


Cenário: Cancelar uma nota fiscal de determinado período modelo NF-e
    Dado que o usuário faz uma pesquisa
    E seleciona uma nota fiscal
    Quando clicar em "Cancelar"
    E informar o motivo
    E confirmar
    Então é exibida a mensagem "Nota Fiscal cancelada com sucesso"
    E a situação da nota fiscal é alterada para "Cancelada"
    Mas se o período previsto da legislação para cancelamento ultrapassar o limite permitido
    Então é exibida a mensagem "501 - Rejeicao: Prazo de Cancelamento Superior ao Previsto na Legislacao"
    E a nota fiscal permanece no sistema


Esquema do Cenário: Cancelar a nota fiscal diferente do modelo NF-e
    Dado que o usuário faz uma pesquisa
    E seleciona uma nota fiscal modelo "<modelo>"
    Quando clicar em "Cancelar"
    Então é exibida a mensagem "<mensagem>"

Exemplos:
| modelo |               mensagem               |
|  NFC-e |  Cancelamento de NFC-e não permitido |
|SAT CF-e|Cancelamento de SAT CF-e não permitido|


Cenário: Emitir a DANFE em formato .pdf de uma nota fiscal modelo NF-e
    Dado que o usuário faz uma pesquisa
    E seleciona uma nota fiscal
    Quando clicar em "DANFE"
    Então o sistema inicia a geração da "DANFE"
    E é exibida a mensagem "DANFE gerado com sucesso"
    E é feito o download da "DANFE" no formato .pdf
    Mas se a situação da nota fiscal for diferente de "Autorizada"
    Então é exibida a mensagem "Nota Fiscal precisa estar Autorizada"


Esquema do Cenário: Emitir a DANFE para a nota fiscal diferente do modelo NF-e
    Dado que o usuário faz uma pesquisa
    E seleciona uma nota fiscal modelo "<modelo>"
    Quando clicar em "DANFE"
    Então é exibida a mensagem "DANFE disponível somente para NF-e"

Exemplos:
| modelo |
|  NFC-e |
|SAT CF-e|


Cenário: Cancelar uma nota fiscal SAT CF-e
    Dado que o usuário faz uma pesquisa
    E seleciona uma nota fiscal
    Quando clicar em "Cancelar"
    Então é exibida a mensagem "Cancelamento de SAT CF-e não permitido"
    E a nota fiscal permanece no sistema


Esquema do Cenário: Consultar informações da situação da nota fiscal
    Dado que o usuário faz uma pesquisa
    E seleciona uma nota fiscal
    Quando clicar na situação "<situação>"
    Então são exibidos informações da nota fiscal

|   situação  |
|  Autorizada |
|  Cancelada  |
|   Enviada   |
|   Enviando  |
|  Rejeitada  |
|   Denegada  |
|Erro no envio|


Cenário: Autorizar uma nota fiscal enviada
    Dado que o usuário faz uma pesquisa
    E seleciona uma a nota fiscal
    E clica em "Detalhe"
    Quando clicar no status "ENVIADA"
    E consultar a situação
    Então o status da nota fiscal é alterado para "Autorizada"


Esquema do Cenário: Autorizar uma nota fiscal diferente de enviada
    Dado que o usuário faz uma pesquisa
    E seleciona uma a nota fiscal
    E clica em "Detalhe"
    Quando clicar no status "<status>"
    E consultar a situação
    Então é exibida a mensagem "<mensagem>"
    E o status permanece inalterado

Exemplos: 
|    status   |             mensagem            |
|  REJEITADA  |      Nota Fiscal Rejeitada      |
|   DENEGADA  |       Nota Fiscal Denegada      |
|ERRO NO ENVIO|Erro de comunicação ao enviar NFE|


Esquema do Cenário: Buscar uma movimentação para emissão de nota fiscal
    Dado que o usuário clica em "Emitir NF-e"
    E seleciona um período, uma funcionalidade "<funcionalidade>"
    Quando clicar em "Pesquisar"
    Então são exibidas as movimentações
    Mas se inexistir uma movimentação
    Então é exibida a mensagem "Nenhum registro encontrado"
    
Exemplos:
|    funcionalidade   |
|  Ajuste de Estoque  |
| Devolução de Compra |
|  Devolução de venda |
|Entrada de importação|
|   Entrada diversa   |
| Entrada de produtos |
|  Nota Substitutiva  |
|   Pedido de venda   |
|    Saída diversa    |
|    Transferência    |
|        Fatura       |


Esquema do Cenário: Emitir uma NF-e para um único destinatário
    Dado que o usuário clica em "Emitir NF-e"
    E pesquisa um período, uma funcionalidade "<funcionalidade>"
    Quando selecionar as movimentações
    E clicar em "Emitir NF-e"
    E confirmar a emissão
    Então é exibida a mensagem "Nota fiscal emitida com sucesso. Aguarde processamento"
    E a nota fiscal é emitida
    E a situação da nota é alterada para "Enviada"
    E as notas fiscais são lançadas nos livros fiscais do sistema na rotina "Fiscal"
    E são feitos os lançamentos contábeis na apuração de tributos na rotina "Contábil"
    
Exemplos:
|    funcionalidade   |
|  Ajuste de Estoque  |
| Devolução de Compra |
|  Devolução de venda |
|Entrada de importação|
|   Entrada diversa   |
| Entrada de produtos |
|  Nota Substitutiva  |
|   Pedido de venda   |
|    Saída diversa    |
|    Transferência    |
|        Fatura       |


Esquema do Cenário: Emitir uma NF-e para diferentes destinatários
    Dado que o usuário clica em "Emitir NF-e"
    E pesquisa um período, uma funcionalidade "<funcionalidade>"
    Quando selecionar as movimentações
    E clicar em "Emitir NF-e"
    E definir um destinatário
    E confirmar a emissão
    Então é exibida a mensagem "Nota fiscal emitida com sucesso. Aguarde processamento"
    E a nota fiscal é emitida
    E a situação da nota é alterada para "Enviada"
    E as notas fiscais são lançadas nos livros fiscais do sistema na rotina "Fiscal"
    E são feitos os lançamentos contábeis na apuração de tributos na rotina "Contábil"
    
Exemplos:
|    funcionalidade   |
|  Ajuste de Estoque  |
| Devolução de Compra |
|  Devolução de venda |
|Entrada de importação|
|   Entrada diversa   |
| Entrada de produtos |
|  Nota Substitutiva  |
|   Pedido de venda   |
|    Saída diversa    |
|    Transferência    |
|        Fatura       |


Esquema do Cenário: Selecionar várias movimentações para emissão de nota fiscal
    Dado que o usuário clica em "Emitir NF-e"
    E pesquisa um período, uma funcionalidade "<funcionalidade>"
    Quando selecionar as movimentações
    E clicar em "Emitir NF-e"
    Então é exibido o valor total das notas
    E o sistema concatena as informações em uma única nota fiscal
    Mas se o usuário desconsiderar a seleção de movimentação
    E clicar em "Emitir NF-e"
    Então é exibida a mensagem "Selecione uma movimentação para continuar"

Exemplos:
|    funcionalidade   |
|  Ajuste de Estoque  |
| Devolução de Compra |
|  Devolução de venda |
|Entrada de importação|
|   Entrada diversa   |
| Entrada de produtos |
|  Nota Substitutiva  |
|   Pedido de venda   |
|    Saída diversa    |
|    Transferência    |
|        Fatura       |


Esquema do Cenário: Selecionar uma movimentação para emissão de nota fiscal
    Dado que o usuário clica em "Emitir NF-e"
    E pesquisa um período, uma funcionalidade "<funcionalidade>"
    Quando selecionar a movimentação
    E clicar em "Emitir NF-e"
    Então o sistema exibe as informações da nota fiscal
    Mas se o usuário desconsiderar a seleção de movimentação
    E clicar em "Emitir NF-e"
    Então é exibida a mensagem "Selecione uma movimentação para continuar"

Exemplos:
|    funcionalidade   |
|  Ajuste de Estoque  |
| Devolução de Compra |
|  Devolução de venda |
|Entrada de importação|
|   Entrada diversa   |
| Entrada de produtos |
|  Nota Substitutiva  |
|   Pedido de venda   |
|    Saída diversa    |
|    Transferência    |
|        Fatura       |

 