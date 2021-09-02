package com.cybertek.businessmansystem_api.serviceImp;

import com.cybertek.businessmansystem_api.dto.ProjectDTO;
import com.cybertek.businessmansystem_api.entity.Project;
import com.cybertek.businessmansystem_api.enums.Status;
import com.cybertek.businessmansystem_api.mapper.MapperUtil;
import com.cybertek.businessmansystem_api.mapper.ProjectMapper;
import com.cybertek.businessmansystem_api.repository.ProjectRepository;
import com.cybertek.businessmansystem_api.repository.TaskRepository;
import com.cybertek.businessmansystem_api.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {


    private ProjectRepository projectRepository;
    private MapperUtil mapperUtil;
    private ProjectMapper projectMapper;
    private TaskRepository taskRepository;


    public ProjectServiceImpl(ProjectRepository projectRepository, MapperUtil mapperUtil, ProjectMapper projectMapper, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;
        this.projectMapper = projectMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public ProjectDTO getByProjectCode(String projectCode) {

        Project byProjectCode = projectRepository.findByProjectCode(projectCode);

        return mapperUtil.convert(byProjectCode, new ProjectDTO());
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream().map(each->mapperUtil.convert(each, new ProjectDTO())).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO projectDTO) {


        Project convert = mapperUtil.convert(projectDTO, new Project());
        convert.setInsertUserId(1L);//todo
        convert.setLastUpdateUserId(1L);//todo
        convert.setLastUpdateDateTime(LocalDateTime.now());
        convert.setStatus(Status.OPEN);




        projectRepository.save(convert);





    }

    @Override
    public void update(ProjectDTO projectDTO) {

        Project project = projectRepository.findByProjectCode(projectDTO.getProjectCode());
        Project convertedProject = projectMapper.convertToEntity(projectDTO);



        //status and id should come from database
        convertedProject.setId(project.getId());
        convertedProject.setStatus(project.getStatus());


        convertedProject.setLastUpdateDateTime(LocalDateTime.now());
        convertedProject.setLastUpdateUserId(1L); // todo after security implementation

        projectRepository.save(convertedProject);

    }

    @Override
    public void delete(String projectCode) {

        Project byProjectCode = projectRepository.findByProjectCode(projectCode);
        byProjectCode.setIsDeleted(true);
        projectRepository.save(byProjectCode);
    }

    @Override
    public void complete(String projectCode) {
        Project byProjectCode = projectRepository.findByProjectCode(projectCode);
        byProjectCode.setStatus(Status.COMPLETE);
        projectRepository.save(byProjectCode);

    }

    @Override
    public List<ProjectDTO> readAllByAssignedManager(String username) {

        List<Project> byAssignedManager = projectRepository.findByAssignedManager_UserName(username);

        return byAssignedManager.stream().map(each->mapperUtil.convert(each, new ProjectDTO())).collect(Collectors.toList());

    }

    @Override
    public List<ProjectDTO> readAllProjectDetails() {
        return null;
    }

    @Override
    public List<ProjectDTO> listNonCompletedProjects() {

        return projectRepository.findAllByStatusIsNot(Status.COMPLETE)
                .stream().map(each->mapperUtil.convert(each,new ProjectDTO()))
                .collect(Collectors.toList());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectServiceImpl)) return false;
        ProjectServiceImpl that = (ProjectServiceImpl) o;
        return Objects.equals(projectRepository, that.projectRepository)
                && Objects.equals(mapperUtil, that.mapperUtil) && Objects.equals(projectMapper, that.projectMapper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectRepository, mapperUtil, projectMapper);
    }
}

