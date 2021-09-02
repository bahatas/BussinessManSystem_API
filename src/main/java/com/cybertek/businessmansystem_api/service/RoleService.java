package com.cybertek.businessmansystem_api.service;

import com.cybertek.businessmansystem_api.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> listAllRoles();

    RoleDTO findById(Long id);
}
