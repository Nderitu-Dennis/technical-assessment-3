package tech.csm.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @FutureOrPresent(message="\"from\" cannot be in the past")
    @Column(name = "from_date")
    private LocalDate fromDate;

    @FutureOrPresent(message="\"to\" cannot be in the past")
    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "total_days")
    private Double totalDays;

    @NotBlank(message="reason cannot be blank")  //runtime level validation
    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status = "Pending";

    @Column(name = "applied_on") 
    
    private LocalDateTime appliedOn = LocalDateTime.now();

    @Column(name = "approved_by")
    private Integer approvedBy;

    @Column(name = "approved_on")
    private LocalDateTime approvedOn;
    
    //format dates from LocalDate
    public String getFromDateFormatted() {
        return fromDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getToDateFormatted() {
        return toDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

   

}
