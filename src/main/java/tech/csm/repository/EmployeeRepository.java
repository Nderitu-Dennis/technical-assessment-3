package tech.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.csm.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByIsActiveTrue();
}
