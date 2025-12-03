package tech.csm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.model.EmployeeLeaveQuota;
import tech.csm.model.LeaveType;

@Repository
public interface EmployeeLeaveQuotaRepository extends JpaRepository<EmployeeLeaveQuota, Integer> {

    @Query("SELECT q.leaveType FROM EmployeeLeaveQuota q " +
           "WHERE q.employee.employeeId = :employeeId " +
           "AND (q.totalAllocated - q.totalUsed) > 0")
    
    List<LeaveType> findActiveLeaveTypesByEmployee(@Param("employeeId") Integer employeeId);

    Optional<EmployeeLeaveQuota> findByEmployeeEmployeeIdAndLeaveTypeLeaveTypeId(Integer employeeId, Integer leaveTypeId);
    
    

}

