import matplotlib.pyplot as plt
import numpy as np
import seaborn as sns

# Configurações comuns
modelos = ['GPT', 'Deep', 'Copilot', 'Gemini']
categorias = ['Bom', 'Regular', 'Ruim']

# Função para criar heatmap para um grupo de métricas
def criar_heatmap(grupo_nome, metricas, dados_metricas):
    fig, axes = plt.subplots(1, 3, figsize=(18, 6), sharey=True)
    fig.suptitle(f'Métricas de {grupo_nome}', y=1.05, fontsize=14)
    
    for i, categoria in enumerate(categorias):
        dados_categoria = dados_metricas[:, :, i].T  # Transpor para métricas x modelos
        
        sns.heatmap(
            dados_categoria,
            annot=True,
            fmt=".1f",
            cmap="YlGn" if i == 0 else "YlOrBr" if i == 1 else "OrRd",
            vmin=0,
            vmax=100,
            ax=axes[i],
            cbar_kws={'label': 'Porcentagem (%)'},
            linewidths=0.5,
            linecolor='white'
        )
        
        axes[i].set_title(f'Avaliação: {categoria}')
        axes[i].set_xlabel('Modelos')
        axes[i].set_xticklabels(modelos, rotation=45)
        if i == 0:
            axes[i].set_ylabel('Métricas')
        else:
            axes[i].set_ylabel('')
        axes[i].set_yticklabels(metricas, rotation=0)
    
    plt.tight_layout()
    plt.show()

# ================================================
# DADOS PARA CADA GRUPO DE MÉTRICAS 
# ================================================

# Gráfico 1: Métricas Coesão
metricas_coesao = ['lcom', 'tcc', 'lcc']
dados_coesao = np.array([
    [[63.3, 0.0, 25.6], [30.6, 33.0, 25.4], [27.8, 7.8, 53.3]],  # GPT
    [[53.5, 0.0, 24.2], [20.0, 39.8, 18.0], [19.7, 7.1, 50.9]],  # Deep
    [[40.8, 0.0, 25.9], [23.8, 23.8, 19.1], [19.8, 3.7, 43.2]],  # Copilot
    [[20.0, 13.3, 16.1], [14.4, 2.8, 16.1], [6.7, 10.6, 0.0]]  # Gemini
])

# Gráfico 2: Métricas Acoplamento
metricas_acoplamento = ['cbo', 'fanin', 'fanout', 'rfc']
dados_acoplamento = np.array([
    [[100.0, 0.0, 0.0], [75.9, 24.1, 0.0], [98.8, 1.2, 0.0], [82.3, 16.6, 1.1]],  # GPT
    [[100.0, 0.0, 0.0], [76.3, 23.7, 0.0], [97.8, 2.2, 0.0], [80.0, 20.0, 0.0]],  # Deep
    [[100.0, 0.0, 0.0], [76.3, 23.7, 0.0], [98.9, 1.1, 0.0], [81.1, 17.8, 1.1]],  # Copilot
    [[50.0, 13.9, 0.0], [52.8, 30.6, 0.0], [66.7, 11.1, 0.0], [50.0, 30.6, 0.0]]  # Gemini
])

# Gráfico 3: Métricas Tamanho 1
metricas_tamanho1 = ['wmc', 'loc', 'totalMethodsQty', 'staticMethodsQty', 'publicMethodsQty', 'abstractMethodsQty', 'finalMethodsQty', 'synchronizedMethodsQty']
dados_tamanho1 = np.array([
    [[48.8, 49.0, 9.9], [56.4, 37.5, 6.1], [54.9, 42.9, 2.2], [95.4, 2.9, 1.7], [42.6, 54.9, 2.5], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0]],  # GPT
    [[45.0, 45.7, 6.1], [49.3, 39.3, 11.4], [48.0, 49.5, 2.5], [95.5, 2.8, 1.7], [37.3, 57.1, 5.6], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0]],  # Deep
    [[50.0, 47.9, 2.2], [65.7, 26.1, 1.1], [63.0, 34.8, 2.2], [97.8, 0.0, 2.2], [50.7, 47.1, 2.2], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0]],  # Copilot
    [[12.8, 45.8, 8.0], [21.1, 41.1, 5.8], [12.8, 51.7, 2.8], [75.0, 8.3, 0.0], [8.9, 61.1, 5.6], [77.8, 0.0, 0.0], [77.8, 0.0, 0.0], [77.8, 0.0, 0.0]]  # Gemini
])

# Gráfico 4: Métricas Tamanho 2
metricas_tamanho2 = ['totalFieldsQty', 'publicFieldsQty', 'finalFieldsQty', 'staticFieldsQty', 'synchronizedFieldsQty', 'anonymousClassesQty', 'innerClassesQty']
dados_tamanho2 = np.array([
    [[65.1, 32.1, 2.8], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [95.5, 3.9, 0.6], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0]],  # GPT
    [[61.2, 32.0, 6.8], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [95.5, 4.5, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0]],  # Deep
    [[71.3, 28.7, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0]],  # Copilot
    [[31.1, 31.9, 11.7], [77.8, 11.1, 0.0], [77.8, 11.1, 0.0], [72.2, 13.9, 0.0], [77.8, 0.0, 0.0], [77.8, 0.0, 0.0], [77.8, 0.0, 0.0]]  # Gemini
])

