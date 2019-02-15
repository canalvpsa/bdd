module ConexaoBanco
  if(!File.file?(Dir.pwd.gsub!('/', '\\') + '\Cliente_ORACLE\oraociei11.dll'))
    puts 'Extraindo DLL Oracle';
    Zip::File.open(Dir.pwd.gsub!('/', '\\') + '\Cliente_ORACLE\oraociei11.zip') do |zip_file|
      zip_file.each do |f|
      fpath = File.join(Dir.pwd.gsub!('/', '\\') + '\Cliente_ORACLE\\', f.name)
      zip_file.extract(f, fpath) unless File.exist?(fpath)
      end
    end
  end

  ENV['PATH'] = ENV['PATH'] + ';' + Dir.pwd.gsub!('/', '\\') + '\Cliente_ORACLE'
  require 'oci8'
  def consulta_banco(query, tipoRetorno)
      con = OCI8.new('vpsa', 'vpsa', '//bd.varejonline.com.br:1521/vpsa')
    if (tipoRetorno == 'QUANTIDADE')
    num_rows = con.exec(query) do |i|
      end
    elsif (tipoRetorno == 'CONTEUDO')
      cursor = con.parse(query)
      cursor.exec
      cursor.fetch() {|row|
          print row
        }
    end
  end
end