module Campos

    def setarData(formcontrolname,data)
        campo_data = find('app-vo-data[formcontrolname='+formcontrolname+']').find('input')
        campo_data.send_keys [:control, 'a'], :backspace
        campo_data.set data
    end
end