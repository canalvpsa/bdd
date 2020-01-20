class ApertarEnter
    include Capybara::DSL
    
    def apertarEnter(campoPressionar)
        campoPressionar.send_keys(:enter)
    end
end