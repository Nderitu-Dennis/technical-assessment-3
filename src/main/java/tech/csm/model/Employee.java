package tech.csm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString

public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "employee_code", nullable = false, unique = true)
    private String employeeCode;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department; //many employees can be in one dept

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;   

    
}


