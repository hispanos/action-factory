package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.services.fileService.ReaderService;
import com.betek.demoday.actionfactory.services.fileService.ValidationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Tag(name = "Controlador de archivos", description = "Controlador para gestionar las operaciones de los archivos.")
@RestController
@RequestMapping("/file")
public class ReaderController {
    private ReaderService readerService;
    private ValidationService validationService;

    @Autowired
    public ReaderController(ReaderService readerService, ValidationService validationService){
        this.readerService = readerService;
        this.validationService = validationService;
    }


    @PostMapping("/upload-csv")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Por favor selecciona un archivo para subir.");
            return "Por favor sube un archivo.";
        }

        try {
           // readerService.processCSVFile(file);
            validationService.validationCsv(file);
            redirectAttributes.addFlashAttribute("message", "¡Archivo subido y procesado correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "¡Error al procesar el archivo CSV!");
        }
        //redirect:/
        return "Estamos procesando tú archivo...";
    }
}


