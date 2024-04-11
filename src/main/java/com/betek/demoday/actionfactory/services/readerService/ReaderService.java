package com.betek.demoday.actionfactory.services.readerService;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.io.Reader;

@Service
public class ReaderService {

    public void processCSVFile(MultipartFile file) throws Exception {
        try {

            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            CSVReader csvReader = new CSVReader(reader);

            List<String[]> lines = csvReader.readAll();

            for (String[] line : lines) {
                // Haz algo con cada línea, por ejemplo, imprímela
                for (String cell : line) {
                    System.out.print(cell + "\t");
                }
                System.out.println(); // Nueva línea después de cada fila
            }

            // Cierra el CSVReader
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al procesar el archivo CSV: " + e.getMessage());
        }
    }
}
