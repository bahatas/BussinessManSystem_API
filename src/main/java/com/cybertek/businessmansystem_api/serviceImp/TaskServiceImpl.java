package com.cybertek.businessmansystem_api.serviceImp;

import com.cybertek.businessmansystem_api.dto.ProjectDTO;
import com.cybertek.businessmansystem_api.dto.TaskDTO;
import com.cybertek.businessmansystem_api.entity.Project;
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
    public TaskDTO save(TaskDTO taskDTO) {

        Task taskEntity = mapperUtil.convert(taskDTO, new Task());

        taskEntity.setTaskStatus(Status.OPEN);
        taskEntity.setAssignedDate(LocalDate.now());
        taskRepository.save(taskEntity);

        return mapperUtil.convert(taskEntity,new TaskDTO());
    }

    @Override
    public void delete(Long id) {

        Task task = taskRepository.findById(id).get();

        task.setIsDeleted(true);

        taskRepository.save(task);
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) {

        Task taskToUpdate = taskRepository.findById(taskDTO.getId()).get();

        taskToUpdate.setAssignedDate(LocalDate.now());
        Task save = taskRepository.save(taskToUpdate);

        return mapperUtil.convert(save, new TaskDTO());

    }

    @Override
    public int totalNonCompletedTasks(String projectCode) {

        List<Task> allByTaskStatusIsNot = taskRepository.findAllByTaskStatusIsNot(Status.COMPLETE);

        return allByTaskStatusIsNot.size();
    }

    @Override
    public int totalCompletedTask(String projectCode) {
      return  taskRepository.findAll().stream().filter(each->
                each.getTaskStatus()==Status.COMPLETE).collect(Collectors.toList()).size();
    }

    @Override
    public List<TaskDTO> listAllByProject(ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasksByProjectManager() {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasksBySttatusIsNot(Status status) {
        return taskRepository.findAllByTaskStatusIsNot(Status.COMPLETE).stream()
                .map(each->mapperUtil.convert(each,new TaskDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(String status) {
        return taskRepository.findAll().stream()
                .filter(each->each.getTaskStatus()==Status.valueOf(status)).collect(Collectors.toList())
                .stream().map(each->mapperUtil.convert(each,new TaskDTO())).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO taskDTO) {

    }

    @Override
    public List<TaskDTO> readAllByEmployee() {
        return null;
    }
}
