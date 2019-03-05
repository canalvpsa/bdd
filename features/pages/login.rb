module EfetuarLogin
   
    def efetuarLogin(link)
        server = ENV['url']

            if server.nil?
            url = 'https://qa.varejonline.com.br:7443'
            else
            url = server
            end

        visit url+link
        find('input[id$=username]').set 'admin'
        find('input[id$=password]').set 'varejonline'
        find('input[id$=cnpj]').set 'qa_vpsa_aut'
        find('input[id$=login-submit]').click
    end
end