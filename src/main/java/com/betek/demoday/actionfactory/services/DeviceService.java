package com.betek.demoday.actionfactory.services;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Device;
import com.betek.demoday.actionfactory.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    public Device saveDevice(Device device){
        if (device == null){
            throw new ApiException(HttpStatus.BAD_REQUEST, "El proveedor no puede ser nulo.");
        }
        return deviceRepository.save(device);
    }

    public List<Device> getAllsDevices(){
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id) {
        Optional<Device> optionalDevice = deviceRepository.findById(id);

        if (optionalDevice.isPresent()) {
            return optionalDevice.get();
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
    }

    public void deleteDeviceById(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
        deviceRepository.deleteById(id);
    }

    public Device updateDeviceById(Long id, Device device) {
        if (!deviceRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
        device.setImei(id);
        return deviceRepository.save(device);
    }

}
