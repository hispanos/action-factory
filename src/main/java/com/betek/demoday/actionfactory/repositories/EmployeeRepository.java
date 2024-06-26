package com.betek.demoday.actionfactory.repositories;

import com.betek.demoday.actionfactory.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    boolean existsByEmail(String email);
    Employee findByEmail(String email);
    Optional<Employee> findById(Long id);
}
