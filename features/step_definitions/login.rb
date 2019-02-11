class Login
  def realizar_login(link)
    puts link
    visit link
    find('input[id$=username]').set 'devcanal'
    find('input[id$=password]').set 'devcanal'
    find('input[id$=cnpj]').set 'vpsa_vpsa_348'
    find('input[id$=login-submit]').click
  end
end