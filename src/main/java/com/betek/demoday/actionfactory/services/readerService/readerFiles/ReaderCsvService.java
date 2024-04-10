package com.betek.demoday.actionfactory.services.readerService.readerFiles;

import com.opencsv.bean.CsvToBeanBuilder;
import com.betek.demoday.actionfactory.models.CsvModel;
import com.betek.demoday.actionfactory.models.responses.ResponseReader;
import com.betek.demoday.actionfactory.exceptions.ReaderException;
import com.betek.demoday.actionfactory.services.readerService.Reader;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class ReaderCsvService extends Reader {

    @Override
    public ResponseReader readerFile(String urlFile) {
        List<CsvModel> elements = null;
        try {
            elements = new CsvToBeanBuilder(new FileReader(urlFile))
                    .withType(CsvModel.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            throw new ReaderException(e.getMessage());
        }

        elements.forEach((element) -> {
            boolean response = this.connection.webClient().post().uri("validateData/csv")
                    .bodyValue(element).retrieve()
                    .bodyToMono(boolean.class).block();
            if (!response) {
                this.invalidLines++;
            }else {
                this.validLines++;
            }
        });

        ResponseReader responseReader = new ResponseReader(this.validLines, this.invalidLines);
        return responseReader;
    }

    @Override
    public String getType() {
        return "csv";
    }
}
