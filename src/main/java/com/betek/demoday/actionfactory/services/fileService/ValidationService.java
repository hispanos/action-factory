package com.betek.demoday.actionfactory.services.fileService;

import com.betek.demoday.actionfactory.dto.DeviceCsvDto;
import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Supplier;
import com.betek.demoday.actionfactory.models.validations.InvalidDevice;
import com.betek.demoday.actionfactory.models.validations.ValidDevice;
import com.betek.demoday.actionfactory.repositories.InvalidDeviceRepository;
import com.betek.demoday.actionfactory.repositories.SupplierRepository;
import com.betek.demoday.actionfactory.repositories.ValidDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ValidationService {

    private ReaderService readerService;
    private ValidTestService validTestService;
    private SupplierRepository supplierRepository;
    private ValidDeviceRepository validDeviceRepository;
    private InvalidDeviceRepository invalidDeviceRepository;

    private Supplier supplier;

    @Autowired
    public ValidationService(ReaderService readerService, ValidDeviceRepository validDeviceRepository, InvalidDeviceRepository invalidDeviceRepository, SupplierRepository supplierRepository, ValidTestService validTestService) {
        this.readerService = readerService;
        this.validDeviceRepository = validDeviceRepository;
        this.invalidDeviceRepository = invalidDeviceRepository;
        this.supplierRepository = supplierRepository;
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
                ValidDevice validDevice = new ValidDevice();
                dispositivosValidos.add(validDevice);
            } else {
                InvalidDevice invalidDevice = new InvalidDevice();
                dispositivosInvalidos.add(invalidDevice);
            }

            System.out.println("Validando dispositivo: " + device.getImei() + ", con puntaje: " + device.getPuntaje() + " - " + device.getState());
        }
        System.out.println("pausita pa");
    }
}


