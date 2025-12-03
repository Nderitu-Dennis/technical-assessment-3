package tech.csm.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_leave_quota", 
    uniqueConstraints = @UniqueConstraint(columnNames =
{"employee_id", "leave_type_id", "leave_year"}))
@Getter
@Setter
@ToString
public class EmployeeLeaveQuota implements Serializable {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "quota_id")
 private Integer quotaId;

 @ManyToOne
 @JoinColumn(name = "employee_id")
 private Employee employee;

 @ManyToOne
 @JoinColumn(name = "leave_type_id")
 private LeaveType leaveType;

 @Column(name = "leave_year")
 private Integer year;

 @Column(name = "total_allocated")
 private Double totalAllocated;

 @Column(name = "total_used")
 private Double totalUsed = 0.0;
}



