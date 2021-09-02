package com.cybertek.businessmansystem_api.serviceImp;

import com.cybertek.businessmansystem_api.dto.RoleDTO;
import com.cybertek.businessmansystem_api.entity.Role;
import com.cybertek.businessmansystem_api.mapper.MapperUtil;
import com.cybertek.businessmansystem_api.repository.RoleRepository;
import com.cybertek.businessmansystem_api.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private MapperUtil mapperUtil;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
        List<Role> list  = roleRepository.findAll();


        return list.stream().map(each-> mapperUtil.convert(each, new RoleDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).get();
        return mapperUtil.convert(role, new RoleDTO()) ;
    }
}
