package com.betek.demoday.actionfactory.repositories;

import com.betek.demoday.actionfactory.models.Device;
import com.betek.demoday.actionfactory.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsById(Long imei);

    Device findByImeiAndSupplier_Name(Long imei, String name);
}
