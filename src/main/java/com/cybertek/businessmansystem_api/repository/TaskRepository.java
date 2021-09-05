package com.cybertek.businessmansystem_api.repository;

import com.cybertek.businessmansystem_api.entity.Task;
import com.cybertek.businessmansystem_api.entity.User;
import com.cybertek.businessmansystem_api.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {


    List<Task> findAllByTaskStatusIsNot(Status status);
    List<Task> findAllByProjectAssignedManager(User manager);
}
