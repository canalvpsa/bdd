module ConexaoBanco

  client = '\resources\Cliente_ORACLE\\'
  if(!File.file?(Dir.pwd.gsub!('/', '\\') + '..'+client+'oraocci11.dll'))
    puts 'Extraindo DLL Oracle'
    Zip::File.open(Dir.pwd.gsub!('/', '\\') +client+'oraocci11.zip') do |zip_file|
      zip_file.each do |f|
      fpath = File.join(Dir.pwd.gsub!('/', '\\') + client, f.name)
      zip_file.extract(f, fpath) unless File.exist?(fpath)
      end
    end
  end


  ENV['PATH'] = ENV['PATH'] + ';' + Dir.pwd.gsub!('/', '\\') + client
  ENV['NLS_LANG'] = 'BRAZILIAN PORTUGUESE_BRAZIL.WE8MSWIN1252'

  
  def consulta_banco(query)
    require 'oci8' 
      con = OCI8.new('vpsa', 'vpsa', '//bd.varejonline.com.br:1521/vpsa')
      retorno = ''
      dbConsulta = con.parse(query)
      dbConsulta.exec
      dbConsulta.fetch() {|row|
        retorno = row
        puts retorno
      }
        return retorno[0]
  end


  def update_banco(query)
    require 'oci8' 
      con = OCI8.new('vpsa', 'vpsa', '//bd.varejonline.com.br:1521/vpsa')
      dbConsulta = con.parse(query)
      dbConsulta.exec
  end

  def delete_banco(query)
    require 'oci8' 
      con = OCI8.new('vpsa', 'vpsa', '//bd.varejonline.com.br:1521/vpsa')
      dbConsulta = con.parse(query)
      dbConsulta.exec
      con.commit
  end

end
