package com.cybertek.businessmansystem_api.entity;


import com.cybertek.businessmansystem_api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Task extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private User assignedEmployee;


    private String taskSubject;

    private String taskDetail;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate assignedDate;



}
