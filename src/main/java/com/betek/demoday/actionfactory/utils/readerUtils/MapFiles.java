package com.betek.demoday.actionfactory.utils.readerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.betek.demoday.actionfactory.services.readerService.Reader;

import java.util.List;

@Component
public class MapFiles {

    static List<Reader> reader;

    @Autowired
    public MapFiles(List<Reader> reader) {
        this.reader = reader;
    }

    public static Reader getReaderService(String fileType) {
        return reader.stream().filter(readerService -> readerService.getType().equals(fileType)).findFirst().orElse(null);
    }

}
