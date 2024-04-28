package com.betek.demoday.actionfactory.repositories;

import com.betek.demoday.actionfactory.models.Role;
import com.betek.demoday.actionfactory.models.validations.InvalidDevice;
import com.betek.demoday.actionfactory.models.validations.ValidDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvalidDeviceRepository extends JpaRepository<InvalidDevice, Long> {

    Optional<InvalidDevice>  findByImei(Long imei);
    Optional<List<InvalidDevice>>  findBySupplier(String supplier);

}
