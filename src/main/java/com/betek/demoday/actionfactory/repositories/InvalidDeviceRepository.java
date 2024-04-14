package com.betek.demoday.actionfactory.repositories;

import com.betek.demoday.actionfactory.models.validations.InvalidDevice;
import com.betek.demoday.actionfactory.models.validations.ValidDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidDeviceRepository extends JpaRepository<InvalidDevice, Long> {

}
