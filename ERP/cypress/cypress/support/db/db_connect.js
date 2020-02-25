//const oracledb = require('oracledb');

//oracledb.outFormat = oracledb.OUT_FORMAT_OBJECT;

const mypw = 'vpsa'  // set mypw to the hr schema password

async function run() {

  let connection;

  try {
    connection = await oracledb.getConnection(  {
      user          : "vpsa",
      password      : "vpsa",
      connectString : "bd.varejonline.com.br:1521/vpsa"
    });

    const result = await connection.execute(
      `select * from qa_vpsa.entidade where id = :id`,
      [1],  // bind value for :id
    );
    console.log(result.rows);

  } catch (err) {
    console.error(err);
  } finally {
    if (connection) {
      try {
        await connection.close();
      } catch (err) {
        console.error(err);
      }
    }
  }
}

run();