package tech.csm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.csm.model.LeaveType;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    List<LeaveType> findByIsActive(Boolean isActive);


}


