=begin
module EfetuarLogin
    def efetuarLogin(link)
        visit link
        find('input[id$=username]').set 'devcanal'
        find('input[id$=password]').set 'devcanal'
        find('input[id$=cnpj]').set 'vpsa_vpsa_348'
        find('input[id$=login-submit]').click
    end
end
=end

module EfetuarLogin
    def efetuarLogin(link)
        visit link
        find('input[id$=username]').set 'admin'
        find('input[id$=password]').set 'varejonline'
        find('input[id$=cnpj]').set 'qa_vpsa'
        find('input[id$=login-submit]').click
    end
end