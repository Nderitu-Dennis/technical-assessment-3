package tech.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.csm.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findByDepartment_DepartmentId(Integer departmentId);
}
