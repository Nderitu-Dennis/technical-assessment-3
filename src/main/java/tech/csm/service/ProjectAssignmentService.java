package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.csm.model.ProjectAssignment;
import tech.csm.repository.ProjectAssignmentRepository;

import java.util.List;

@Service
public class ProjectAssignmentService {

    @Autowired
    private ProjectAssignmentRepository projectAssignmentRepository;

    public ProjectAssignment saveAssignment(ProjectAssignment assignment) {
        return projectAssignmentRepository.save(assignment);
    }

    public List<ProjectAssignment> getAllAssignedProjects() {
        return projectAssignmentRepository.findAll();
    }

    public void deleteAssignment(Integer assignmentId) {
        projectAssignmentRepository.deleteById(assignmentId);
    }
}
