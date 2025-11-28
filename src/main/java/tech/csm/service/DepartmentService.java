package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.csm.model.Department;
import tech.csm.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    public List<Department> getActiveDepartments() {
        return repo.findByStatus("Active");
    }
}
