package com.betek.demoday.actionfactory.repositories;

import com.betek.demoday.actionfactory.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
