#language: pt

@desconto_titulos
Funcionalidade: ERP - Desconto de títulos


Esquema do Cenário: Efetuar desconto de títulos de modalidade contas a receber
    Dado que o usuário informou a conta bancária que recebeu o desconto de títulos
    E filtrou pela modalidade "contas a receber" 
    Quando seleciona um título a receber
    E informa um valor líquido a receber
    E confirmar
    Então será exibida mensagem de sucesso
    E será criada uma conta a receber para o título descontado, no valor líquido informado
    E a conta a receber gerada será recebida na conta bancária
    E a conta a receber original permanecerá em aberto
    E ocorre os lançamentos contábeis refente a "<tipo_lançamento>" na conta débito "<conta_debito>", conta crédito "<conta_debito>" e valor "<valor>"

Exemplos:
|    tipo_lançamento   |       conta_debito       |               Crédito              |              Valor             |
|     Título gerado    |     CT - DIV (70001)     |TÍTULOS DESCONTADOS DIVERSOS (13451)|    Conta a receber original    |
|Baixa do título gerado|      C/C selecionada     |          CT - DIV (70001)          |     Valor líquido recebido     |
|   Encargos gerados   |DESPESAS BANCÁRIAS (63503)|          CT - DIV (70001)          |(valor receber - valor recebido)|



Esquema do Cenário: Efetuar desconto de títulos de modalidade cartão transação
    Dado que o usuário informou a conta bancária que recebeu o desconto de títulos
    E filtrou pela modalidade "cartão transação" 
    Quando seleciona um título a receber
    E informa um valor líquido a receber
    E confirmar
    Então será exibida mensagem de sucesso
    E será criada uma conta a receber para o título descontado, no valor líquido informado
    E a conta a receber gerada será recebida na conta bancária
    E a conta a receber original será recebida na conta bancária com valor original
       E ocorre os lançamentos contábeis refente a "<tipo_lançamento>" na conta débito "<conta_debito>", conta crédito "<conta_debito>" e valor "<valor>"

Exemplos:
|     tipo_lançamento    |        conta_debito        |               Crédito              |              Valor             |
|      Título gerado     |      CT - DIV (70001)      |TÍTULOS DESCONTADOS DIVERSOS (13451)|    Conta a receber original    |
| Baixa do título gerado |       C/C selecionada      |          CT - DIV (70001)          |     Valor líquido recebido     |
|    Encargos gerados    | DESPESAS BANCÁRIAS (63503) |          CT - DIV (70001)          |(valor receber - valor recebido)|
|Baixa do título original|TÍTULOS DESCONTADOS DIVERSOS|          CARTÕES EM GERAL          |    Conta a receber original    |