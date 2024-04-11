package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.services.readerService.ReaderService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("/file")
public class ReaderController {
    private ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService){
        this.readerService = readerService;
    }


    @PostMapping("/upload-csv")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Por favor selecciona un archivo para subir.");
            return "redirect:/";
        }

        try {
            readerService.processCSVFile(file);
            redirectAttributes.addFlashAttribute("message", "¡Archivo subido y procesado correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "¡Error al procesar el archivo CSV!");
        }

        return "redirect:/";
    }
}


