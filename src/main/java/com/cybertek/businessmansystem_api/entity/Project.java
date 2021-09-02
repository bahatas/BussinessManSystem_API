package com.cybertek.businessmansystem_api.entity;

import com.cybertek.businessmansystem_api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="projects_table")
@Where(clause = "is_deleted=false")
public class Project extends BaseEntity {

    private String projectName;

    @Column(unique = true)
    private String projectCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private User assignedManager;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(columnDefinition = "text")
    private String projectDetail;

    @Enumerated(EnumType.STRING)
    private Status status;


}
