package com.betek.demoday.actionfactory.services.fileService;

import com.betek.demoday.actionfactory.dto.DeviceCsvDto;
import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.validations.InvalidDevice;
import com.betek.demoday.actionfactory.models.validations.ValidDevice;
import com.betek.demoday.actionfactory.repositories.InvalidDeviceRepository;
import com.betek.demoday.actionfactory.repositories.ValidDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ValidationService {

    private ReaderService readerService;
    private ValidTestService validTestService;
    private ValidDeviceRepository validDeviceRepository;
    private InvalidDeviceRepository invalidDeviceRepository;


    @Autowired
    public ValidationService(ReaderService readerService, ValidDeviceRepository validDeviceRepository, InvalidDeviceRepository invalidDeviceRepository, ValidTestService validTestService) {
        this.readerService = readerService;
        this.validDeviceRepository = validDeviceRepository;
        this.invalidDeviceRepository = invalidDeviceRepository;
        this.validTestService = validTestService;
    }

    public List<DeviceCsvDto> validationCsv(MultipartFile file) {

        List<DeviceCsvDto> resultado = readerService.processCSVFile(file);

        try {
            sortList(resultado);
            validateList(resultado);
            return resultado;

        } catch (ApiException e) {
           e.getMessage();
            return null;
        }
    }

    public void sortList(List<DeviceCsvDto> list) {
        Collections.sort(list, Comparator.comparing(DeviceCsvDto::getImei));
    }

    public void validateList(List<DeviceCsvDto> sortedList) {

        List<ValidDevice> dispositivosValidos = new ArrayList<>();
        List<InvalidDevice> dispositivosInvalidos = new ArrayList<>();


        for (DeviceCsvDto device : sortedList) {
            boolean isValid = validTestService.validations(device);

            if (isValid) {
               // ValidDeviceDto validDevice = mapToInvalidDevice(device);

                //dispositivosValidos.add(validDevice);
            } else {
                InvalidDevice invalidDevice = mapToInvalidDevice(device);

                dispositivosInvalidos.add(invalidDevice);
            }

            System.out.println("Validando dispositivo: " + device.getImei() + ", con puntaje: " + device.getPuntaje() + " - " + device.getState());
        }
        validDeviceRepository.saveAll(dispositivosValidos);
        invalidDeviceRepository.saveAll(dispositivosInvalidos);

        System.out.println("pausita pa");
    }

    private InvalidDevice mapToInvalidDevice(DeviceCsvDto device) {
        InvalidDevice invalidDevice = new InvalidDevice();

        invalidDevice.setImei(device.getImei());
        invalidDevice.setState(device.getState());
        invalidDevice.setSupplier(device.getProveedor());

        try {
            int score = Integer.parseInt(device.getPuntaje());
            invalidDevice.setScore(score);
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el puntaje a int: " + device.getPuntaje());
            invalidDevice.setScore(0);
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date validationDate = dateFormat.parse(device.getDate());
            invalidDevice.setLoadingDate(validationDate);
        } catch (ParseException e) {
            System.err.println("Error al convertir la fecha: " + device.getDate());
            invalidDevice.setLoadingDate(new Date());
        }

        //invalidDevice.setEmployee(mapEmployee(device.getProveedor()));

       // invalidDevice.setValidatorID(String.valueOf(Long.parseLong(device.getProveedor())));

        return invalidDevice;
    }

}


