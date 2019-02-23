include ConexaoBanco

module BD_validacao

    def consulta_escrituracao()
        query = 'select nf.ESCRITURADA from QA_ESTOQUE_AUT.entrada entrada
        inner join QA_ESTOQUE_AUT.entrada_nota_fiscal entrada_nf
        on entrada.ID = entrada_nf.ENTRADA_ID
        inner join QA_ESTOQUE_AUT.NOTA_FISCAL nf
        on entrada_nf.NOTASFISCAIS_ID = nf.ID where ROWNUM <= 1
        order by nf.id desc'
        return consulta_banco(query)
    end

    def consulta_ultimaEntrada()
        query = 'select ent.NUMERODOCUMENTO from QA_ESTOQUE_AUT.entrada ent where ROWNUM <= 1
        order by ent.id desc'
        return consulta_banco(query)
    end
end
