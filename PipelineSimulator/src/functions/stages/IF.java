package functions.stages;

import functions.generic.PC;
import javax.swing.JTextArea;

public class IF {
    private JTextArea outputArea;

    public IF(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    public String[] readingInstruction(String line) {
        String[] op = line.split("\\s");
        outputArea.append("\nPC: " + PC.getPc() + "\nClock: " + PC.getClock() + "\n");
        PC.setClock(PC.getClock() + 1); // Incrementa apenas o clock
        return op;
    }
}
