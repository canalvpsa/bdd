require 'capybara'
require 'capybara/cucumber'
require 'selenium-webdriver'
require 'rspec'

  Capybara.configure do |c|
      server = ENV['url']
      if server.nil?
      @url = 'https://qa.varejonline.com.br:7443'
      else
      @url = server
    end

    c.default_driver = :selenium
    c.default_max_wait_time = 10
    c.run_server = false    
    c.default_driver = :selenium_chrome
    c.app_host = @url
  end


  RSpec.configure do |config|
    config.expect_with :rspec do |expectations|
      expectations.include_chain_clauses_in_custom_matcher_descriptions = true
    end

    config.mock_with :rspec do |mocks|
      mocks.verify_partial_doubles = true
    end

    config.shared_context_metadata_behavior = :apply_to_host_groups

    config.include Capybara::DSL

    config.before(:example) do
      page.current_window.resize_to(1280,800)
  end

end