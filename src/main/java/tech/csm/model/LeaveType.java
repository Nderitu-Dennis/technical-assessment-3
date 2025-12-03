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

 @Column(name = "leave_type_code")
 private String leaveTypeCode;

 @Column(name = "leave_type_name")
 private String leaveTypeName;

 @Column(name = "is_paid_leave")
 private Boolean isPaidLeave = true;

 @Column(name = "is_active")
 private Boolean isActive = true;

 
}





