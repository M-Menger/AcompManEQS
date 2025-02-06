package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileSelector {

    public static void fileSelector() {
        // Cria uma instância de JFrame (janela)
        JFrame frame = new JFrame("Seletor de Arquivos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Cria um botão para abrir o seletor de arquivos
        JButton button = new JButton("Selecionar Arquivo");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância de JFileChooser
                JFileChooser fileChooser = new JFileChooser();

                // Abre o diálogo para seleção de arquivos
                int returnValue = fileChooser.showOpenDialog(null);

                // Verifica se o usuário selecionou um arquivo
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    java.io.File selectedFile = fileChooser.getSelectedFile();

                    // Exibe o nome do arquivo selecionado
                    System.out.println("Arquivo selecionado: " + selectedFile.getName());

                    // Fecha a janela após a seleção do arquivo
                    frame.dispose();
                }
            }
        });

        // Adiciona o botão à janela
        frame.getContentPane().add(button);

        // Torna a janela visível
        frame.setVisible(true);
    }
}