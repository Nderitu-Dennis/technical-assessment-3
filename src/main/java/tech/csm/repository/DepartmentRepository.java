package tech.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.csm.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByStatus(String status); // Active departments

}
