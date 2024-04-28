package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Supplier;
import com.betek.demoday.actionfactory.models.responses.CustomResponse;
import com.betek.demoday.actionfactory.services.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Supplier Controller", description = "Controlador para gestionar las operaciones de los proveedores")
@RestController
@RequestMapping("api/supplier")
public class SupplierController {
    SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @PostMapping
    @Operation(summary = "Crear un Proveedor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor creado."),
            @ApiResponse(responseCode = "400", description = "El email ya está registrado.", content = @Content),
    })
    public CustomResponse<Supplier> saveSupplier(@RequestBody Supplier supplier){
        try {
            return CustomResponse.success(supplierService.saveSupplier(supplier));
        } catch (ApiException e){
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Mostrar proveedores.")
    public List<Supplier> getAllSuppliers(){
            return supplierService.getAllsSuppliers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un proveedor por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado."),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado.", content = @Content),
    })
    public CustomResponse<Supplier> getSupplierById(@PathVariable Long id) {
        try {
            Supplier supplier = supplierService.getSupplierById(id);
            return CustomResponse.success(supplier);
        } catch (ApiException e) {
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proveedor por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor eliminado."),
            @ApiResponse(responseCode = "400", description = "Proveedor no encontrado.", content = @Content)
    })
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id){
        try {
            supplierService.deleteSupplierById(id);
            return ResponseEntity.noContent().build();
        }catch (ApiException e){
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado."),
            @ApiResponse(responseCode = "400", description = "Proveedor no encontrado.", content = @Content)
    })
   public CustomResponse<Supplier> putSupplierById(@PathVariable Long id, @RequestBody Supplier supplier) {
        try{
            return CustomResponse.success(supplierService.updateSupplierById(id, supplier));
        }catch(ApiException e){
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }


}
