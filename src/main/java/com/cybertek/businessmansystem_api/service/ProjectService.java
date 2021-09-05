package com.cybertek.businessmansystem_api.service;


import com.cybertek.businessmansystem_api.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    ProjectDTO getByProjectCode(String projectCode);

    List<ProjectDTO> listAllProjects();

    void save(ProjectDTO projectDTO);

    void update( ProjectDTO projectDTO);

    void delete ( String projectCode);

    void complete(String projectCode);

    List<ProjectDTO> readAllByAssignedManager(String username);
    List<ProjectDTO> readAllProjectDetails();
    List<ProjectDTO> listNonCompletedProjects();


}
