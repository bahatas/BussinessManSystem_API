package com.cybertek.businessmansystem_api.serviceImp;

import com.cybertek.businessmansystem_api.dto.ProjectDTO;
import com.cybertek.businessmansystem_api.dto.TaskDTO;
import com.cybertek.businessmansystem_api.entity.Task;
import com.cybertek.businessmansystem_api.enums.Status;
import com.cybertek.businessmansystem_api.mapper.MapperUtil;
import com.cybertek.businessmansystem_api.repository.ProjectRepository;
import com.cybertek.businessmansystem_api.repository.TaskRepository;
import com.cybertek.businessmansystem_api.repository.UserRepository;
import com.cybertek.businessmansystem_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public List<TaskDTO> listAllTasks() {
        List<Task> all = taskRepository.findAll();
        return all.stream()
                .map(each -> mapperUtil.convert(each, new TaskDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {
        Task task = taskRepository.findById(id).get();

        return mapperUtil.convert(task, new TaskDTO());
    }

    @Override
    public void save(TaskDTO taskDTO) {

        Task taskEntity = mapperUtil.convert(taskDTO, new Task());

        taskEntity.setTaskStatus(Status.OPEN);
        taskEntity.setAssignedDate(LocalDate.now());
        taskRepository.save(taskEntity);

    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id).get();

        task.setIsDeleted(true);

        taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO taskDTO) {

        Task taskToUpdate = taskRepository.findById(taskDTO.getId()).get();

        taskToUpdate.setAssignedDate(LocalDate.now());
        taskRepository.save(taskToUpdate);

    }

    @Override
    public int totalNonCompletedTasks(String projectCode) {
        return 0;
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return 0;
    }

    @Override
    public List<TaskDTO> listAllByProject(ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasksBySttatusIsNot(Status status) {
        return taskRepository.findAllByTaskStatusIsNot(Status.COMPLETE).stream()
                .map(each->mapperUtil.convert(each,new TaskDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status status) {
        return null;
    }

    @Override
    public void updateStatus(TaskDTO taskDTO) {

    }

    @Override
    public List<TaskDTO> readAllByEmployee() {
        return null;
    }
}
