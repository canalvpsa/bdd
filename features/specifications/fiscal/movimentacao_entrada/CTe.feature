#language: pt

@cte
Funcionalidade: Escrituração de CTe


Cenário: Escriturar CTE previamente cadastrado
Quando o CTe estiver sido registrado na entrada
E o usuario acessa a escrituração
Então haverá um CTe pendente de escrituração


Cenário: Validar dados do CTe previamente cadastrado
Quando escriturar um CTe previamente cadastrado
Então os dados preenchidos na entradado CTe devem ser carregados para escrituração
E a data do lançamento deve ser igual a data do CTE
E não deve ser possível gerar conta a pagar
E os lançamentos contábeis dos impostos deve ser efetuado


Cenário: Cadastrar CTE
Quando criar um CTE avulso
Então todos os campos devem ser preenchidos manualmente
E deve ser possível gerar conta a pagar


Cenário: Cancelar escrituração de CTE
Quando cancelar a escrituracao do CTE
Então os lançamentos contábeis dos impostos são mantidos
E se houver provisão, ela também será mantida


Cenário: Alterar escrituração de CTE sem ajustar valores
Quando alterar uma escrituração
E não altera nenhuma informação
Então os lançamentos contábeis dos impostos são regerados
E se houver provisão, ela também será mantida


Cenário: Alterar escrituração de CTE ajustando valores
Quando alterar uma escrituração
E alterar os valores do CTE
Então os lançamentos contábeis dos impostos são regerados

Cenário: Alterar escrituração de CTE alterando provisão
Quando alterar uma escrituração de CTE que não possui provisão
E marcar para gerar provisão
Então os lançamentos contábeis são excluídos
E se houver provisão, ela também será excluídas
E será gerada novamente o lançamento contábil da provisão e dos impostos


Cenário: Alterar escrituração de CTE alterando provisão
Quando alterar uma escrituração de CTE que já possui provisão
E a provisão está em aberto
E marcar para gerar provisão
Então os lançamentos contábeis são excluídos
E se houver provisão, ela também será excluídas
E será gerada novamente o lançamento contábil da provisão e dos impostos


Cenário: Alterar escrituração de CTE alterando provisão que já está baixada
Quando alterar uma escrituração que já possui provisão baixada
E marcar para gerar provisão
Então será exibida mensagem:
"""
Não é possível excluir a provisão pois ela possui parcelas com baixa. 
"""

Cenário: Excluir escrituração de CTE cadastrado pela entrada
Quando excluir uma escrituração independente do status
E a origem do CTe foi na entrada
Então será exibida a mensagem:
"""
Não é possível excluir conhecimento de transporte originados de outro módulo.
"""


Cenário: Excluir escrituração de CTE cadastrado na escrituração
Quando excluir uma escrituração independente do status
E a origem do CTe foi na escrituração
Então a escrituração será excluída com sucesso
Mas se houver provisão
E a provisão está baixada
Então será exibida a mensagem:
"""
Não foi possível excluir o conhecimento de transporte.
"""