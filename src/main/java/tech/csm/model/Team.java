package tech.csm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "teams",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"department_id", "team_name"})
       })

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties({"department", "projects"})

public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private Integer teamId;

    @Column(name="team_code", nullable = false, length = 20)
    private String teamCode;

    @Column(name="team_name",nullable = false, length = 100)
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false, length = 10)
    private String status; // Active / Inactive

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "team")
    private List<Project> projects;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
