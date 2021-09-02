package com.cybertek.businessmansystem_api.service;

import com.cybertek.businessmansystem_api.dto.ProjectDTO;
import com.cybertek.businessmansystem_api.dto.TaskDTO;
import com.cybertek.businessmansystem_api.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    List<TaskDTO> listAllTasks();
    TaskDTO findById(Long id);
    void save(TaskDTO taskDTO);
    void delete(Long id);
    void update(TaskDTO taskDTO);

    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTask(String projectCode);

    List<TaskDTO> listAllByProject(ProjectDTO projectDTO);

    List<TaskDTO> listAllTasksBySttatusIsNot(Status status);

    List<TaskDTO> listAllTasksByStatus(Status status);

    void updateStatus(TaskDTO taskDTO);

    List<TaskDTO> readAllByEmployee();


}
