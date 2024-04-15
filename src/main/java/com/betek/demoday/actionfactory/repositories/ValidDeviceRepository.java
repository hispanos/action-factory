package com.betek.demoday.actionfactory.repositories;

import com.betek.demoday.actionfactory.models.validations.ValidDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidDeviceRepository extends JpaRepository<ValidDevice, Long> {
    boolean existsById(Long id);

}
