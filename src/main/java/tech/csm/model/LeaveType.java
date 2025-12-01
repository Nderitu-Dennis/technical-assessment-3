package tech.csm.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "leave_types")
@Getter
@Setter
@ToString

public class LeaveType implements Serializable {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "leave_type_id")
 private Integer leaveTypeId;

 @Column(name = "leave_type_code", nullable = false, unique = true)
 private String leaveTypeCode;

 @Column(name = "leave_type_name", nullable = false)
 private String leaveTypeName;

 @Column(name = "is_paid_leave", nullable = false)
 private Boolean isPaidLeave = true;

 @Column(name = "is_active", nullable = false)
 private Boolean isActive = true;

 
}





