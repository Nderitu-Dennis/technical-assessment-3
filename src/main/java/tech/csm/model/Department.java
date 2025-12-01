package tech.csm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Entity
@Table(name = "departments")
@Getter
@Setter
@ToString


public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="department_id")
    private Integer departmentId;

    @Column(name = "department_name", nullable = false, unique = true)
    private String departmentName;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
  
    
}
