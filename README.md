# Features BDD

A análise está escrita no formato BDD que descreve o comportamento para cada cenário possível, utilizando as seguintes palavras chaves:
Esquema do cenário: Permite criar tabela em que cada linha da tabela representa um cenário.
Contexto: Pré condição para execução de todos os cenários.
Dado: Pré-condição para a execução do cenário.
Quando: Ação executada pelo usuário.
Então: Resultado da ação executada.
E: Complemento da instrução informada na linha anterior.
Mas: Forma negativa do "então".


# Como gerar documentação a partir de Features:

Na pasta raiz do projeto execute:


```
yard config load_plugins true & bundle exec rake yard
```


Após concluído, abra o arquivo index.html na pasta doc.
