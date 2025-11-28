package tech.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.csm.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByTeam_TeamIdAndStatusAndIsBillable(Integer teamId, String status, Boolean isBillable);
}
