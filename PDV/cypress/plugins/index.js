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


// const fs = require('fs')

module.exports = (on, config) => {

}
//   on('after:screenshot', (details) => {
//     console.log(details) 
//     // const fileName = details.specName+"/"+details.takenAt.replace(/[^a-zA-Z0-9]/g, '') +".png";
//     // const newPath = `cypress/report/screenshot/`+fileName;

//     const fileName = details.specName+"/"+details.path
//     const newPath = fileName.split(" - ");
//     console.log(newPath[1])

//     return new Promise((resolve, reject) => {
//       fs.rename(details.path, newPath, (err) => {
//         if (err) return reject(err)
//         resolve({ path: newPath })
//       })
//     })
//   })
// }