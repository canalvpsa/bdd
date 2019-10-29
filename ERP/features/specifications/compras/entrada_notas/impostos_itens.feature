#language: pt

 @ERP @imposto @entrada
Funcionalidade: ERP - Entrada de nota fiscal no ERP com validação dos impostos

Contexto: Nova entrada de notas
Dado que o usuário inicia uma nova entrada de notas

#CP-708  - XML CST51 com base de ICMS
#OP-1185 e CP-766  - XML CST00 só com base de ICMS em um item e outro item correto
#CP-707  - XML CST10 só com base de ST (não tem aliquota e valor de ST)
#CP-771  - XML CST10 com base de ST e alíquota (não tem valor de ST)
#CP-772 - XML CST10 com base e valor de ST (não tem alíquota de ST)
#OP-1192 - XML CSOSN202 (como se fosse CST10)


@entrada_XML @cst @icms @icmsst @ipi @entrada
Esquema do Cenário: Entrada de notas com XML e validação de ICMS,ICMS ST e IPI para determinado CST/CSOSN
    Dado que o usuário preencheu os dados iniciais informando o "<XML>" conciliando na nota "codigo_produto" e no sistema "codigo_interno"
    E que confirma a conciliação, confirma os dados adicionais e confirma os produtos
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado "<XML>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<num_Doc>" e série "<serie>" 

    Exemplos:
|               XML               |num_Doc|serie|                                 obs_item                                 |     chamado     |
|        CST00_com_ICMS.xml       |   17  |  10 |                     vBC 44.00 pICMS 12.00 vICMS 2.64                     |        -        |
|      CST00_zerado_comBC.xml     |  701  |  17 |                      vBC 22,00 pICMS 0.00 vICMS 0.00                     |#OP-1185 e CP-766|
|       CST10_com_ICMSST.xml      |   8   |  10 |   vBC 8.73 pICMS 7.00 vICMS 0.61 vBCST 11.35 pICMSST 7.00 vICMSST 0.18   |        -        |
|    CST10_zerado_com_BCST.xml    |   9   |  10 |   vBC 8.73 pICMS 7.00 vICMS 0.61 vBCST 11.35 pICMSST 0.00 vICMSST 0.00   |     #CP-707     |
|CST10_zerado_com_BCST_PICMSST.xml|1083335|  7  |  vBC 17.25 pICMS 19.00 vICMS 3.28 vBCST 12.24 pICMSST 19.00 vICMSST 0.00 |     #CP-771     |
|CST10_zerado_com_BCST_VICMSST.xml|  707  |  17 |    vBC 20.90 pICMS 7.00 vICMS 1.46 vBCST 27.80 pICMSST 0 vICMSST 0.49    |     #CP-772     |
|      CST30_desoneracao.xml      |   6   |  10 |                     vBCST 13.68 pICMSST vICMSST 1.41                     |        -        |
|    CST40_deson_n_subtrai.xml    |   6   |  10 |                              vICMSDeson 1.05                             |        -        |
|     CST40_deson_subtrai.xml     |   6   |  10 |                              vICMSDeson 1.05                             |        -        |
|         CST51_zerado.xml        |   6   |  10 |                      vBC 0.00 pICMS 0.00 vICMS 0.00                      |        -        |
|      CST51_zerado_comBC.xml     |   6   |  10 |                      vBC 8.73 pICMS 0.00 vICMS 0.00                      |     #CP-708     |
|        CST51_sem_tags.xml       |   6   |  10 |                   Não existe as tags vBC, pICMS e vICMS                  |        -        |
|     CST51_dif_sem_vICMS.xml     |   6   |  10 |vBC 8.73 pICMS 18.0000 vICMSOp 1.57 pDif 100.0000 vICMSDif 1.57 vICMS 0.00|        -        |
|          CST51_dif.xml          |   6   |  10 | vBC 8.73 pICMS 18.0000 vICMSOp 1.57 pDif 50.0000 vICMSDif 1.57 vICMS 0.79|        -        |
|     ICMSSN101_com_ICMSSN.xml    |  105  |  2  |                      pCredSN 19.00 vCredICMSSN 3.32                      |        -        |
|     ICMSSN202_com_ICMSST.xml    |  101  |  2  |                   BCST 11.09 pICMSST 17.00 vICMSST 0.84                  |     #OP-1192    |


