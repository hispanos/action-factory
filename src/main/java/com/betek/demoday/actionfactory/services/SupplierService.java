package com.betek.demoday.actionfactory.services;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Supplier;
import com.betek.demoday.actionfactory.repositories.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
            log.info("Proveedor guardado: " + supplier);
            return supplierRepository.save(supplier);
        }catch (ApiException e){
            log.error("Error al guardar el proveedor: " + e.getMessage());
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
            log.error("Proveedor no encontrado.");
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
    }

    public void deleteSupplierById(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
        log.info("Proveedor eliminado con id: " + id);
        supplierRepository.deleteById(id);
    }

    public Supplier updateSupplierById(Long id, Supplier supplier) {
        if (!supplierRepository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
        supplier.setId(id);
        log.info("Proveedor actualizado: " + supplier);
        return supplierRepository.save(supplier);
    }
}
