package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InstructionView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public static String instructionFile;

    public void callInstructionFrame() {
        this.setVisible(true);
    }

    public static String getInstructionFile() {
        return instructionFile;
    }

    public static void setInstructionFile(String instructionFile) {
        InstructionView.instructionFile = instructionFile;
    }

    public InstructionView() {
        setTitle("Pipeline Simulator");
        setResizable(false);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 195);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblInstructionFile = new JLabel("Escolha um arquivo de instruções:");
        lblInstructionFile.setBounds(10, 21, 416, 22);
        contentPane.add(lblInstructionFile);

        JLabel lblInstructionFileSelected = new JLabel("");
        lblInstructionFileSelected.setVisible(false);
        lblInstructionFileSelected.setForeground(new Color(0, 128, 0));
        lblInstructionFileSelected.setBounds(152, 38, 274, 14);
        contentPane.add(lblInstructionFileSelected);

        JButton btnFileConfig = new JButton("Escolher arquivo");
        btnFileConfig.setBackground(Color.PINK);
        btnFileConfig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser jfc = new JFileChooser("./");
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int returnValue = jfc.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    if (jfc.getSelectedFile().isFile()) {
                        instructionFile = jfc.getSelectedFile().toString();
                    }
                    lblInstructionFileSelected.setText("Arquivo de instruções selecionado");
                    lblInstructionFileSelected.setVisible(true);
                }
            }
        });
        btnFileConfig.setBounds(10, 40, 135, 22);
        contentPane.add(btnFileConfig);

        JLabel lblMethod = new JLabel("Iniciar execução:");
        lblMethod.setBounds(10, 84, 416, 22);
        contentPane.add(lblMethod);

        JButton btnD = new JButton("Iniciar");
        btnD.setBackground(Color.PINK);
        btnD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose(); // Fecha a janela atual
                PipelineSimulatorUI ui = new PipelineSimulatorUI(); // Cria a interface de execução
                ui.setVisible(true); // Exibe a interface
            }
        });
        btnD.setBounds(10, 103, 132, 23);
        contentPane.add(btnD);
    }
}
