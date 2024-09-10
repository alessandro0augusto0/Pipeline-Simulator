# Simulador de Pipeline MIPS

Este é um simulador de pipeline MIPS desenvolvido como parte do trabalho para a disciplina de **Organização e Arquitetura de Computadores** no curso de **Engenharia de Computação** no **IFSULDEMINAS**. O projeto foi orientado pelo **Prof. Douglas Castilho**.

## Visão Geral

Este simulador implementa um pipeline simplificado com suporte a operações básicas de carga, armazenamento e operações aritméticas de inteiros. Ele permite a execução de arquivos de instruções em linguagem MIPS e apresenta os estágios de execução do pipeline visualmente em uma interface gráfica.

## Funcionalidades

- **Arquitetura do tipo load-store (registrador-registrador)**.
- **Encaminhamento (forwarding)** nas últimas duas etapas do pipeline, com opção de desativar essa técnica.
- Sincronismo pelo clock central informado a cada mudança no pipeline.
- **Memória de 32 bits** orientada a palavras e registradores de 32 bits.
- Informações detalhadas para cada estágio do pipeline, incluindo:
  - **IF (Instruction Fetch)**: Contador de programa (PC).
  - **ID (Instruction Decode)**: Instrução em binário e endereços de operandos.
  - **EX (Execute)**: Operação realizada com operandos.
  - **MEM (Memory Access)**: Endereço lido/escrito na memória e seus valores.
  - **WB (Write Back)**: Valores escritos no banco de registradores.

## Como Funciona

### 1. Interface Inicial

Ao iniciar o simulador, você verá a tela principal onde poderá carregar um arquivo de instruções e controlar a execução do pipeline.

<p align="center">
  <img src="https://github.com/user-attachments/assets/268ae489-7f7a-43ce-be02-b8d246a27b13" alt="Interface Inicial">
</p>

### 2. Entrada de Dados

Durante a execução, algumas instruções solicitarão a entrada de valores para serem armazenados na memória. O simulador utiliza uma janela modal para isso.

<p align="center">
  <img src="https://github.com/user-attachments/assets/e3ec14e3-6e86-4c94-a42c-1b9ef96ee50c" alt="Entrada de Dados">
</p>

### 3. Execução Finalizada

Após a conclusão da execução, o simulador exibirá o resumo dos ciclos executados, os estados finais dos registradores e os valores de memória modificados.

<p align="center">
  <img src="https://github.com/user-attachments/assets/3fbdfd32-2e80-48ab-bca8-1ccbb6ca1380" alt="Execução Finalizada">
</p>

## Como Usar

### 1. Clonando o Repositório

```bash
git clone https://github.com/alessandro0augusto0/pipeline-simulator.git
cd pipeline-simulator
```

### 2. Executando o Simulador

Compile e execute o projeto usando seu ambiente de desenvolvimento preferido. O simulador foi desenvolvido em **Java** e pode ser executado em qualquer ambiente com suporte a **Swing**.

### 3. Arquivos de Exemplo

Na pasta `src/`, você encontrará dois exemplos de arquivos `.txt` que podem ser carregados no simulador para testar a execução de instruções MIPS.

---

## Requisitos do Trabalho

Este projeto segue as diretrizes fornecidas no trabalho, que inclui:

### Arquitetura do Pipeline:

- **Load-store**
- **Sincronismo por clock**
- Estágios **IF**, **ID**, **EX**, **MEM**, **WB**

### Conjunto de Instruções:

- `add`, `sub`, `lw`, `sw`, `beq`, `bne`, `j`, entre outras.

### Exemplos de Programas:

- **Cálculo da média de dois números**
- **Verificação do maior número entre dois dados**
- **Programas criativos implementados no arquivo de teste**.

---

## Tecnologias Usadas

- **Java (Swing)**: Para a interface gráfica e execução do simulador.
- **JOptionPane**: Para entrada de dados do usuário.
- **MIPS Instruction Set**: Para simulação de instruções MIPS.

