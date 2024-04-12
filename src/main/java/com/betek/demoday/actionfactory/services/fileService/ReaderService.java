package com.betek.demoday.actionfactory.services.fileService;

import com.betek.demoday.actionfactory.dto.DeviceCsvDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderService {

    public List<DeviceCsvDto> processCSVFile(MultipartFile file) throws Exception {
        try {

            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

                List<DeviceCsvDto> divList = new ArrayList<DeviceCsvDto>();

                for (CSVRecord csvRecord : parser) {
                    DeviceCsvDto div = new DeviceCsvDto();
                    div.setImei(csvRecord.get("imei"));
                    div.setState(csvRecord.get("state"));
                    div.setProveedor(csvRecord.get("proveedor"));
                    div.setPuntaje(csvRecord.get("puntaje"));
                    div.setStatus(csvRecord.get("status"));
                    div.setDate(csvRecord.get("date"));
                    divList.add(div);
                }

                return divList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al procesar el archivo CSV: " + e.getMessage());
        }
    }
}
