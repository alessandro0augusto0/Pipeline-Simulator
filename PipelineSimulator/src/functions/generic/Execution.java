package functions.generic;

import javax.swing.JTextArea;
import java.util.ArrayList;
import functions.stages.EX;
import functions.stages.ID;
import functions.stages.IF;
import functions.stages.MEM;
import view.PipelineSimulatorUI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Execution {
    private IF exe1;
    private ID exe2;
    private EX exe3;
    private MEM exe4;

    private ArrayList<String> instructions = new ArrayList<>();
    private ArrayList<String> executionLog = new ArrayList<>(); // Para acumular os logs
    private JTextArea outputArea;
    private PipelineSimulatorUI ui; // Referência para a UI
    private boolean isPaused = false; // Flag de pausa

    public Execution(JTextArea outputArea, PipelineSimulatorUI ui) {
        this.outputArea = outputArea;
        this.ui = ui;
        exe1 = new IF(outputArea);
        exe2 = new ID(outputArea);
        exe3 = new EX(outputArea);
        exe4 = new MEM(outputArea);
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    // Método para pausar e retomar a execução
    public void togglePause() {
        isPaused = !isPaused; // Alterna entre pausado e não pausado
        if (isPaused) {
            outputArea.append("Execução pausada.\n");
        } else {
            outputArea.append("Execução retomada.\n");
        }
    }

    public void execution() {
        if (instructions.isEmpty()) {
            outputArea.append("Nenhuma instrução carregada.\n");
            return;
        }

        String[] pipelineStages = new String[5]; // Estágios: IF, ID, EX, MEM, WB
        int instructionCount = instructions.size();
        int cyclesCompleted = 0; // Contador de ciclos
        StringBuilder summary = new StringBuilder(); // Resumo da execução
        Map<Integer, Integer> modifiedRegisters = new HashMap<>(); // Registradores modificados
        Map<Integer, Integer> modifiedMemory = new HashMap<>(); // Memória modificada

        while (cyclesCompleted < instructionCount + 4) { // Ciclos extras para completar o pipeline
            if (isPaused) {
                try {
                    Thread.sleep(500);
                    continue; 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Avançar as instruções nos estágios
            if (cyclesCompleted >= 4) {
                pipelineStages[4] = pipelineStages[3]; // WB recebe MEM
            }
            if (cyclesCompleted >= 3) {
                pipelineStages[3] = pipelineStages[2]; // MEM recebe EX
            }
            if (cyclesCompleted >= 2) {
                pipelineStages[2] = pipelineStages[1]; // EX recebe ID
            }
            if (cyclesCompleted >= 1) {
                pipelineStages[1] = pipelineStages[0]; // ID recebe IF
            }

            // Fetch uma nova instrução no estágio IF
            if (cyclesCompleted < instructionCount) {
                String instruction = null;
                try {
                    instruction = instructions.get(PC.getPc()); // Verifica se há instrução válida no PC
                } catch (IndexOutOfBoundsException e) {
                    outputArea.append("Erro: Tentativa de acessar uma instrução fora do limite. Ciclo " + cyclesCompleted + ".\n");
                    break;
                }

                instruction = instruction.replace("$lo", "$33");
                instruction = instruction.replace("$hi", "$32");
                pipelineStages[0] = instruction; // IF recebe a nova instrução
                PC.setPc(PC.getPc() + 1); // Incrementa o PC
            } else {
                pipelineStages[0] = "noop"; // Quando não há mais instruções, preenche com noop
            }

            // Atualiza o Pipeline visualmente
            ui.updatePipelineStages(pipelineStages);

            // Executa a instrução que está no estágio EX
            if (pipelineStages[2] != null && !pipelineStages[2].equals("noop")) {
                String[] stage1 = exe1.readingInstruction(pipelineStages[2]);
                Integer[] stage2 = exe2.decodeInstruction(stage1);

                if (stage2 != null) { // Verifica se a decodificação retornou valores válidos
                    Integer[] stage3 = exe3.executeInstruction(stage2);
                    exe4.memoryAccess(stage3);

                    // Captura resultados da execução para o resumo
                    summary.append("Instrução: ").append(pipelineStages[2]).append(" - Resultado: ");
                    summary.append(Arrays.toString(stage3)).append("\n");

                    // Armazena registradores e memória modificados
                    if (stage3[0] == 3 || stage3[0] == 4) { // lw ou sw
                        int register = stage3[1];
                        int value = exe4.getRegisterValue(register);
                        modifiedRegisters.put(register, value);
                    }
                    if (stage3[0] == 13) { // get_tc
                        int memAddress = stage3[1];
                        int value = exe4.getMemoryValue(memAddress);
                        modifiedMemory.put(memAddress, value);
                    }
                } else {
                    outputArea.append("Erro: Instrução não pôde ser decodificada no ciclo " + cyclesCompleted + ".\n");
                }
            }

            outputArea.append("Ciclo " + cyclesCompleted + " concluído.\n");

            // Delay para permitir visualização dos estágios
            try {
                Thread.sleep(1000); // 1 segundo de delay entre cada ciclo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cyclesCompleted++; // Incrementa o contador de ciclos
        }

        outputArea.append("Execução concluída.\n");

        // Limpeza final do pipeline após a execução completa
        for (int i = 0; i < 5; i++) {
            pipelineStages[i] = ""; // Limpa os estágios para a próxima execução
        }

        // Atualiza o pipeline uma última vez para limpar visualmente
        ui.updatePipelineStages(pipelineStages);

        // Exibe o resumo da execução
        outputArea.append("*** Resumo da Execução ***\n");
        outputArea.append(summary.toString());

        // Exibe apenas os registradores modificados
        outputArea.append("Estado Final dos Registradores:\n");
        for (Map.Entry<Integer, Integer> entry : modifiedRegisters.entrySet()) {
            outputArea.append("R" + entry.getKey() + ": " + entry.getValue() + "\n");
        }

        // Exibe apenas os valores de memória modificados
        outputArea.append("Memória Modificada:\n");
        for (Map.Entry<Integer, Integer> entry : modifiedMemory.entrySet()) {
            outputArea.append("M[" + entry.getKey() + "]: " + entry.getValue() + "\n");
        }
    }
}

