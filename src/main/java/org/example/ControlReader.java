package org.example;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControlReader {

    public static void readerCsv(String controlCsv, String relMov) {

        String outPutFile = "relatorioatt.csv";
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            // Ler o primeiro arquivo CSV (controlCsv) com separador ";"
            CSVReader reader1 = new CSVReaderBuilder(
                    new InputStreamReader(new FileInputStream(controlCsv), "ISO-8859-1"))
                    .withCSVParser(parser)
                    .build();
            List<String[]> linhasArquivo1 = reader1.readAll();
            reader1.close();

            // Pular o cabeçalho do primeiro arquivo
            if (!linhasArquivo1.isEmpty()) {
                linhasArquivo1.remove(0); // Remove a primeira linha (cabeçalho)
            }

            // Ler o segundo arquivo CSV (relMov) com separador ";"
            CSVReader reader2 = new CSVReaderBuilder(
                    new InputStreamReader(new FileInputStream(relMov), "ISO-8859-1"))
                    .withCSVParser(parser)
                    .build();

            List<String[]> linhasArquivo2 = reader2.readAll();
            reader2.close();

            // Pular o cabeçalho do segundo arquivo
            if (!linhasArquivo2.isEmpty()) {
                linhasArquivo2.remove(0); // Remove a primeira linha (cabeçalho)
            }

            // Criar um conjunto para armazenar os IDs do primeiro arquivo
            Set<String> idsArquivo1 = new HashSet<>();
            for (String[] linha : linhasArquivo1) {
                // Verificar se a linha tem pelo menos 3 colunas
                if (linha.length > 2) {
                    String nAtend = linha[2].trim(); // Coluna 3 (índice [2])
                    idsArquivo1.add(nAtend);
                } else {
                    System.out.println("Aviso: Linha com menos de 3 colunas no controlCsv: " + String.join(";", linha));
                }
            }

            // Escrever as linhas únicas do segundo arquivo no arquivo de saída
            CSVWriter writer = (CSVWriter) new CSVWriterBuilder(
                    new OutputStreamWriter(new FileOutputStream(outPutFile), "ISO-8859-1"))
                    .withSeparator(';')
                    .build();

            // Escrever o cabeçalho do arquivo de saída
            String[] cabecalho = {"Atendimento", "Placa", "Motivo OS"};
            writer.writeNext(cabecalho);

            // Verificar cada linha do segundo arquivo (relMov)
            for (String[] linha : linhasArquivo2) {
                // Verificar se a linha tem pelo menos 1 coluna
                if (linha.length > 0) {
                    String atend = linha[0].trim(); // Coluna 1 (índice [0]) do relMov
                    if (!idsArquivo1.contains(atend)) {
                        // Extrair os dados relevantes: atendimento, placa, motivo
                        String placa = linha.length > 4 ? linha[4].trim() : ""; // Coluna 5 (índice [4])
                        String motivo = linha.length > 34 ? linha[34].trim() : ""; // Coluna 35 (índice [34])
                        String[] linhaSaida = {atend, placa, motivo};
                        writer.writeNext(linhaSaida);
                    }
                } else {
                    System.out.println("Aviso: Linha vazia no arquivo relMov.");
                }
            }

            writer.close();
            System.out.println("Arquivo de saída gerado com sucesso: " + outPutFile);

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}