package com.betek.demoday.actionfactory.services.devicesServices;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.validations.InvalidDevice;
import com.betek.demoday.actionfactory.models.validations.ValidDevice;
import com.betek.demoday.actionfactory.repositories.ValidDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValidDeviceService {

    private ValidDeviceRepository validDeviceRepository;

    @Autowired
    public ValidDeviceService(ValidDeviceRepository validDeviceRepository){
        this.validDeviceRepository = validDeviceRepository;
    }

    public List<ValidDevice> getAllsValidDevices(){
        try {
            return validDeviceRepository.findAll();
        }catch (ApiException e){
            System.out.println(e.getMessage() + e.getStatusCode());
            return null;
        }

    }

    public ValidDevice getValidDeviceById(Long imei) {
        Optional<ValidDevice> optionalValidDevice = validDeviceRepository.findByImei(imei);

        if (optionalValidDevice.isPresent()) {
            return optionalValidDevice.get();
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Dispositivo no encontrado.");
        }
    }

    public void deleteValidDeviceById(Long imei) {
        if (!validDeviceRepository.existsById(imei)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Dispositivo no encontrado.");
        }
        validDeviceRepository.deleteById(imei);
    }

    public ValidDevice updateValidDeviceById(Long imei, ValidDevice validDevice) {
        if (!validDeviceRepository.existsById(imei)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Dispositivo no encontrado.");
        }
        validDevice.setImei(imei);
        return validDeviceRepository.save(validDevice);
    }
    public List<ValidDevice> getValidDeviceBySupplier(String supplier) {

        Optional<List<ValidDevice>> optionalValidDevice = validDeviceRepository.findBySupplier(supplier);

        if (optionalValidDevice.isPresent()) {
            return optionalValidDevice.get();
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Dispositivo no encontrado.");
        }
    }
}
