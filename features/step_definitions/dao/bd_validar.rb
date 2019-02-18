include ConexaoBanco

module BD_validacao


def consulta_escriturao()
query = 'select nf.ESCRITURADA from QA_ESTOQUE.entrada entrada
inner join QA_ESTOQUE.entrada_nota_fiscal entrada_nf
on entrada.ID = entrada_nf.ENTRADA_ID
inner join QA_ESTOQUE.NOTA_FISCAL nf
on entrada_nf.NOTASFISCAIS_ID = nf.ID
where entrada.numerodocumento = "1010"'

consulta_banco(query, 'CONTEUDO')
end
end
