/// <reference types="Cypress" />

var path = require('path');
process.env['PATH'] = path.join(__dirname, '/instantclient') + ';' + process.env['PATH'];
var oracledb = require('oracledb');

const inputArray = process.argv.splice(1)
const query = inputArray.join(' ')

oracledb.autoCommit = true;

async function run(query) {

  let connection;

  try {
    connection = await oracledb.getConnection(  {
          user: "vpsa",
             password: "vpsa",
             connectString: "bd.varejonline.com.br:1521/vpsa"
    });
    console.log("Successfully connected to Oracle!")
    console.log(query)
    result = await connection.execute(query);
    console.log("Result is:", result);

  } catch (err) {
    console.error(err.message);
  } finally {
    if (connection) {
      try {
        await connection.close();   // Always close connections
      } catch (err) {
        console.error(err.message);
      }
    }
  }
}

run('select * from qa_estoque_auto.entrada')