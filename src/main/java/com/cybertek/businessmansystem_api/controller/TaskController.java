package com.cybertek.businessmansystem_api.controller;


import com.cybertek.businessmansystem_api.dto.TaskDTO;
import com.cybertek.businessmansystem_api.entity.ResponseWrapper;
import com.cybertek.businessmansystem_api.enums.Status;
import com.cybertek.businessmansystem_api.service.ProjectService;
import com.cybertek.businessmansystem_api.service.TaskService;
import com.cybertek.businessmansystem_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;

    //READ ALL TASKS
    @GetMapping
    public ResponseEntity<ResponseWrapper> readAll() {

        List<TaskDTO> tasks = taskService.listAllTasks();


        return ResponseEntity.ok(new ResponseWrapper("Successfully Retrieved All Tasks", tasks));
    }

    //READ BY ASSIGNED MANAGER
    @GetMapping("/project-manager")
    public ResponseEntity<ResponseWrapper> readyByAssignedManager() {

        List<TaskDTO> tasks = taskService.listAllTasksByProjectManager();


        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved tasks by project manager", tasks));
    }


    //READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> updateSave(@PathVariable("id") Long id) {


        TaskDTO task = taskService.findById(id);

        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved task by id", task));
    }

    //CREATE TASK
    @PostMapping()
    public ResponseEntity<ResponseWrapper> create(@RequestBody TaskDTO taskDTO) {


        TaskDTO task = taskService.save(taskDTO);


        return ResponseEntity
                .ok(new ResponseWrapper("Successfully task created", task));
    }


    // DELETE TASK
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper> delete(@PathVariable("id") Long id) {
        taskService.delete(id);


        return ResponseEntity
                .ok(new ResponseWrapper("Successfully task DELETED"));
    }

    // UPDATE TASK
    @PutMapping
    public ResponseEntity<ResponseWrapper> update(@RequestBody TaskDTO taskDTO) {
        taskService.update(taskDTO);

        TaskDTO task = taskService.findById(taskDTO.getId());


        return ResponseEntity
                .ok(new ResponseWrapper("Successfully task Updated",task));
    }

    //READ ALL NON COMPLETED TASKS

    @GetMapping("/employee")
    public ResponseEntity<ResponseWrapper> employeeReadAllNonCompletedTask() {

        List<TaskDTO> tasks = taskService.listAllTasksBySttatusIsNot(Status.COMPLETE);

        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved non completed tasks of current user ",tasks));


    }

    @PutMapping("/employee/update")
    public ResponseEntity<ResponseWrapper> employeeUpdateTask(@RequestBody TaskDTO taskDTO) {

      taskService.updateStatus(taskDTO);
      TaskDTO task = taskService.findById(taskDTO.getId());

        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved non completed tasks of current user ",task));


    }
}
