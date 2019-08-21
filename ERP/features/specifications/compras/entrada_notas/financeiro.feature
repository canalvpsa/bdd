#language: pt

@ERP @entrada @financeiro
Funcionalidade: ERP - Plano de pagamento na entrada de notas e parâmetro que permite alteração de valor da provisão

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas
E que os produtos adicionados nas entradas manuais são:
|codigo_item|quantidade|
|  2541.001 |     2    |
|  2541.002 |     1    |

#Entrada de notas manual
@entrada_manual
Cenário: Entrada de notas manual sem valor de pagamento
   Dado que o usuário informou todos os dados da entrada e a operação da nota é "Transferência p/ comercialização - Externa"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então é exibida a mensagem "A(s) operação(ões) do(s) produto(s) não permite(m) gerar provisão de conta a pagar"
   E nenhum valor é exibido
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 

@entrada_manual
Esquema do Cenário: Entrada de notas manual com valor de pagamento
   Dado que o plano de pagamento de compras padrão é "30/60/90"
   E que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário informou todos os dados da entrada e a operação da nota é "Compra p/ comercialização"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então o plano pagamento exibido é "30/60/90"
   E o valor total "<permite_editar_total>" alteração
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 
   Exemplos:
|  parametro |permite_editar_total|
| habilitado |       permite      |
|desabilitado|       permite      |


#Entrada de notas manual com pedido de compras associado
@entrada_manual @entrada_pedido
Esquema do Cenário: Entrada de notas manual com pedido de compra com valor financeiro
   Dado que existe um pedido de compra em aberto com plano de pagamento "BOLETO 2X"
   E que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário associou o pedido de compra na entrada manual
   E que o usuário preencheu os demais campos da entrada e a operação da nota é "Compra p/ comercialização"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então o plano pagamento exibido é "BOLETO 2X"
   E o valor total "<permite_editar_total>" alteração
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 
   Exemplos:
|  parametro |permite_editar_total|
| habilitado |       permite      |
|desabilitado|       permite      |


# Entrada de notas com XML
@entrada_XML
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


@entrada_XML
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


@entrada_XML
Esquema do Cenário: Entrada de notas com XML que possui parcelas e operação gera financeiro
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




# Entrada de notas com XML e pedido de compras associado 
@entrada_XML @entrada_pedido
Esquema do Cenário: Entrada de notas com XML que não possui parcelas, pedido de compra associado e operação não gera financeiro
   Dado que existe um pedido de compra em aberto com plano de pagamento "BOLETO 2X"
   E que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário associou o pedido de compra na entrada manual
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


@entrada_XML @entrada_pedido
Esquema do Cenário: Entrada de notas com XML que não possui parcelas, pedido de compra associado e operação gera financeiro
   Dado que existe um pedido de compra em aberto com plano de pagamento "BOLETO 2X"
   E que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário associou o pedido de compra na entrada manual
   E que o usuário preencheu os dados iniciais informando o "<XML>" e a operação "Compra p/ comercialização"
   E que confirma os dados adicionais, adiciona os produtos e confirma a visão geral
   Quando validar os dados financeiros da entrada de notas
   Então o plano pagamento exibido é "BOLETO 2X"
   E o valor total "<permite_editar_total>" alteração
   E as parcelas são exibidas conforme o plano de pagamento definido, considerando a data base
   E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<numeroDocumento>" e série "<serie>" 
   Exemplos:
|  parametro |permite_editar_total|      XML      |numeroDocumento|serie|
| habilitado |       permite      |xml_simples.xml|      562      |  4  |
|desabilitado|     nao_permite    |xml_simples.xml|      562      |  4  |




@entrada_XML @entrada_pedido
Esquema do Cenário: Entrada de notas com XML que possui parcelas, pedido de compra associado e operação não gera financeiro
   Dado que existe um pedido de compra em aberto com plano de pagamento "BOLETO 2X"
   E que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário associou o pedido de compra na entrada manual
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


@entrada_XML @entrada_pedido
Esquema do Cenário: Entrada de notas com XML que possui parcelas, pedido de compra associado e operação gera financeiro
   Dado que existe um pedido de compra em aberto com plano de pagamento "BOLETO 2X"
   E que o parâmetro que permite editar o valor da provisão em entrada de notas XML está "<parametro>"
   E que o usuário associou o pedido de compra na entrada manual
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