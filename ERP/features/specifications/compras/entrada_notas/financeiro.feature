#language: pt

@ERP @entrada @financeiro
Funcionalidade: ERP - Plano de pagamento na entrada de notas

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas
E que os produtos adicionados nas entradas manuais são:
|codigo_item|quantidade|
|  2541.001 |     2    |
|  2541.002 |     1    |


Cenário: Entrada de notas manual sem informações de pagamento
   Dado que o usuário informou todos os dados da entrada e a operação da nota é "Transferência p/ comercialização - Externa"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então é exibida a mensagem "A(s) operação(ões) do(s) produto(s) não permite(m) gerar provisão de conta a pagar"
   E nenhum valor é exibido
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 


Esquema do Cenário: Entrada de notas manual com informações de pagamento
   Dado que o plano de pagamento de compras padrão é "a vista"
   E que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário informou todos os dados da entrada e a operação da nota é "Compra p/ comercialização"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então o plano pagamento exibido é "a vista"
   E o valor total "<permite_editar_total>" alteração
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 
   Exemplos:
|  parametro |permite_editar_total|
| habilitado |       permite      |
|desabilitado|       permite      |


Esquema do Cenário: Entrada de notas com XML que não possui parcelas e operação não gera financeiro
   Dado que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário preencheu os dados iniciais informando o "<XML>" e a operação "Transferência p/ comercialização - Externa"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então é exibida a mensagem "A(s) operação(ões) do(s) produto(s) não permite(m) gerar provisão de conta a pagar"
   E nenhum valor é exibido
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 
   Exemplos:
|  parametro |permite_editar_total|      XML      |numeroDocumento|serie|
| habilitado |       permite      |xml_simples.xml|      562      |  4  |
|desabilitado|     nao_permite    |xml_simples.xml|      562      |  4  |



Esquema do Cenário: Entrada de notas com XML que possui parcelas e operação não gera financeiro
   Dado que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário preencheu os dados iniciais informando o "<XML>" e a operação "Transferência p/ comercialização - Externa"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então o plano pagamento exibido é "avulso"
   E o valor total "<permite_editar_total>" alteração
   E as parcelas são exibidas conforme o XML 
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 
   Exemplos:
|  parametro |permite_editar_total|        XML       |numeroDocumento|serie|
| habilitado |       permite      |CST00_com_ICMS.xml|       17      |  10 |
|desabilitado|     nao_permite    |CST00_com_ICMS.xml|       17      |  10 |


Esquema do Cenário: Entrada de notas com XML que possui parcelas e operação que gera financeiro
   Dado que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário preencheu os dados iniciais informando o "<XML>" e a operação "Compra p/ comercialização"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então o plano pagamento exibido é "avulso"
   E o valor total "<permite_editar_total>" alteração
   E as parcelas são exibidas conforme o XML 
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 
   Exemplos:
|  parametro |permite_editar_total|      XML      |numeroDocumento|serie|
| habilitado |       permite      |xml_simples.xml|      562      |  4  |
|desabilitado|     nao_permite    |xml_simples.xml|      562      |  4  |
 