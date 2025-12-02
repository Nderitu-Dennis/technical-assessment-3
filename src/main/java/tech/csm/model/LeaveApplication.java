package tech.csm.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_applications")
@Getter
@Setter
@ToString

public class LeaveApplication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_application_id")
    private Integer leaveApplicationId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @Column(name = "total_days", nullable = false)
    private Double totalDays;

    @Column(name = "reason", nullable = false, length = 500)
    private String reason;

    @Column(name = "status", nullable = false)
    private String status = "Pending";

    @Column(name = "applied_on", nullable = false)
    private LocalDateTime appliedOn = LocalDateTime.now();

    @Column(name = "approved_by")
    private Integer approvedBy;

    @Column(name = "approved_on")
    private LocalDateTime approvedOn;
}
