package com.betek.demoday.actionfactory.services.fileService;

import com.betek.demoday.actionfactory.dto.DeviceCsvDto;
import com.betek.demoday.actionfactory.models.Supplier;
import com.betek.demoday.actionfactory.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidTestService {

    private SupplierRepository supplierRepository;
    private Supplier supplier;

    @Autowired
    public ValidTestService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public boolean validations(DeviceCsvDto device){
        boolean isValid = true;

        isValid &= existSupplier(device);
        isValid &= stateIsValid(device);
        isValid &= imeiIsPalindromo(device);
        isValid &= validScore(device);

        return isValid;
    }

    public boolean existSupplier(DeviceCsvDto device){
        return supplierRepository.existsByEmail(supplier.getName());
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
