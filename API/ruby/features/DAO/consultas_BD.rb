include ConexaoBanco

module BD_consultas

    def consulta_token()
        query = 'SELECT access_token FROM relatorios2.vpsa_user WHERE id_user = 101';
        return consulta_banco(query)
    end
end
