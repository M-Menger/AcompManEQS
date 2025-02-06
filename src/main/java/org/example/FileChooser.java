package org.example;

import javax.swing.*;
import java.io.File;

public class FileChooser {

    // Método para abrir o seletor de arquivos e retornar o arquivo selecionado
    public static File selectFile() {
        // Cria uma instância de JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Abre o diálogo para seleção de arquivos
        int returnValue = fileChooser.showOpenDialog(null);

        // Verifica se o usuário selecionou um arquivo
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Retorna o arquivo selecionado
            return fileChooser.getSelectedFile();
        } else {
            // Retorna null se nenhum arquivo foi selecionado
            return null;
        }
    }
}