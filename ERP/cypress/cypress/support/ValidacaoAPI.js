/// <reference types="Cypress" />

//import apiElements from '../../support/elements/ApiElements'

class ValidacaoAPI {


    dataAtual() {
        var data = new Date();
        var ano = data.getFullYear();
        var mes = data.getMonth();
        var dia = data.getDate();

        return data.toISOString()
    }

    excluirEntrada(numeroNota) {
        let cookie
        cy.getCookie('CSRF-TOKEN')
            .then((c) => {
                cookie = c

                cy.request({
                    method: 'GET',
                    url: Cypress.env('baseUrl') + '/server/api/estoque/entrada/filtrar?',
                    qs: {
                        'dataInicial': '2020-01-01T03:06:19.318Z',
                        'dataFinal': this.dataAtual() + 'T01:06:19.318Z',
                        'numeroDocumento': numeroNota
                    },
                    headers: {
                        'accept': 'application/json'
                    }
                }).then((response) => {
                    if (response.status == 200) {
                        if (response.body.numberOfElements > 0) {
                            var id = response.body.content[0].id

                            cy.request({
                                method: 'DELETE',
                                url: Cypress.env('baseUrl') + '/server/api/estoque/entrada/excluir?',
                                qs: {
                                    'idEntrada': id,
                                    'v': Cypress.env('base_vpsa'),
                                    'e': Cypress.env('base_estoque'),
                                    'c': Cypress.env('base_compras'),
                                    'idUsuarioLogado': '1'
                                },
                                headers: {
                                    'accept': 'application/json',
                                    'X-CSRF-TOKEN': c.value
                                }
                            }).then((response) => {
                                expect(response).property('status').to.equal(200)
                            })

                        }
                    }
                })
            })
    }


    validaEscrituracao(numeroNotaFiscal, tipo, statusEsperado) {
        var statusEscrituracao

        cy.request({
            method: 'GET',
            url: Cypress.env('baseUrl') + '/estoque/api/notas-mercadoria/?',
           qs: {
            'numeroNotaFiscal': numeroNotaFiscal,
            'tipo': 'ENTRADA',
            'v': Cypress.env('base_vpsa'),
            'e': Cypress.env('base_estoque'),
            'c': Cypress.env('base_compras'),
            'idUsuarioLogado': '1'
           },
            headers: {
                'accept': 'application/json'
            }
        }).then((response) => {
            expect(response).property('status').to.equal(200)

            if (response.body !== null) {
                const idDocumento = response.body[response.body.length - 1].id
                statusEscrituracao = response.body[response.body.length - 1].escriturada
                console.log("id documento " + idDocumento + " Status escrituracao: " + statusEscrituracao)
                expect(statusEscrituracao).to.be.equal(statusEsperado)
            }
        })
    }
}


export default ValidacaoAPI;