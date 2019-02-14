module ConexaoBanco
    ENV['PATH'] = ENV['PATH'] + ';' + Dir.pwd.gsub!('/', '\\') + '\Cliente_ORACLE'
    require 'oci8'
    def consulta_banco(query, tipoRetorno)
        con = OCI8.new('vpsa', 'vpsa', '//bd.varejonline.com.br:1521/vpsa')
        if (tipoRetorno == 'QUANTIDADE')
            num_rows = con.exec(query) do |i| end
        elsif (tipoRetorno == 'CONTEUDO')
          cursor = con.parse(query)
          cursor.exec
          cursor.fetch() {|row|
                print row
            }
        end
    end
end