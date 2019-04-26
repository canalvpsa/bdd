
module ConexaoBanco

def conectar()
    
    # Initialize connection variables.
    host = String('192.168.25.100')
    database = String('compras')
    username = String('root')
    password = String('vpsa')
    client = '\resources\mysql\lib\\'
    # Initialize connection object.
   # require 'mysql2'
    client = Mysql2::Client.new(:host => host, :username => username, :database => database, :password => password)
    puts 'Successfully created connection to database.'
end


def consulta_banco(query)
    conectar
    client.query(query)
    puts 'Finished dropping table (if existed).'
end

end