#CP-759 - Não foi possível aplicar a diferença em nenhum dos itens da lista
@entrada_XML @cst @icms @icmsst @ipi @conciliacao
Esquema do Cenário: Entrada de notas com XML e validação de ICMS,ICMS ST e IPI para determinado CST/CSOSN
    Dado que o usuário preencheu os dados iniciais informando o "<XML>" conciliando na nota "codigo_produto" e no sistema "codigo_interno"
    E que na conciliação um item do "<XML>" é conciliado com vários do sistema
    E que confirma a conciliação, confirma os dados adicionais e confirma os produtos
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado "<XML>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<num_Doc>" e série "<serie>" 

    Exemplos:
|           XML           |num_Doc|serie|                                 obs_item                                 |
|CST40_deson_n_subtrai.xml|   6   |  10 |                              vICMSDeson 1.05                             |
| CST40_deson_subtrai.xml |   6   |  10 |                              vICMSDeson 1.05                             |
| CST51_dif_sem_vICMS.xml |   6   |  10 |vBC 8.73 pICMS 18.0000 vICMSOp 1.57 pDif 100.0000 vICMSDif 1.57 vICMS 0.00|
|      CST51_dif.xml      |   6   |  10 | vBC 8.73 pICMS 18.0000 vICMSOp 1.57 pDif 50.0000 vICMSDif 1.57 vICMS 0.79|
| ICMSSN101_com_ICMSSN.xml|  105  |  2  |                      pCredSN 19.00 vCredICMSSN 3.32                      |
| ICMSSN202_com_ICMSST.xml|  101  |  2  |                   BCST 11.09 pICMSST 17.00 vICMSST 0.84                  |


@entrada_XML @cst @icms @icmsst @ipi @conciliacao
Esquema do Cenário: Entrada de notas com XML e validação de ICMS,ICMS ST e IPI para determinado CST/CSOSN
    Dado que o usuário preencheu os dados iniciais informando o "<XML>" conciliando na nota "codigo_produto" e no sistema "codigo_interno"
    E que na conciliação vários itens do "<XML>" são conciliados com o mesmo item do sistema
    E que confirma a conciliação, confirma os dados adicionais e confirma os produtos
    Quando validar os dados da nota fiscal de entrada
    Então nenhuma mensagem de divergência de valores deve ser exibida
    E na visão geral da entrada com os impostos devem estar preenchidos de acordo com o XML informado "<XML>"
    E ao finalizar, a entrada é realizada com sucesso exibindo a mensagem com o número do documento "<num_Doc>" e série "<serie>" 

    Exemplos:
|           XML           |num_Doc|serie|                                 obs_item                                 |
|CST40_deson_n_subtrai.xml|   6   |  10 |                              vICMSDeson 1.05                             |
| CST40_deson_subtrai.xml |   6   |  10 |                              vICMSDeson 1.05                             |
| CST51_dif_sem_vICMS.xml |   6   |  10 |vBC 8.73 pICMS 18.0000 vICMSOp 1.57 pDif 100.0000 vICMSDif 1.57 vICMS 0.00|
|      CST51_dif.xml      |   6   |  10 | vBC 8.73 pICMS 18.0000 vICMSOp 1.57 pDif 50.0000 vICMSDif 1.57 vICMS 0.79|
| ICMSSN101_com_ICMSSN.xml|  105  |  2  |                      pCredSN 19.00 vCredICMSSN 3.32                      |
| ICMSSN202_com_ICMSST.xml|  101  |  2  |                   BCST 11.09 pICMSST 17.00 vICMSST 0.84                  |
 