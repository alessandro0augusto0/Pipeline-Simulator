package functions.generic;

import javax.swing.JTextArea;
import view.PipelineSimulatorUI;

public class ProgramExecution {

    public static void main(String args[]) {
        JTextArea dummyOutputArea = new JTextArea(); // Cria uma JTextArea dummy para testes
        PipelineSimulatorUI dummyUI = new PipelineSimulatorUI(); // Cria uma instância da interface para passar ao Execution
        Execution exe = new Execution(dummyOutputArea, dummyUI);
        exe.execution();
    }
}
