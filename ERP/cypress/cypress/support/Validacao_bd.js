/// <reference types="Cypress" />

//import oracleDatabase from '../support/oracle_database.js'
var base_estoque = Cypress.env('base_estoque')

class Validacao_bd {

    select(query) {
        try {
            cy.log('chegou aqui')
            //  cy.exec('npm run /cypress/support/oracle_database.js select * from qa_estoque_auto.entrada')
           // oracleDatabase.run(query)

            console.log("enviou select")
        } catch (error) {
            console.log(error);
        }
    }
    consulta_escrituracao() {
        var query = "select * from qa_estoque_auto.entrada"

        // "select * from ("
        // +"select nf.ESCRITURADA from "+base_estoque+".entrada entrada "
        // +"inner join "+base_estoque+".entrada_nota_fiscal entrada_nf "
        // +"on entrada.ID = entrada_nf.ENTRADA_ID "
        // +"inner join"+ base_estoque+".NOTA_FISCAL nf "
        // +"on entrada_nf.NOTASFISCAIS_ID = nf.ID "
        // +"order by nf.id desc) "
        // +"where ROWNUM <= 1"
        //         cy.log(query)
        // select(query)
        this.select(query)
    }
}

//module.exports.select = select;

export default Validacao_bd;

//      consulta_entrada, (numeroNota) => {
//             var query = `select * from (QA_ESTOQUE_AUT.entrada
//                         order by nf.id desc)
//                 where ROWNUM <= 1`
//             return select(query)
//             }



//     consulta_ultimaEntrada = () => {
//         var query = `select * from (QA_ESTOQUE_AUT.entrada
//                     order by nf.id desc)
//             where ROWNUM <= 1`
//         return select(query)
//         }


//     consulta_ultimaEntrada = () => {
//         var query = `select * from (
//             select ent.NUMERODOCUMENTO from QA_ESTOQUE_AUT.entrada ent order by ent.id desc)
//             where ROWNUM <= 1`
//         return select(query)
//         }

//     consulta_parametro_custo = () => {
//         var query = 'SELECT id FROM QA_VPSA_AUT.PARAMETRO WHERE CHAVE = 5'
//         return select(query)
//        }


// //     parametro_decimais_custo, (casas_decimais) => {
// //         query = 'UPDATE QA_VPSA_AUT.PARAMETROINTEGER SET VALOR = '+casas_decimais.to_s+'
// //                  WHERE ID = (SELECT id FROM QA_VPSA_AUT.PARAMETRO WHERE CHAVE = 5)'
// //                  'UPDATE QA_VPSA_AUT.PARAMETRO SET DATAALTERACAO = SYSDATE WHERE chave = 5'
// //         return select(query)
// // }

// //     parametro_custo_impostos, (boolean) => {
// //         query = 'UPDATE QA_VPSA_AUT.PARAMETROBOOLEAN SET VALOR = '+boolean+' 
// //                  WHERE ID = (SELECT id FROM QA_VPSA_AUT.PARAMETRO WHERE CHAVE = 80);'
// //                 'UPDATE QA_VPSA_AUT.PARAMETRO SET DATAALTERACAO = SYSDATE WHERE chave = 5'"
// //         return select(query)
// //     }


//     data_alteracao_parametro() => {
//         query = "UPDATE QA_ESTOQUE_AUT.PARAMETRO SET DATAALTERACAO = SYSDATE WHERE chave = 5"
//         select(query)
//     }


//    deletar_ultimo_usuario() => {
//         data = Time.now.strftime '%d/%m/%y 00:00:00'
//         ultimo_usuario = consulta_banco("SELECT ID FROM QA_VPSA_AUT.USUARIO WHERE DATACRIACAO >= '"+data.to_s+"' AND ROWNUM <= 1 order by id desc")

//         if !ultimo_usuario.nil?
//             terceiro = consulta_banco("SELECT ID FROM QA_VPSA_AUT.terceiro WHERE USUARIO_ID =" +ultimo_usuario.to_s)
//             delete_banco('DELETE FROM QA_VPSA_AUT.EMAIL WHERE EMAIL_TERCEIRO = '+terceiro.to_s)
//             delete_banco('DELETE QA_ESTOQUE_AUT.VPSA_TERCEIRO WHERE referenciaid = '+terceiro.to_s)
//             delete_banco('DELETE QA_VPSA_AUT.TERCEIRO WHERE USUARIO_ID = '+ultimo_usuario.to_s)
//             delete_banco('DELETE QA_VPSA_AUT.USUARIO_ENTIDADE WHERE USUARIO_ID = '+ultimo_usuario.to_s)
//             delete_banco('DELETE QA_VPSA_AUT.USUARIO WHERE ID = '+ultimo_usuario.to_s)
//         else
//             puts 'Nenhum usuario foi cadastrado no banco de dados'
//         end
//    }


//     consulta_custoProduto, (codigoInterno) => {

//         case codigoInterno

//         when '2541.001'
//             idProduto = '863'

//         when '2541.002'
//             idProduto = '864'

//         else
//             puts 'codigo produto nao previsto'
//             idProduto = ''
//         end

//         query = 'select * from (
//             select CAST(VRESTOQUE AS varchar(10)) from QA_ESTOQUE_AUT.MOVTO_ESTOQUE
//             where MERCADORIADNA_ID = '+idProduto+' ORDER BY id desc) 
//             where ROWNUM <= 1'
//         return consulta_banco(query)
//     end
//         }
