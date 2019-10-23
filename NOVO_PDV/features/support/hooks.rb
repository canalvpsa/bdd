
Before do
    visit 'https://pdv.varejonline.com.br/pdv'
    find('input[formcontrolname=login]').set 'pdvnovo'
    find('input[formcontrolname=senha]').set 'varejonline'
    find('button', text: 'Entrar').click
  end

  After do |scenario|
    if scenario.failed?
      data = Time.now.strftime '%d-%m-%y-%H_%M_%S'
  
      if page.has_css?('noty_body', wait: 10)
      puts find('.noty_body').text
      page.driver.browser.action.move_to(page.find('.noty_progressbar').native).perform
      find('.noty_body').trigger(:mouseover)
      end
  
      page.current_window.maximize
      page.execute_script("document.body.style.zoom = '70%'")
      shot_file = page.save_screenshot('log/screenshot_' + data.to_s + '.png')
      puts 'log/screenshot_' + data.to_s + '.png'
      shot_b64 = Base64.encode64(File.open(shot_file, 'rb').read)
      embed(shot_b64, 'image/png', 'screenshot_' + data.to_s) # Cucumber anexa o screenshot no report
      page.current_window.resize_to(1280, 800)
      page.execute_script("document.body.style.zoom = '100%'")
    end
  end