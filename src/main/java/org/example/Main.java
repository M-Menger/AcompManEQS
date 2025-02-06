package org.example;

import java.io.File;

import static org.example.ControlReader.readerCsv;
import static org.example.FileChooser.selectFile;

public class Main {
    public static void main(String[] args) {

        String arquivo1 = "C:\\Users\\Balcão\\Desktop\\Developing\\3.AreaDeTestes\\Java\\AcompManEQS\\src\\main\\resources\\Controle de Manutenção MOVIDA(MANUTENÇÃO MOVIDA).csv";
        String arquivo2 = "C:\\Users\\Balcão\\Desktop\\Developing\\3.AreaDeTestes\\Java\\AcompManEQS\\src\\main\\resources\\Manutenção - Relatorio de Atendimento 2.csv";

        System.out.println("Selecione o arquivo de controle: ");
        File csvControlpath = selectFile();
        String csvControl = csvControlpath.getPath();

        System.out.println("Selecione o relatório Movida: ");
        File relMovidaPath = selectFile();
        String relMovida = relMovidaPath.getPath();

        readerCsv(arquivo1, arquivo2);
    }
}