package functions.instructions;

import functions.generic.Conversion;
import javax.swing.JTextArea;

public class Register {
    private JTextArea outputArea;

    public Register(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    public Integer[] add(String[] ins) {
        String op = ins[0];
        Integer rd = Integer.parseInt(ins[1].substring(1, ins[1].length()-1));
        Integer rs = Integer.parseInt(ins[2].substring(1, ins[2].length()-1));
        Integer rt = Integer.parseInt(ins[3].substring(1, ins[3].length()));
        
        String opBinary = "000000";
        String rsBinary = Conversion.intToBinaryString(rs, 5);
        String rtBinary = Conversion.intToBinaryString(rt, 5);
        String rdBinary = Conversion.intToBinaryString(rd, 5);
        String shampt = "00000";
        String funct = "100000";
        outputArea.append("Opcode instruction: " + opBinary + " " + rsBinary + " " + rtBinary + " " + rdBinary + " " + shampt + " " + funct + "\n");
        
        Integer values[] = {7, rd, rs, rt};
        
        return values;
    }

    public Integer[] mult(String[] ins) {
        String op = ins[0];
        Integer rs = Integer.parseInt(ins[1].substring(1, ins[1].length()-1));
        Integer rt = Integer.parseInt(ins[2].substring(1, ins[2].length()));
        
        String opBinary = "000000";
        String rsBinary = Conversion.intToBinaryString(rs, 5);
        String rtBinary = Conversion.intToBinaryString(rt, 5);
        String rdBinary = "00000";
        String shampt = "00000";
        String funct = "011000";
        outputArea.append("Opcode instruction: " + opBinary + " " + rsBinary + " " + rtBinary + " " + rdBinary + " " + shampt + " " + funct + "\n");
        
        Integer values[] = {8, rs, rt};
        
        return values;
    }

    public Integer[] sub(String[] ins) {
        String op = ins[0];
        Integer rd = Integer.parseInt(ins[1].substring(1, ins[1].length()-1));
        Integer rs = Integer.parseInt(ins[2].substring(1, ins[2].length()-1));
        Integer rt = Integer.parseInt(ins[3].substring(1, ins[3].length()));
        
        String opBinary = "000000";
        String rsBinary = Conversion.intToBinaryString(rs, 5);
        String rtBinary = Conversion.intToBinaryString(rt, 5);
        String rdBinary = Conversion.intToBinaryString(rd, 5);
        String shampt = "00000";
        String funct = "100010";
        outputArea.append("Opcode instruction: " + opBinary + " " + rsBinary + " " + rtBinary + " " + rdBinary + " " + shampt + " " + funct + "\n");
        
        Integer values[] = {9, rd, rs, rt};
        
        return values;
    }

    public Integer[] div(String[] ins) {
        String op = ins[0];
        Integer rs = Integer.parseInt(ins[1].substring(1, ins[1].length()-1));
        Integer rt = Integer.parseInt(ins[2].substring(1, ins[2].length()));
        
        String opBinary = "000000";
        String rsBinary = Conversion.intToBinaryString(rs, 5);
        String rtBinary = Conversion.intToBinaryString(rt, 5);
        String rdBinary = "00000";
        String shampt = "00000";
        String funct = "011010";
        outputArea.append("Opcode instruction: " + opBinary + " " + rsBinary + " " + rtBinary + " " + rdBinary + " " + shampt + " " + funct + "\n");
        
        Integer values[] = {10, rs, rt};
        
        return values;
    }

    public Integer[] jr(String[] ins) {
        String op = ins[0];
        Integer rs = Integer.parseInt(ins[1].substring(1, ins[1].length()));
        
        String opBinary = "000000";
        String rsBinary = Conversion.intToBinaryString(rs, 5);
        String rtBinary = "00000";
        String rdBinary = "00000";
        String shampt = "00000";
        String funct = "001000";
        outputArea.append("Opcode instruction: " + opBinary + " " + rsBinary + " " + rtBinary + " " + rdBinary + " " + shampt + " " + funct + "\n");
        
        Integer values[] = {11, rs};
        
        return values;
    }
}