# Gráfico 5: Métricas Tamanho 3
metricas_tamanho3 = ['numbersQty', 'assignmentsQty', 'stringLiteralsQty', 'variablesQty', 'lambdasQty', 'uniqueWordsQty', 'logStatementsQty', 'modifiers']
dados_tamanho3 = np.array([
    [[58.0, 32.1, 9.9], [56.4, 41.2, 2.4], [53.6, 28.6, 17.7], [70.4, 24.5, 5.1], [96.4, 3.9, 0.0], [90.4, 9.6, 0.0], [100.0, 0.0, 0.0], [92.0, 0.0, 8.0]],  # GPT
    [[51.7, 28.5, 16.6], [56.4, 43.6, 0.0], [49.3, 37.0, 13.6], [68.7, 29.0, 2.3], [97.8, 2.2, 0.0], [91.8, 8.2, 0.0], [100.0, 0.0, 0.0], [94.4, 0.0, 5.6]],  # Deep
    [[56.3, 39.4, 4.3], [70.7, 29.3, 0.0], [53.0, 35.8, 11.2], [80.7, 16.9, 2.3], [97.8, 2.2, 0.0], [97.2, 2.8, 0.0], [100.0, 0.0, 0.0], [91.7, 0.0, 8.3]],  # Copilot
    [[19.4, 51.1, 15.6], [16.7, 74.4, 5.6], [16.7, 43.3, 26.1], [45.6, 45.6, 2.8], [77.8, 2.8, 0.0], [59.4, 32.2, 0.0], [77.8, 0.0, 0.0], [68.3, 0.0, 8.3]]  # Gemini
])

# Gráfico 6: Métricas Tamanho 4
metricas_tamanho4 = ['returnQty', 'loopQty', 'comparisonsQty', 'tryCatchQty ', 'parenthesizedExpsQty', 'mathOperationsQty', 'maxNestedBlocksQty']
dados_tamanho4 = np.array([
    [[59.3, 39.8, 0.8], [92.9, 7.1, 0.0], [94.5, 5.5, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [87.1, 8.3, 4.6], [71.9, 23.5, 4.6]],  # GPT
    [[48.8, 51.2, 0.0], [95.5, 4.5, 0.0], [96.7, 3.3, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [84.4, 10.0, 5.6], [74.1, 22.4, 3.5]],  # Deep
    [[65.7, 34.3, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [88.9, 5.6, 5.6], [78.5, 18.2, 3.3]],  # Copilot
    [[29.4, 65.6, 0.0], [63.9, 19.4, 0.0], [75.6, 8.3, 0.0], [77.8, 0.0, 0.0], [77.8, 0.0, 0.0], [38.3, 37.8, 5.0], [56.8, 26.3, 2.9]]  # Gemini
])

# Gráfico 7: Ocultação de informações
metricas_ocultacao = ['privateMethodsQty', 'protectedMethodsQty', 'defaultMethodsQty', 'privateFieldsQty', 'protectedFieldsQty', 'defaultFieldsQty']
dados_ocultacao = np.array([
    [[93.8, 4.1, 2.1], [100.0, 0.0, 0.0], [99.0, 1.0, 0.0], [48.9, 48.1, 3.0], [97.2, 2.8, 0.0], [87.0, 13.0, 0.0]],  # GPT
    [[93.2, 4.0, 2.8], [100.0, 0.0, 0.0], [99.0, 1.0, 0.0], [44.5, 50.6, 4.9], [97.5, 2.5, 0.0], [91.7, 8.3, 0.0]],  # Deep
    [[97.8, 2.2, 0.0], [100.0, 0.0, 0.0], [98.9, 1.1, 0.0], [53.0, 44.2, 2.8], [100.0, 0.0, 0.0], [97.8, 2.2, 0.0]],  # Copilot
    [[51.7, 16.7, 0.0], [77.8, 0.0, 0.0], [77.8, 0.0, 0.0], [17.8, 60.0, 11.1], [63.9, 13.9, 0.0], [75.0, 11.1, 0.0]]  # Gemini
])

# Gráfico 8: Métricas Herança
metricas_heranca = ['dit', 'noc', 'nosi']
dados_heranca = np.array([
    [[100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [86.7, 10.6, 2.8]],  # GPT
    [[100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [88.9, 8.0, 3.1]],  # Deep
    [[100.0, 0.0, 0.0], [100.0, 0.0, 0.0], [88.9, 9.4, 1.7]],  # Copilot
    [[77.8, 0.0, 0.0], [77.8, 0.0, 0.0], [71.1, 20.0, 0.0]]  # Gemini
])


# ================================================
# GERAR OS GRÁFICOS
# ================================================

# Lista de todos os grupos para iterar
grupos = [
    ('Coesão', metricas_coesao, dados_coesao),
    ('Acoplamento', metricas_acoplamento, dados_acoplamento),
    ('Tamanho 1', metricas_tamanho1, dados_tamanho1),
    ('Tamanho 2', metricas_tamanho2, dados_tamanho2),
    ('Tamanho 3', metricas_tamanho3, dados_tamanho3),
    ('Tamanho 4', metricas_tamanho4, dados_tamanho4),
    ('Ocultação de Informações', metricas_ocultacao, dados_ocultacao),
    ('Herança', metricas_heranca, dados_heranca)
]

# Gerar os gráficos
for nome, metricas, dados in grupos:
    criar_heatmap(nome, metricas, dados)