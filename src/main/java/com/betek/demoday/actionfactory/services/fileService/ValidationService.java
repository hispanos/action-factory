package com.betek.demoday.actionfactory.services.fileService;

import com.betek.demoday.actionfactory.dto.DeviceCsvDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ValidationService {

    private final ReaderService readerService;

    @Autowired
    public ValidationService(ReaderService readerService){
        this.readerService = readerService;
    }

    public void validationCsv(MultipartFile file) throws Exception {
        //resultado tiene la lista con los registros
        List<DeviceCsvDto> resultado = readerService.processCSVFile(file);
    }

}
