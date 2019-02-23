module EfetuarLogin
    def efetuarLogin(link)
        visit link
        find('input[id$=username]').set 'admin'
        find('input[id$=password]').set 'varejonline'
        find('input[id$=cnpj]').set 'qa_vpsa_aut'
        find('input[id$=login-submit]').click
    end
end