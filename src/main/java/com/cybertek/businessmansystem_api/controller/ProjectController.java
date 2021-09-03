package com.cybertek.businessmansystem_api.controller;


import com.cybertek.businessmansystem_api.dto.ProjectDTO;
import com.cybertek.businessmansystem_api.service.ProjectService;
import com.cybertek.businessmansystem_api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;
    private UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("listOfAllProjects",projectService.listAllProjects());

        System.out.println("projectService.listAllProjects() = " + projectService.listAllProjects());
        model.addAttribute("managers",userService.listAllByRole("manager"));

        return "/pages/project/project-create";
    }


    @PostMapping("/create")
    public String postCreateProject(ProjectDTO projectDTO) throws Exception {

        projectService.save(projectDTO);
        userService.save(projectDTO.getAssignedManager());
        return "redirect:/project/create";
    }

    @GetMapping ("/update/{projectCode}")
    public String updateProject (@PathVariable("projectCode") String projectCode, Model model){


        model.addAttribute("updatedProject",projectService.getByProjectCode(projectCode));
        model.addAttribute("listOfAllProjects",projectService.listAllProjects());
        model.addAttribute("managers",userService.listAllByRole("manager"));

        return "/pages/project/project-update";
    }

    @PostMapping ("/update/{projectCode}")
    public String updateProjectPost (@PathVariable("projectCode") String projectCode, Model model, ProjectDTO projectDTO){


        projectService.update(projectDTO);
        return "redirect:/project/create";
}

    @GetMapping("/delete/{projectCode}")
    public String delete(@PathVariable("projectCode") String projectCode){

        projectService.delete(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String updateStatus(@PathVariable("projectCode") String projectCode){


        projectService.complete(projectCode);
        return "redirect:/project/create";
    }

    @GetMapping("/manager/status")
    public String managerComplete(Model model){

        model.addAttribute("projectlist",projectService.listAllProjects());
        model.addAttribute("managers",userService.listAllByRole("manager"));

        return "pages/manager/project-status";
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
