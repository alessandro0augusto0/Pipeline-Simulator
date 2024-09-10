package functions.stages;

import javax.swing.JTextArea;

public class EX {

    private JTextArea outputArea; // JTextArea para exibir as informações
    Integer ALU[] = new Integer[4];

    public EX(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    public Integer[] executeInstruction(Integer vet[]) {

        switch (vet[0]) {
            case 0:
                // noop
                outputArea.append("Sem mais instruções\n");
                break;
            case 1:
                // bne
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                ALU[3] = vet[3];
                break;
            case 2:
                // beq
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                ALU[3] = vet[3];
                break;
            case 3:
                // lw
                ALU[0] = vet[0];
                ALU[1] = vet[2];
                ALU[2] = vet[3] + vet[1];
                break;
            case 4:
                // sw
                ALU[0] = vet[0];
                ALU[1] = vet[2];
                ALU[2] = vet[3] + vet[1];
                break;
            case 5:
                // bltz
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                break;
            case 6:
                // bgtz
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                break;
            case 7:
                // add
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                ALU[3] = vet[3];
                break;
            case 8:
                // mult
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                break;
            case 9:
                // sub
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                ALU[3] = vet[3];
                break;
            case 10:
                // div
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                break;
            case 11:
                // jr
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                break;
            case 12:
                // j
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                break;
            case 13:
                // get_tc
                ALU[0] = vet[0];
                ALU[1] = vet[1];
                ALU[2] = vet[2];
                break;
            default:
                break;
        }

        return ALU;
    }
}
