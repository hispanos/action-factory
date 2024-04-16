package com.betek.demoday.actionfactory.services.fileService;

import com.betek.demoday.actionfactory.dto.DeviceCsvDto;
import com.betek.demoday.actionfactory.models.Device;
import com.betek.demoday.actionfactory.models.validations.InvalidDevice;
import com.betek.demoday.actionfactory.models.validations.ValidDevice;
import com.betek.demoday.actionfactory.repositories.InvalidDeviceRepository;
import com.betek.demoday.actionfactory.repositories.ValidDeviceRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ValidationService {

    private final ReaderService readerService;
    private ValidDeviceRepository validDeviceRepository;
    private InvalidDeviceRepository invalidDeviceRepository;

    private Set<String> proveedoresExistentes = new HashSet<>();

    @Autowired
    public ValidationService(ReaderService readerService, ValidDeviceRepository validDeviceRepository, InvalidDeviceRepository invalidDeviceRepository){
        this.readerService = readerService;
        this.validDeviceRepository = validDeviceRepository;
        this.invalidDeviceRepository = invalidDeviceRepository;
    }

    public List<DeviceCsvDto> validationCsv(MultipartFile file) throws Exception {
        List<DeviceCsvDto> resultado = readerService.processCSVFile(file);

        sortList(resultado);
        validateList(resultado);

        return resultado;
    }


    public void sortList(List<DeviceCsvDto> list) {
        // Ordena la lista por IMEI
        Collections.sort(list, Comparator.comparing(DeviceCsvDto::getImei));
    }

    public void validateList(List<DeviceCsvDto> sortedList) {

        List<ValidDevice> dispositivosValidos = new ArrayList<>();
        List<InvalidDevice> dispositivosInvalidos = new ArrayList<>();

        for (DeviceCsvDto device : sortedList) {

            String imei = device.getImei();

            if (imei.equals(new StringBuilder(imei).reverse().toString())) {
                System.out.println("¡No se puede validar el dispositivo ya que su IMEI (" + imei + ") es un palindromo!");
                continue;
            }

            if(("LISTO_PARA_USAR".equals(device.getState())) && Integer.parseInt(device.getPuntaje()) > 60) {

                if (proveedoresExistentes.contains(device.getProveedor())){
                    ValidDevice validDevice = new ValidDevice();
                    dispositivosValidos.add(validDevice);
                }else{
                    InvalidDevice invalidDevice = new InvalidDevice();
                    dispositivosInvalidos.add(invalidDevice);
                }
                System.out.println("Validando dispositivo: " + device.getImei() + ", con puntaje: " + device.getPuntaje() + " - " + device.getState());
            }
        }
    }



}
