package com.betek.demoday.actionfactory.services.readerService;

import com.betek.demoday.actionfactory.models.responses.ResponseReader;
import com.betek.demoday.actionfactory.utils.readerUtils.Connection;

import java.io.IOException;

public abstract class Reader {
    protected Connection connection = new Connection();
    protected Integer validLines = 0;
    protected Integer invalidLines = 0;
    protected ResponseReader responseReader;

    public abstract ResponseReader readerFile(String urlFile) throws IOException;

    public abstract String getType();
}
