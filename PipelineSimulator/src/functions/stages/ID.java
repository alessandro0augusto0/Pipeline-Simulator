package functions.stages;

import functions.instructions.*;
import javax.swing.JTextArea;

public class ID {
    private JTextArea outputArea;

    public ID(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    public Integer[] decodeInstruction(String[] op) {
        Integer values[];
        
        switch (op[0]) {
            case "add":
                Register add = new Register(outputArea);
                values = add.add(op);
                break;
            case "mult":
                Register mult = new Register(outputArea);
                values = mult.mult(op);
                break;
            case "bne":
                Immediate bne = new Immediate(outputArea);
                values = bne.bne(op);
                break;
            case "sub":
                Register sub = new Register(outputArea);
                values = sub.sub(op);
                break;
            case "div":
                Register div = new Register(outputArea);
                values = div.div(op);
                break;
            case "beq":
                Immediate beq = new Immediate(outputArea);
                values = beq.beq(op);
                break;
            case "lw":
                Immediate lw = new Immediate(outputArea);
                values = lw.lw(op);
                break;
            case "bltz":
                Immediate bltz = new Immediate(outputArea);
                values = bltz.bltz(op);
                break;
            case "j":
                Jump j = new Jump();
                values = j.j(op);
                break;
            case "sw":
                Immediate sw = new Immediate(outputArea);
                values = sw.sw(op);
                break;
            case "bgtz":
                Immediate bgtz = new Immediate(outputArea);
                values = bgtz.bgtz(op);
                break;
            case "jr":
                Register jr = new Register(outputArea);
                values = jr.jr(op);
                break;
            case "get_tc":
                TypeD get = new TypeD();
                values = get.get_tc(op);
                break;
            case "noop":
                TypeD no = new TypeD();
                values = no.noop(op);
                break;
            default:
                outputArea.append("Instrução não encontrada\n");
                values = null;
                break;
        }
        return values;
    }
}
