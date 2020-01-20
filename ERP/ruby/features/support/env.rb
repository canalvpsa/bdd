# frozen_string_literal: true

require 'capybara'
require 'capybara/cucumber'
require 'capybara/rspec'
require 'selenium-webdriver'
require 'rspec'
#require 'rspec/expectations'

# CONFIG = YAML.load_file(File.join(Dir.pwd, "features/support/config/#{ENV['ENV_TYPE']}.yaml"))

# case ENV['BROWSER']
# when 'firefox'
#   @driver = :selenium

# when 'chrome'
#   @driver = :selenium_chrome

# when 'headless'
#   @driver = :selenium_chrome_headless

# else
#   puts 'invalid browser'
# end

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
driver = c.default_driver = :selenium_chrome
c.app_host = @url
end


# Capybara.configure do |config|
#   config.default_driver = @driver
#   config.app_host = CONFIG['url']
#   config.default_max_wait_time = 10
#   config.run_server = false
# end

RSpec.configure do |config|
  config.expect_with :rspec do |expectations|
    expectations.include_chain_clauses_in_custom_matcher_descriptions = true
    #expectations.syntax = :should
  end


  config.mock_with :rspec do |mocks|
    mocks.verify_partial_doubles = true
  end

  config.shared_context_metadata_behavior = :apply_to_host_groups
  config.include Capybara::DSL
end

Before do
  Capybara.page.current_window.resize_to(1280, 800)
end

Before('@seminovo', '@troca_nacional') do
  pending
end
