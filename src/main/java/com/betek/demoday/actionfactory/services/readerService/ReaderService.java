package com.betek.demoday.actionfactory.services.readerService;

import com.betek.demoday.actionfactory.exceptions.FileException;
import com.betek.demoday.actionfactory.models.responses.ResponseReader;
import com.betek.demoday.actionfactory.services.readerService.Reader;
import com.betek.demoday.actionfactory.utils.readerUtils.MapFiles;
import org.springframework.stereotype.Service;

@Service
public class ReaderService {
    private Reader readerServices;

    public ResponseReader processFile(String urlFile) {
        String fileType = urlFile.substring(urlFile.lastIndexOf(".") + 1);
        readerServices = MapFiles.getReaderService(fileType);
        if (readerServices == null) {
            throw new FileException("File type not supported");
        }
        try {
            return readerServices.readerFile(urlFile);
        } catch (Exception e) {
            throw new FileException("Ocurred a problem reading the file" + e.getMessage());
        }
    }
}
