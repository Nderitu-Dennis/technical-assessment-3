package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.csm.model.ProjectAssignment;
import tech.csm.repository.ProjectAssignmentRepository;

import java.util.List;

@Service
public class ProjectAssignmentService {

    @Autowired
    private ProjectAssignmentRepository repo;

    public ProjectAssignment saveAssignment(ProjectAssignment assignment) {
        return repo.save(assignment);
    }

    public List<ProjectAssignment> getAllAssignments() {
        return repo.findAll();
    }

    public void deleteAssignment(Integer id) {
        repo.deleteById(id);
    }
}
