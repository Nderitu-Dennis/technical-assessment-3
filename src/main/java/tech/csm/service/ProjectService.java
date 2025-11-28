package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.csm.model.Project;
import tech.csm.repository.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repo;

    public List<Project> getBillableProjectsByTeam(Integer teamId) {
        return repo.findByTeam_TeamIdAndStatusAndIsBillable(teamId, "Active", true);
    }
}
