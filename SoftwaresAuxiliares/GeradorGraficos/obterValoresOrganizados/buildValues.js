// --------------------------------------------------------
// GERA RESULTADO PARA GRÁFICOS
// --------------------------------------------------------
const fs = require('fs');
const path = require('path');
const { parse } = require('csv-parse/sync');

// Função para processar o CSV e gerar os dados no formato especificado
function processCSV(filePath) {
    const content = fs.readFileSync(filePath, 'utf-8');
    const records = parse(content, { columns: true, skip_empty_lines: true });

    // Identificar colunas numéricas
    const numericColumn = Object.keys(records[0]).filter(field =>
        records.some(record => record[field] !== '')
    );

    // Tabelas de referência
    const ref = {
        // Métricas discretas
        assignmentsQty: { bom: 5, ruim: 21 },
        cbo: { bom: 10, ruim: 22 },
        dit: { bom: 2, ruim: 3 },
        fanin: { bom: 2, ruim: 7 },
        fanout: { bom: 7, ruim: 15 },
        finalFieldsQty: { bom: 1, ruim: 3 },
        loc: { bom: 36, ruim: 114 },
        maxNestedBlocksQty: { bom: 1, ruim: 3 },
        modifiers: { bom: 2, ruim: 17 },
        nosi: { bom: 2, ruim: 9 },
        numbersQty: { bom: 1, ruim: 8 },
        privateFieldsQty: { bom: 1, ruim: 4 },
        publicMethodsQty: { bom: 3, ruim: 9 },
        returnQty: { bom: 2, ruim: 8 },
        rfc: { bom: 9, ruim: 29 },
        stringLiteralsQty: { bom: 3, ruim: 15 },
        totalFieldsQty: { bom: 2, ruim: 5 },
        totalMethodsQty: { bom: 5, ruim: 12 },
        uniqueWordsQty: { bom: 43, ruim: 96 },
        variablesQty: { bom: 5, ruim: 19 },
        wmc: { bom: 7, ruim: 22 },
        // Métricas Discretas sem o valor 0.
        abstractMethodsQty: { bom: 3, ruim: 6 },
        anonymousClassesQty: { bom: 2, ruim: 5 },
        comparisonsQty: { bom: 5, ruim: 13 },
        defaultFieldsQty: { bom: 3, ruim: 5 },
        defaultMethodsQty: { bom: 2, ruim: 6 },
        finalMethodsQty: { bom: 3, ruim: 7 },
        innerClassesQty: { bom: 2, ruim: 4 },
        lambdasQty: { bom: 3, ruim: 8 },
        logStatementsQty: { bom: 4, ruim: 9 },
        loopQty: { bom: 3, ruim: 8 },
        mathOperationsQty: { bom: 4, ruim: 11 },
        noc: { bom: 4, ruim: 9 },
        parenthesizedExpsQty: { bom: 3, ruim: 8 },
        privateMethodsQty: { bom: 3, ruim: 6 },
        protectedFieldsQty: { bom: 3, ruim: 7 },
        protectedMethodsQty: { bom: 2, ruim: 5 },
        publicFieldsQty: { bom: 2, ruim: 7 },
        staticFieldsQty: { bom: 2, ruim: 6 },
        staticMethodsQty: { bom: 2, ruim: 6 },
        synchronizedFieldsQty: { bom: 0, ruim: 0 },
        synchronizedMethodsQty: { bom: 3, ruim: 6 },
        tryCatchQty: { bom: 2, ruim: 6 },
        //Métricas contínuas
        lcc: { bom: 1, ruim: 0.6044 },
        lcom: { bom: 0.4, ruim: 0.7917 },
        tcc: { bom: 1, ruim: 0.4483 }
    };

    // Classificar os dados com base na tabela de referência
    function classify(metric, value) {
        const refValue = ref[metric];
        if (!refValue) return 'N/A';

        if (metric === 'lcom') {
            if (value < refValue.bom) return 'Bom';
            if (value < refValue.ruim) return 'Regular';
            return 'Ruim';
        }

        if (metric === 'lcc' || metric === 'tcc') {
            if (value === refValue.bom) return 'Bom';
            if (value >= refValue.ruim) return 'Regular';
            return 'Ruim';
        }

        if (value <= refValue.bom) return 'Bom';
        if (value <= refValue.ruim) return 'Regular';
        return 'Ruim';
    }

    let result = {};
    numericColumn.forEach(column => {
        result[column] = { Bom: 0, Regular: 0, Ruim: 0, Total: 0 };
    });

    records.forEach(data => {
        numericColumn.forEach(column => {
            const value = parseFloat(data[column]);
            if (!isNaN(value)) {
                const classificacao = classify(column, value);
                if (classificacao !== 'N/A') {
                    result[column][classificacao]++;
                    result[column].Total++;
                }
            }
        });
    });

    // Definir os grupos de métricas
    const grupos = {
        metricas_coesao: ['lcom', 'tcc', 'lcc'],
        metricas_acoplamento: ['cbo', 'fanin', 'fanout', 'rfc'],
        metricas_tamanho1: ['wmc', 'loc', 'totalMethodsQty', 'staticMethodsQty', 'publicMethodsQty', 'abstractMethodsQty', 'finalMethodsQty', 'synchronizedMethodsQty'],
        metricas_tamanho2: ['totalFieldsQty', 'publicFieldsQty', 'finalFieldsQty', 'staticFieldsQty', 'synchronizedFieldsQty', 'anonymousClassesQty', 'innerClassesQty'],
        metricas_tamanho3: ['numbersQty', 'assignmentsQty', 'stringLiteralsQty', 'variablesQty', 'lambdasQty', 'uniqueWordsQty', 'logStatementsQty', 'modifiers'],
        metricas_tamanho4: ['returnQty', 'loopQty', 'comparisonsQty', 'tryCatchQty', 'parenthesizedExpsQty', 'mathOperationsQty', 'maxNestedBlocksQty'],
        metricas_ocultacao: ['privateMethodsQty', 'protectedMethodsQty', 'defaultMethodsQty', 'privateFieldsQty', 'protectedFieldsQty', 'defaultFieldsQty'],
        metricas_heranca: ['dit', 'noc', 'nosi']
    };

    // Preparar os dados no formato desejado
    let output = {};
    for (const [grupo, metricas] of Object.entries(grupos)) {
        const dadosGrupo = [];
        
        metricas.forEach(metrica => {
            if (result[metrica] && result[metrica].Total > 0) {
                const bom = ((result[metrica].Bom / result[metrica].Total) * 100).toFixed(1);
                const regular = ((result[metrica].Regular / result[metrica].Total) * 100).toFixed(1);
                const ruim = ((result[metrica].Ruim / result[metrica].Total) * 100).toFixed(1);
                dadosGrupo.push([parseFloat(bom), parseFloat(regular), parseFloat(ruim)]);
            } else {
                dadosGrupo.push([0, 0, 0]);
            }
        });
        
        output[grupo] = metricas;
        output[`dados_${grupo.split('_')[1]}`] = dadosGrupo;
    }

    // Formatar a saída como strings no formato desejado
    let formattedOutput = '';
    for (const [grupo, metricas] of Object.entries(grupos)) {
        const nomeDados = `dados_${grupo.split('_')[1]}`;
        formattedOutput += `${grupo} = ${JSON.stringify(metricas)}\n`;
        formattedOutput += `${nomeDados} = ${JSON.stringify(output[nomeDados])}\n\n`;
    }

    fs.writeFileSync('resultados_formatados.txt', formattedOutput, 'utf-8');
    console.log('Resultados formatados gerados em resultados_formatados.txt');
}

const filePath = path.join(__dirname, 'class.csv');
processCSV(filePath);