package com.cybertek.businessmansystem_api.controller;


import com.cybertek.businessmansystem_api.dto.ProjectDTO;
import com.cybertek.businessmansystem_api.entity.ResponseWrapper;
import com.cybertek.businessmansystem_api.service.ProjectService;
import com.cybertek.businessmansystem_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    //READ ALL PROJECTS
    @GetMapping
    public ResponseEntity<ResponseWrapper> readAll(Model model){

        List<ProjectDTO> projects = projectService.listAllProjects();

        return ResponseEntity.ok(new ResponseWrapper("Projects are retrieved",projects));
    }


    //READ PROJECT BY PROJECT CDDE
    @GetMapping("/{projectcode}")
    public ResponseEntity<ResponseWrapper> readByProjectCode(@PathVariable String projectcode) throws Exception {

        ProjectDTO project = projectService.getByProjectCode(projectcode);
        return ResponseEntity.ok(new ResponseWrapper("Project is retrieved",project));
    }

    //CREATE PROJECT
    @PostMapping
    public ResponseEntity<ResponseWrapper> createPrpject(@RequestBody ProjectDTO projectDTO) throws Exception {

        projectService.save(projectDTO);
        ProjectDTO project = projectService.getByProjectCode(projectDTO.getProjectCode());

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully created",project));
    }

    //UPDATE PROJECT
    @PutMapping
    public ResponseEntity<ResponseWrapper> updatePrpject(@RequestBody ProjectDTO projectDTO) throws Exception {

        projectService.update(projectDTO);
        ProjectDTO project = projectService.getByProjectCode(projectDTO.getProjectCode());

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated",project));
    }

    //DELETE PROJECT
    @DeleteMapping("/delete/{projectCode}")
    public ResponseEntity<ResponseWrapper>  delete(@PathVariable("projectCode") String projectCode){

        projectService.delete(projectCode);

        return ResponseEntity.ok(new ResponseWrapper("Project Successfully DELETED"));

    }

    //COMPLETE PROJECT
    @PutMapping("/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> updateStatus(@PathVariable("projectCode") String projectCode){


        projectService.complete(projectCode);
        ProjectDTO project = projectService.getByProjectCode(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project is Completed",project));
    }



    //READ ALL PROJECTS DETAILS
    @GetMapping("/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> readAllProjectDetails(){


        List<ProjectDTO> projectDTOS = projectService.readAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Project Details are Retrieved",projectDTOS));
    }

    @PostMapping("/manager/status/{managerUserName}")
    public String listAllWithManager(@PathVariable("managerUserName") String userName, Model model, ProjectDTO projectDTO){

        model.addAttribute("projectlist",projectService.listAllProjects());
        model.addAttribute("managers",userService.listAllByRole("manager"));
        model.addAttribute("project",projectService.readAllByAssignedManager(userName));
        model.addAttribute("selectedmanager",userService.findByUserName(userName));


        return "pages/manager/project-status-selectedmanager";
    }
    @GetMapping("/manager/status/{managerUserName}")
    public String getListAllWithManager(@PathVariable("managerUserName") String userName, Model model, ProjectDTO projectDTO){

        model.addAttribute("projectlist",projectService.listAllProjects());
        model.addAttribute("managers",userService.listAllByRole("manager"));
        model.addAttribute("project",projectService.readAllByAssignedManager(userName));
        model.addAttribute("selectedmanager",userService.findByUserName(userName));


        return "pages/manager/project-status-selectedmanager";
    }

    @GetMapping("/manager/complete/{managerUserName}/{projectCode}")
    public String updateStatusByManager(@PathVariable("projectCode") String projectCode,@PathVariable("managerUserName") String userName){


        projectService.complete(projectCode);
        return "redirect:/project/manager/status/{managerUserName}";

    }
}
