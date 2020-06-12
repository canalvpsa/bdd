
const apiElements = {
    PARAMETROS:{
        URL_ENTRADA: '/estoque/api/entradas/',
        BASE: "v="+Cypress.env('base_vpsa')+"&c="+Cypress.env('base_compras')+"&e="+Cypress.env('base_estoque')+"&idUsuarioLogado=1"
    }
}
export default apiElements;