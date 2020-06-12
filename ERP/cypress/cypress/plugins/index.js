// ***********************************************************
// This example plugins/index.js can be used to load plugins
//
// You can change the location of this file or turn off loading
// the plugins file with the 'pluginsFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/plugins-guide
// ***********************************************************

// This function is called when a project is opened or re-opened (e.g. due to
// the project's config changing)

module.exports = (on, config) => {
  // `on` is used to hook into various events Cypress emits
  // `config` is the resolved Cypress config
}

const oracle = require('oracledb')

module.exports = (on, config) => {
  config = configSwitcher.loadEnvConfig(config);

  tasks = db.loadDBPlugin(oracle, config.db);
  on('task', tasks);
}

module.exports = (on, config) => {
  on('after:screenshot', (details) => {
    console.log(details) // print all details to terminal

    const newPath = '/new/path/to/screenshot.png'

    return new Promise((resolve, reject) => {
      fs.rename(details.path, newPath, (err) => {
        if (err) return reject(err)

        // because we renamed/moved the image, resolve with the new path
        // so it is accurate in the test results
        resolve({ path: newPath })
      })
    })
  })
}