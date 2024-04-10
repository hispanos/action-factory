package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.models.ReaderRequest;
import com.betek.demoday.actionfactory.models.responses.ResponseReader;
import com.betek.demoday.actionfactory.services.readerService.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/file")
public class ReaderController {
    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService){
        this.readerService = readerService;
    }

    @PostMapping
    public ResponseEntity<ResponseReader> loadFile(@RequestBody ReaderRequest request) {
        return ResponseEntity.ok(readerService.processFile(request.getUrlFile()));
    }

    @PostMapping("/upload")
    public String load(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {


        return "Archivo subido.";
    }

}
