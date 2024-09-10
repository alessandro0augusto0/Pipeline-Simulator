package functions.instructions;

import javax.swing.JOptionPane;

public class TypeD {

    public Integer[] get_tc(String[] ins) {
        String op = ins[0];
        Integer mem = null;

        try {
            mem = Integer.parseInt(ins[1]); // Verifica se o segundo valor é válido
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: endereço de memória inválido na instrução get_tc.");
            return new Integer[]{0, 0, 0}; // Retorna uma instrução "noop" para evitar falhas
        }

        // Solicita a entrada do usuário para armazenar em M[mem]
        String input = JOptionPane.showInputDialog(null, "Digite um valor inteiro para armazenar em M[" + mem + "]:");
        
        Integer value = null;
        try {
            value = Integer.parseInt(input); // Verifica se o valor inserido é um número inteiro
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: valor inválido inserido para M[" + mem + "].");
            return new Integer[]{0, 0, 0}; // Retorna uma instrução "noop"
        }

        Integer values[] = {13, mem, value};

        return values;
    }

    public Integer[] noop(String[] ins) {
        Integer values[] = {0, 0};

        return values;
    }
}
