package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.models.Device;
import com.betek.demoday.actionfactory.services.DeviceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Device Controller", description = "Controlador para gestionar las operaciones de los dispositivos")
@RestController
@RequestMapping("api/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/{imei}")
    public Device getDeviceByImei(@PathVariable Long imei) {
        return deviceService.getDeviceByImei(imei);
    }

    @GetMapping("/search")
    public ResponseEntity<Device> getDeviceByImeiAndSupplier(
            @RequestParam("imei") Long imei,
            @RequestParam("supplier") String name) {

        Device device = deviceService.getDeviceByImeiAndSupplier(imei, name);

        if (device != null) {
            return ResponseEntity.ok(device);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
