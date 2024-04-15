package com.betek.demoday.actionfactory.services;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Supplier;
import com.betek.demoday.actionfactory.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public Supplier saveSupplier(Supplier supplier){
        if (supplier == null){
            throw new ApiException(HttpStatus.BAD_REQUEST, "El proveedor no puede ser nulo.");
        }
        if (supplierRepository.existsByEmail(supplier.getEmail())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "El email ya está registrado.");
        }
        try{
            return supplierRepository.save(supplier);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Supplier> getAllsSuppliers(){
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            return optionalSupplier.get();
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
    }

    public void deleteSupplierById(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
        supplierRepository.deleteById(id);
    }

    public Supplier updateSupplierById(Long id, Supplier supplier) {
        if (!supplierRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
        supplier.setId(id);
        return supplierRepository.save(supplier);
    }
}
