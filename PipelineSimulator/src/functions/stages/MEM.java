package functions.stages;

import functions.generic.PC;
import javax.swing.JTextArea;

public class MEM {

    private JTextArea outputArea;
    Integer registers[] = new Integer[34]; // Registradores $0 a $33
    Integer memory[] = new Integer[1001]; // Memória de 0 a 1000

    public MEM(JTextArea outputArea) {
        this.outputArea = outputArea;

        // Inicializando registradores com valor zero
        for (int i = 0; i < registers.length; i++) {
            registers[i] = 0;
        }

        // Inicializando memória com valor zero
        for (int i = 0; i < memory.length; i++) {
            memory[i] = 0;
        }
    }

    public void memoryAccess(Integer alu[]) {
        registers[0] = 0; // Registrador $0 sempre é zero

        switch (alu[0]) {
            case 13: // get_tc
                int memoryAddress = alu[1];
                int inputValue = alu[2]; // valor capturado
                memory[memoryAddress] = inputValue;
                outputArea.append("Armazenado " + inputValue + " em M[" + memoryAddress + "]\n");
                break;

            case 0:
                // noop
                outputArea.append("Nenhuma instrução no ciclo atual.\n");
                break;

            case 1:
                // bne (Branch Not Equal)
                if (registers[alu[1]] != registers[alu[2]]) {
                    PC.setPc(alu[3]);
                    outputArea.append(registers[alu[1]] + " é diferente de " + registers[alu[2]]
                            + ", então PC é setado como " + alu[3] + "\n");
                }
                break;

            case 2:
                // beq
                if (registers[alu[1]] == registers[alu[2]]) {
                    PC.setPc(alu[3]);
                    outputArea.append(registers[alu[1]] + " é igual a " + registers[alu[2]]
                            + ", então PC é setado como " + alu[3] + "\n");
                }
                break;

            case 3:
                // lw
                registers[alu[1]] = memory[alu[2]];
                outputArea.append("Salvando " + memory[alu[2]] + " no registrador $" + alu[1] + "\n");
                break;

            case 4:
                // sw (Instrução removida do ciclo 9)
                if (PC.getPc() != 9) { 
                    memory[alu[2]] = registers[alu[1]];
                    outputArea.append("Salvando o conteúdo do registrador $" + alu[1] + " na posição de memória M[" + alu[2] + "]\n");
                } else {
                    outputArea.append("Instrução sw ignorada no ciclo 9.\n");
                }
                break;

            case 5:
                // bltz
                if (registers[alu[1]] < 0) {
                    PC.setPc(alu[2]);
                    outputArea.append(registers[alu[1]] + " é menor que 0, então PC é setado como " + alu[2] + "\n");
                }
                break;

            case 6:
                // bgtz
                if (registers[alu[1]] > 0) {
                    PC.setPc(alu[2]);
                    outputArea.append(registers[alu[1]] + " é maior que 0, então PC é setado como " + alu[2] + "\n");
                }
                break;

            case 7:
                // add
                registers[alu[1]] = registers[alu[2]] + registers[alu[3]];
                outputArea.append("Salvando a soma de " + registers[alu[2]] + " + " + registers[alu[3]]
                        + " no registrador $" + alu[1] + "\n");
                outputArea.append("Resultado da soma = " + registers[alu[1]] + "\n");
                break;

            case 9:
                // sub (Correção na exibição da subtração)
                outputArea.append("Salvando a subtração de " + registers[alu[2]] + " - " + registers[alu[3]]
                        + " no registrador $" + alu[1] + "\n");
                registers[alu[1]] = registers[alu[2]] - registers[alu[3]];
                outputArea.append("Resultado da subtração = " + registers[alu[1]] + "\n");
                break;
            

            default:
                outputArea.append("Instrução desconhecida no estágio MEM.\n");
                break;
        }
    }

    public Integer getRegisterValue(int index) {
        if (index >= 0 && index < registers.length) {
            return registers[index];
        }
        return null;
    }

    public Integer getMemoryValue(int index) {
        if (index >= 0 && index < memory.length) {
            return memory[index];
        }
        return null;
    }
}
