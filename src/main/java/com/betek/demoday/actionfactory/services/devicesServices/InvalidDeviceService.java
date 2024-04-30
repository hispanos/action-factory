package com.betek.demoday.actionfactory.services.devicesServices;

import com.betek.demoday.actionfactory.dto.DeviceResponseDto;
import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.validations.InvalidDevice;
import com.betek.demoday.actionfactory.repositories.InvalidDeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvalidDeviceService {
    private InvalidDeviceRepository invalidDeviceRepository;

    @Autowired
    public InvalidDeviceService(InvalidDeviceRepository invalidDeviceRepository){
        this.invalidDeviceRepository = invalidDeviceRepository;
    }

    public List<InvalidDevice> getAllsInvalidDevices(){
        try {
            return invalidDeviceRepository.findAll();
        }catch (ApiException e){
            System.out.println(e.getMessage() + e.getStatusCode());
            return null;
        }

    }

    public InvalidDevice getInvalidDeviceById(Long imei) {

        Optional<InvalidDevice> optionalInvalidDevice = invalidDeviceRepository.findByImei(imei);

        if (optionalInvalidDevice.isPresent()) {
            return optionalInvalidDevice.get();
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Dispositivo no encontrado.");
        }
    }

    public void deleteInvalidDeviceById(Long imei) {

        if (!invalidDeviceRepository.existsById(imei)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Dispositivo no encontrado.");
        }
        invalidDeviceRepository.deleteById(imei);
    }

    public InvalidDevice updateInvalidDeviceById(Long imei, InvalidDevice invalidDevice) {

        if (!invalidDeviceRepository.existsById(imei)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Dispositivo no encontrado.");
        }
        invalidDevice.setImei(imei);
        return invalidDeviceRepository.save(invalidDevice);
    }

    public List<InvalidDevice> getInvalidDeviceBySupplier(String supplier) {

        Optional<List<InvalidDevice>> optionalInvalidDevice = invalidDeviceRepository.findBySupplier(supplier);

        if (optionalInvalidDevice.isPresent()) {
            return optionalInvalidDevice.get();
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Dispositivo no encontrado.");
        }
    }

    public DeviceResponseDto updateDeviceState(Long imei, String state) {
            InvalidDevice device = invalidDeviceRepository.findByImei(imei)
                    .orElseThrow(() -> new ApiException("Dispositivo no encontrado con imei: " + imei));
            device.setState(state);
            InvalidDevice updatedDevice = invalidDeviceRepository.save(device);
            return mapToDeviceResponseDto(updatedDevice);
    }

    private DeviceResponseDto mapToDeviceResponseDto(InvalidDevice device) {
        DeviceResponseDto deviceResponseDto = new DeviceResponseDto();

        deviceResponseDto.setValidationID(device.getValidationID());
        deviceResponseDto.setImei(device.getImei());
        deviceResponseDto.setState(device.getState());
        deviceResponseDto.setSupplier(device.getSupplier());
        deviceResponseDto.setScore(device.getScore());
        deviceResponseDto.setLoadingDate(device.getLoadingDate());
        deviceResponseDto.setEmployee(device.getEmployee());
        deviceResponseDto.setValidatorID(device.getValidatorID());
        return deviceResponseDto;
    }


}
