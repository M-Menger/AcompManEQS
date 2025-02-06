package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControlReader {

    public static void readerCsv(String controlCsv, String relMov){

        String outPutFile = "relatorioatt.csv";
        try {
            // Ler o primeiro arquivo CSV
            CSVReader reader1 = new CSVReader(new FileReader(controlCsv));
            List<String[]> linhasArquivo1 = reader1.readAll();
            reader1.close();

            // Ler o segundo arquivo CSV
            CSVReader reader2 = new CSVReader(new FileReader(relMov));
            List<String[]> linhasArquivo2 = reader2.readAll();
            reader2.close();

            // Criar um conjunto para armazenar os IDs do primeiro arquivo
            Set<String> idsArquivo1 = new HashSet<>();
            for (String[] linha : linhasArquivo1) {
                // Assumindo que a coluna "id" é a primeira coluna
                String nAtend = linha[2].trim();
                idsArquivo1.add(nAtend);
            }

            // Escrever as linhas únicas do segundo arquivo no arquivo de saída
            CSVWriter writer = new CSVWriter(new FileWriter(outPutFile));

            // Escrever o cabeçalho do arquivo de saída
            String[] cabecalho = {"Atendimento", "Placa", "Motivo OS"};
            writer.writeNext(cabecalho);

            // Verificar cada linha do segundo arquivo
            for (String[] linha : linhasArquivo2) {
                String atend = linha[0].trim(); // Coluna "atendimento" do segundo arquivo
                System.out.println(atend);
                if (!idsArquivo1.contains(atend)) {
                    // Extrair os dados relevantes: id, nome (cliente), idade
                    String placa = linha[4].trim(); // Coluna "cliente" do segundo arquivo
                    String motivo = linha[34].trim(); // Coluna "motivo" do segundo arquivo
                    String[] linhaSaida = {atend, placa, motivo};
                    writer.writeNext(linhaSaida);
                }
            }

            writer.close();
            System.out.println("Arquivo de saída gerado com sucesso: " + outPutFile);

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
