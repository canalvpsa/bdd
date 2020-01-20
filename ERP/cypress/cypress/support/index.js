// ***********************************************************
// This example support/index.js is processed And
// loaded automatically before your test files.
//
// This is a great place to put global configuration And
// behavior that modifies Cypress.
//
// You can change the location of this file or turn off
// automatically serving support files with the
// 'supportFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/configuration
// ***********************************************************

// Import commands.js using ES2015 syntax:
import './commands'

require('cypress-xpath')

// Alternatively you can use CommonJS syntax:
// require('./commands')

Cypress.SelectorPlayground.defaults({
    selectorPriority: ['id', 'class', 'attributes', 'tag', 'nth-child' ]
  })

  Cypress.on('window:before:load', function (win) {
    const original = win.EventTarget.prototype.addEventListener
  
    win.EventTarget.prototype.addEventListener = function () {
      if (arguments && arguments[0] === 'beforeunload') {
        return
      }
      return original.apply(this, arguments)
    }
  
    Object.defineProperty(win, 'onbeforeunload', {
      get: function () { },
      set: function () { }
    })
  })