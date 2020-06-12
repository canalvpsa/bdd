/// <reference types="Cypress" />

import entradaElements from '../../support/elements/Entrada_notasElements'
import EntradaNotas from '../../support/pageobjects/EntradaNotasPage'
import ValidacaoAPI from '../../support/ValidacaoAPI'
import Validacao_bd from '../../support/Validacao_bd'

const entradaNotas = new EntradaNotas();
const validacaoAPI = new ValidacaoAPI();
const validacao_bd = new Validacao_bd();

var statusEscrituracao = ''
var numeroNota = ''
var idEntrada


describe('ERP - Entrada de nota fiscal no ERP e validação do status da escrituração', () => {

    before(() => {
        cy.clearCookies()
        cy.login()
    })

    beforeEach(() => {
        Cypress.Cookies.preserveOnce('CSRF-TOKEN', 'baseOuCNPJ', '_single-sign-on-server_session', 'service', 'tgt')
        cy.visit((Cypress.env('baseUrl')) + '/server/erp/estoque/entradas/')
        // cy.get(entradaElements.ULTIMAS_NOVIDADES)
        const numeroNota = ['1010', '562']

        numeroNota.forEach(numeroNota => {
            validacaoAPI.excluirEntrada(numeroNota)
        })
    })
    //validacao_bd.consulta_escrituracao()
    const produtos = ['2541.001', '2541.002']
    const escrituracao = [
        {
            modelo: '1A',
            status: 'ESCRITURADA_EXTERNO',
        },
        {
            modelo: '1',
            status: 'ESCRITURADA_EXTERNO',
        },
        {
            modelo: '55',
            status: 'NAO_ESCRITURADA',
        },
        {
            modelo: '4',
            status: 'ESCRITURADA_EXTERNO',
        },
    ]

    escrituracao.forEach(modeloNota => {
        it(`Entrada de notas manual validando status da escrituração para modelo ${modeloNota.modelo}`, () => {
            numeroNota = '1010'

            entradaNotas.preencherDadosIniciais_tiposNota(modeloNota.modelo)
            entradaNotas.clicarBotao(entradaElements.BTN_SALVAR)//dados adicionais
            entradaNotas.incluirProdutos(produtos)
            entradaNotas.clicarBotao(entradaElements.BTN_SALVAR) //Visao geral
            entradaNotas.clicarBotao(entradaElements.BTN_SALVAR) //Pagamento
            entradaNotas.validarMensagem('A entrada 1010/1 foi efetuada com sucesso')
            validacaoAPI.validaEscrituracao('1010', 'ENTRADA', modeloNota.status)
            console.log('Excluindo nota ' + numeroNota)
            entradaNotas.excluirUtimaEntrada(numeroNota)
        })
    })


    it(`Entrada de notas manual validando status da escrituração para modelo 55 e chave de acesso`, () => {
        statusEscrituracao = 'ESCRITURADA_EXTERNO'
        numeroNota = '562'

        entradaNotas.preencherDadosIniciais_comChave()
        entradaNotas.clicarBotao(entradaElements.BTN_SALVAR)//dados adicionais
        entradaNotas.incluirProdutos(produtos)
        entradaNotas.clicarBotao(entradaElements.BTN_SALVAR) //Visao geral
        entradaNotas.clicarBotao(entradaElements.BTN_SALVAR) //Pagamento
        cy.get(entradaElements.MENSAGEM, { timeout: 100000 })
            .should('contain', 'A entrada 562/4 foi efetuada com sucesso')

        validacaoAPI.validaEscrituracao('562', 'ENTRADA', statusEscrituracao)
        console.log('Excluindo nota ' + numeroNota)
        entradaNotas.excluirUtimaEntrada(numeroNota)
    })

    it(`Entrada de notas com XML validando status da escrituração`, () => {
        statusEscrituracao = 'ESCRITURADA_EXTERNO'
        numeroNota = '562'

        entradaNotas.preencherDadosIniciais_xml('XMLs/xml_simples.xml', 'codigo_produto', 'codigo_interno')
        entradaNotas.clicarBotao(entradaElements.BTN_SALVAR)//conciliacao
        entradaNotas.clicarBotao(entradaElements.BTN_SALVAR) //produtos
        entradaNotas.clicarBotao(entradaElements.BTN_SALVAR)//dados adocionais
        entradaNotas.clicarBotao(entradaElements.BTN_SALVAR) //Visao geral
        entradaNotas.clicarBotao(entradaElements.BTN_SALVAR) //Pagamento
        entradaNotas.validarMensagem('foi efetuada com sucesso')
        validacaoAPI.validaEscrituracao('562', 'ENTRADA', statusEscrituracao)
        console.log('Excluindo nota ' + numeroNota)
        entradaNotas.excluirUtimaEntrada(numeroNota)
    })
})