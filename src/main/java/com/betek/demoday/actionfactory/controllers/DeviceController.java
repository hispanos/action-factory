package com.betek.demoday.actionfactory.controllers;

import com.betek.demoday.actionfactory.dto.DeviceResponseDto;
import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.responses.CustomResponse;
import com.betek.demoday.actionfactory.models.validations.InvalidDevice;
import com.betek.demoday.actionfactory.models.validations.ValidDevice;
import com.betek.demoday.actionfactory.services.devicesServices.InvalidDeviceService;
import com.betek.demoday.actionfactory.services.devicesServices.ValidDeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Device Controller", description = "Controlador para gestionar las operaciones de los dispositivos procesados.")
@RestController
@RequestMapping("api/device")
public class DeviceController {
    InvalidDeviceService invalidDeviceService;
    ValidDeviceService validDeviceService;

    @Autowired
    public DeviceController( ValidDeviceService validDeviceService, InvalidDeviceService invalidDeviceService){
        this.invalidDeviceService = invalidDeviceService;
        this.validDeviceService = validDeviceService;
    }

    @GetMapping
    @Operation(summary = "Obtener un dispositivos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispositivos encontrados."),
            @ApiResponse(responseCode = "404", description = "Dispositivos no encontrados.", content = @Content),
    })
    public CustomResponse <List<DeviceResponseDto>> getAllDevices(){
        List<DeviceResponseDto> deviceResponseDtos = new ArrayList<>();
        try{

            List<ValidDevice> validDevices = validDeviceService.getAllsValidDevices();
            deviceResponseDtos.addAll(setListDeviceResponseDtoValid(validDevices));

            List<InvalidDevice> invalidDevices = invalidDeviceService.getAllsInvalidDevices();
            deviceResponseDtos.addAll(setListDeviceResponseDtoInvalid(invalidDevices));

        }catch (ApiException e){
            return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
        return CustomResponse.success(deviceResponseDtos);
    }

    @GetMapping("imei/{imei}")
    @Operation(summary = "Obtener un dispositivo por su imei.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispositivo encontrado."),
            @ApiResponse(responseCode = "404", description = "Dispositivo no encontrado.", content = @Content),
    })
    public CustomResponse<DeviceResponseDto> getDeviceById(@PathVariable Long imei) {

        DeviceResponseDto deviceResponseDto = new DeviceResponseDto();

        try{
            ValidDevice validDevice = validDeviceService.getValidDeviceById(imei);
            deviceResponseDto = setDeviceResponseDtoValid(validDevice);

        }catch (ApiException e){
            try{
                InvalidDevice invalidDevice = invalidDeviceService.getInvalidDeviceById(imei);
                deviceResponseDto = setDeviceResponseDtoInvalid(invalidDevice);

            }catch (ApiException a){
                return CustomResponse.error(a.getStatusCode(), a.getMessage());
            }

        }
            return CustomResponse.success(deviceResponseDto);
    }

    @GetMapping("supplier/{supplier}")
    @Operation(summary = "Obtener un dispositivo por proveedor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispositivo encontrado."),
            @ApiResponse(responseCode = "404", description = "Dispositivo no encontrado.", content = @Content),
    })
    public CustomResponse<List<DeviceResponseDto>> getDeviceById(@PathVariable String supplier) {

        List<DeviceResponseDto> deviceResponseDto = new ArrayList<>();

        try{
            List<ValidDevice> validDevices = validDeviceService.getValidDeviceBySupplier(supplier);
            deviceResponseDto.addAll(setListDeviceResponseDtoValid(validDevices));

            List<InvalidDevice> invalidDevices = invalidDeviceService.getInvalidDeviceBySupplier(supplier);
            deviceResponseDto.addAll(setListDeviceResponseDtoInvalid(invalidDevices));

        }catch (ApiException e){
                return CustomResponse.error(e.getStatusCode(), e.getMessage());
        }
        return CustomResponse.success(deviceResponseDto);
    }

    public DeviceResponseDto setDeviceResponseDtoValid(ValidDevice validDevice){
        DeviceResponseDto deviceResponseDto = new DeviceResponseDto();

        deviceResponseDto.setValidationID(validDevice.getValidationID());
        deviceResponseDto.setImei(validDevice.getImei());
        deviceResponseDto.setValid(true);
        deviceResponseDto.setState(validDevice.getState());
        deviceResponseDto.setSupplier(validDevice.getSupplier());
        deviceResponseDto.setScore(validDevice.getScore());
        deviceResponseDto.setLoadingDate(validDevice.getLoadingDate());
        deviceResponseDto.setEmployee(validDevice.getEmployee());
        deviceResponseDto.setValidatorID(validDevice.getValidatorID());

        return deviceResponseDto;
    }

    public DeviceResponseDto setDeviceResponseDtoInvalid(InvalidDevice invalidDevice){

        DeviceResponseDto deviceResponseDto = new DeviceResponseDto();

        deviceResponseDto.setValidationID(invalidDevice.getValidationID());
        deviceResponseDto.setImei(invalidDevice.getImei());
        deviceResponseDto.setValid(false);
        deviceResponseDto.setState(invalidDevice.getState());
        deviceResponseDto.setSupplier(invalidDevice.getSupplier());
        deviceResponseDto.setScore(invalidDevice.getScore());
        deviceResponseDto.setLoadingDate(invalidDevice.getLoadingDate());
        deviceResponseDto.setEmployee(invalidDevice.getEmployee());
        deviceResponseDto.setValidatorID(invalidDevice.getValidatorID());

        return deviceResponseDto;
    }

    public List<DeviceResponseDto> setListDeviceResponseDtoInvalid(List<InvalidDevice> invalidDevices){
       List<DeviceResponseDto> listResponses = new ArrayList<>();

        invalidDevices.forEach(invalidDevice -> {
            DeviceResponseDto deviceResponseDto = setDeviceResponseDtoInvalid(invalidDevice);
            listResponses.add(deviceResponseDto);
        });
        return listResponses;
    }

    public List<DeviceResponseDto> setListDeviceResponseDtoValid(List<ValidDevice> validDevices){
        List<DeviceResponseDto> listResponses = new ArrayList<>();

        validDevices.forEach(validDevice -> {
            DeviceResponseDto deviceResponseDto = setDeviceResponseDtoValid(validDevice);
            listResponses.add(deviceResponseDto);
        });
        return listResponses;
    }
}
