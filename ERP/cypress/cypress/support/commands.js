// ***********************************************
// This example commands.js shows you how to
// create various custom commands And overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })
import loc from './elements/LoginElements';
import 'cypress-file-upload';

// Cypress.Commands.add('login2', () => {
//     cy.setCookie('CSRF-TOKEN', '675726cd-07ff-4d8e-8782-be092bdc3332')
//     cy.setCookie('baseOuCNPJ', 'qa_vpsa')
//     cy.setCookie('_single-sign-on-server_session', 'BAh7B0kiD3Nlc3Npb25faWQGOgZFRkkiJTYwNWE5Yzg5MjRiN2MyMDQ0NzBkNGIxZmVmMTRjYzRiBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMTFYcm1zVy9OZzdGQ1pDTHFhQ2J4aENMMmhPTzBXd0RNTUVVdjJranVpaWs9BjsARg%3D%3D--73822576aaf927656844ef6c270125cef1eb26ca')
//     cy.setCookie('tgt', 'TGC-1579871952rM5HkToQI31jtRbWNKT')
// })

Cypress.Commands.add('login', () => {
  cy.visit(Cypress.env('baseUrl') + '/erp')
  cy.get(loc.LOGIN.USER).type('admin')
  cy.get(loc.LOGIN.PASSWORD).type('varejonline')
  cy.get(loc.LOGIN.BASE).type('qa_vpsa_auto')
  cy.get(loc.LOGIN.BTN_LOGIN).click()
  cy.get('.breadcumbs-first').should('contain', 'Página inicial')
})


// Cypress.Commands.add('oracle', (query) => {

//   var path = require('path');
//   process.env['PATH'] = path.join(__dirname, '/instantclient') + ';' + process.env['PATH'];
//   var oracledb = require('oracledb');

//   oracledb.autoCommit = true;

//   async function run(query) {

//     let connection;

//     try {
//       connection = await oracledb.getConnection({
//         user: "vpsa",
//         password: "vpsa",
//         connectString: "bd.varejonline.com.br:1521/vpsa"
//       });
//       console.log("Successfully connected to Oracle!")
//       result = await connection.execute(query);
//       console.log("Result is:", result);

//     } catch (err) {
//       console.error(err.message);
//     } finally {
//       if (connection) {
//         try {
//           await connection.close();   // Always close connections
//         } catch (err) {
//           console.error(err.message);
//         }
//       }
//     }
//   }
// })