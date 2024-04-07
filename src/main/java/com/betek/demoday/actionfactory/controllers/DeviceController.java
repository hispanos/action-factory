package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Device;
import com.betek.demoday.actionfactory.models.responses.CustomResponse;
import com.betek.demoday.actionfactory.services.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/device")
public class DeviceController {
    DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @PostMapping
    @Operation(summary = "Crear un empleado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor creado."),
            @ApiResponse(responseCode = "400", description = "El email ya está registrado.", content = @Content),
    })
    public CustomResponse<Device> saveDevice(@RequestBody Device device){
        try {
            return CustomResponse.success(deviceService.saveDevice(device));
        } catch (ApiException e){
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Mostrar proveedores.")
    public List<Device> getAllDevices(){
        return deviceService.getAllsDevices();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un proveedor por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado."),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado.", content = @Content),
    })
    public CustomResponse<Device> getDeviceById(@PathVariable Long id) {
        try {
            Device device = deviceService.getDeviceById(id);
            return CustomResponse.success(device);
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
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id){
        try {
            deviceService.deleteDeviceById(id);
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
    public CustomResponse<Device> putDeviceById(@PathVariable Long id, @RequestBody Device device) {
        try{
            return CustomResponse.success(deviceService.updateDeviceById(id, device));
        }catch(ApiException e){
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
    }

}
