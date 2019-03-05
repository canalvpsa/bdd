module EfetuarLogin
   
    def efetuarLogin(link)
            if ENV['url'].nil?
            url = 'https://qa.varejonline.com.br:7443'
            else
            url = ENV['url']
            end

        visit url+link

        find('input[id$=username]').set 'admin'
        find('input[id$=password]').set 'varejonline'
        find('input[id$=cnpj]').set 'qa_vpsa_aut'
        find('input[id$=login-submit]').click
    end
end