package tech.csm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.csm.model.Team;
import tech.csm.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repo;

    public List<Team> getTeamsByDepartment(Integer departmentId) {
        return repo.findByDepartment_DepartmentId(departmentId);
    }
}
