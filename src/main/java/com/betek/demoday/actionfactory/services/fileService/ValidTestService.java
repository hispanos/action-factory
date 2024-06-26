package com.betek.demoday.actionfactory.services.fileService;

import com.betek.demoday.actionfactory.dto.DeviceCsvDto;
import com.betek.demoday.actionfactory.models.Supplier;
import com.betek.demoday.actionfactory.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidTestService {

    private SupplierRepository supplierRepository;

    @Autowired
    public ValidTestService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public boolean validations(DeviceCsvDto device){
        if (imeiIsPalindromo(device)){
            return false;
        }
        return existSupplier(device) && stateIsValid(device) && validScore(device);
    }

    public boolean existSupplier(DeviceCsvDto device){
        return supplierRepository.existsByName(device.getProveedor());
    }

    public boolean stateIsValid(DeviceCsvDto device){
        return "LISTO_PARA_USAR".equals(device.getState().trim());
    }

    public boolean imeiIsPalindromo(DeviceCsvDto device){
        String imei = device.getImei();
        return imei.equals(new StringBuilder(imei).reverse().toString());
    }

    public boolean validScore(DeviceCsvDto device){
        return Integer.parseInt(device.getPuntaje()) > 60;
    }

}
