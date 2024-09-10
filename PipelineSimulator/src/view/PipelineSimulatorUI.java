package view;

import functions.generic.Execution;
import functions.generic.FileManager;
import functions.generic.PC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PipelineSimulatorUI extends JFrame {
    private JPanel controlPanel;
    private JTextArea outputArea;
    private Execution execution;
    private JLabel ifStage, idStage, exStage, memStage, wbStage; // Estágios do Pipeline
    private JButton pauseButton; // Botão de Pausar/Despausar

    public PipelineSimulatorUI() {
        setTitle("Simulador de Pipeline");
        setSize(900, 700); // Tamanho maior para acomodar a interface
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar na tela
        setLayout(new BorderLayout());

        // Painel de Controle (Área Superior)
        controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        controlPanel.setBackground(new Color(58, 64, 78)); // Cor escura moderna para o painel
        add(controlPanel, BorderLayout.NORTH);

        JButton loadButton = new JButton("Carregar Instruções");
        JButton startButton = new JButton("Iniciar");
        pauseButton = new JButton("Pausar"); // Inicialmente definido como "Pausar"
        JButton resetButton = new JButton("Resetar");

        // Estilo moderno dos botões
        configureButton(loadButton);
        configureButton(startButton);
        configureButton(pauseButton);
        configureButton(resetButton);

        controlPanel.add(loadButton);
        controlPanel.add(startButton);
        controlPanel.add(pauseButton);
        controlPanel.add(resetButton);

        // Área de Logs e Saída (Centro)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(30, 30, 30)); // Cor de fundo escura para visual técnico
        outputArea.setForeground(Color.GREEN); // Texto verde
        outputArea.setFont(new Font("Monospaced", Font.BOLD, 14)); // Estilo da fonte
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Painel de Execução (Área Inferior para estágios do Pipeline)
        JPanel pipelinePanel = new JPanel(new GridLayout(1, 5, 10, 10));
        pipelinePanel.setBackground(new Color(240, 240, 240)); // Fundo claro para o pipeline
        pipelinePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Caixas representando estágios do Pipeline
        ifStage = createStageLabel("IF");
        idStage = createStageLabel("ID");
        exStage = createStageLabel("EX");
        memStage = createStageLabel("MEM");
        wbStage = createStageLabel("WB");

        pipelinePanel.add(ifStage);
        pipelinePanel.add(idStage);
        pipelinePanel.add(exStage);
        pipelinePanel.add(memStage);
        pipelinePanel.add(wbStage);

        add(pipelinePanel, BorderLayout.SOUTH);

        // Configuração dos botões
        execution = new Execution(outputArea, this); // Passa a JTextArea e o contexto da interface para o Execution

        // Ações dos botões
        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser("./");
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String instructionFilePath = fileChooser.getSelectedFile().getPath();
                ArrayList<String> instructions = FileManager.stringReader(instructionFilePath);
                execution.setInstructions(instructions);
                outputArea.append("Arquivo de instruções carregado.\n");
            }
        });

        startButton.addActionListener(e -> {
            if (execution != null) {
                outputArea.append("Iniciando execução...\n");
                new Thread(() -> execution.execution()).start();  // Inicia a execução em uma nova thread
            }
        });

        // Ação do botão "Pausar/Despausar"
        pauseButton.addActionListener(e -> {
            execution.togglePause(); // Chama o método para pausar/retomar
            if (pauseButton.getText().equals("Pausar")) {
                pauseButton.setText("Despausar"); // Altera o texto para "Despausar"
            } else {
                pauseButton.setText("Pausar"); // Altera o texto de volta para "Pausar"
            }
        });

        resetButton.addActionListener(e -> {
            PC.setPc(0);  // Reseta o Program Counter (PC)
            PC.setClock(0);  // Reseta o clock
            outputArea.setText(""); // Limpa a saída da interface
            outputArea.append("Execução resetada.\n");
            pauseButton.setText("Pausar"); // Garante que o botão seja redefinido para "Pausar"
        });
    }

    // Método para atualizar as caixinhas dos estágios
    public void updatePipelineStages(String[] stages) {
        // Atualiza o texto nas caixinhas de acordo com as instruções em cada estágio
        ifStage.setText("IF: " + (stages[0] != null ? stages[0] : ""));
        idStage.setText("ID: " + (stages[1] != null ? stages[1] : ""));
        exStage.setText("EX: " + (stages[2] != null ? stages[2] : ""));
        memStage.setText("MEM: " + (stages[3] != null ? stages[3] : ""));
        wbStage.setText("WB: " + (stages[4] != null ? stages[4] : ""));

        // Alterar as cores das caixas para indicar atividade
        ifStage.setBackground(stages[0] != null ? new Color(0, 128, 0) : new Color(100, 150, 200));
        idStage.setBackground(stages[1] != null ? new Color(0, 128, 0) : new Color(100, 150, 200));
        exStage.setBackground(stages[2] != null ? new Color(0, 128, 0) : new Color(100, 150, 200));
        memStage.setBackground(stages[3] != null ? new Color(0, 128, 0) : new Color(100, 150, 200));
        wbStage.setBackground(stages[4] != null ? new Color(0, 128, 0) : new Color(100, 150, 200));
    }

    private void configureButton(JButton button) {
        button.setBackground(new Color(68, 163, 237)); // Cor do botão
        button.setForeground(Color.WHITE); // Cor do texto
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo do texto
        button.setFocusPainted(false); // Remove o foco visual no botão
    }

    private JLabel createStageLabel(String stage) {
        JLabel label = new JLabel(stage, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(100, 150, 200)); // Cor para os estágios
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(100, 60));
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PipelineSimulatorUI().setVisible(true));
    }
}
