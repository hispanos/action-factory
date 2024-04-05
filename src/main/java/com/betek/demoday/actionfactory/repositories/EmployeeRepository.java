package com.betek.demoday.actionfactory.repositories;

import com.betek.demoday.actionfactory.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}
