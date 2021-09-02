package com.cybertek.businessmansystem_api.dto;


import com.cybertek.businessmansystem_api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDTO {

    private Long id;
    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate endDate;

    private String projectDetail;
    private Status status;

    private int completedCount;
    private int unCompletedCount;
}
