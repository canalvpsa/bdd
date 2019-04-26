#language: pt
@ERP
Funcionalidade: ERP - Configuração de crédito (limite crédito)


Cenário: Configurar limite mensal máximo "não controlar"
    Dado que o usuário já realizou as demais configurações necessárias
    Quando configura o "Limite mensal máximo" com a opção "não controlar"
    Então nenhum campo adicional é exibido


 Cenário: Configurar limite mensal máximo "sobre total de crédito"
    Dado que o usuário já realizou as demais configurações necessárias
    Quando configura o "Limite mensal máximo" com a opção "sobre total de crédito"
    Então informa o "limite em %"
    E informa o "Dia inicial do período"


 Cenário: Configurar limite mensal máximo "informar por cliente"
    Dado que o usuário já realizou as demais configurações necessárias
    Quando configura o "Limite mensal máximo" com a opção "informar por cliente"
    Então informa o "Dia inicial do período"


Esquema do Cenário: Padrão para limite mensal máximo diferente de "não controlar" configurado antes do novo campo
    Dado que o usuário já estava configurado com limite mensal máximo igual a "<opçãoLimiteMax>"
    Quando acessar a configuração de limite de crédito
    Então no limite mensal máximo o "Dia inicial do período" será igual a "01"

Exemplos:
|    opçãoLimiteMax    |
|sobre total de crédito|
| informar por cliente |
 