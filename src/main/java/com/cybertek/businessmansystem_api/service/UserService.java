package com.cybertek.businessmansystem_api.service;

import com.cybertek.businessmansystem_api.dto.UserDTO;
import com.cybertek.businessmansystem_api.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserDTO> listAllUser();

    UserDTO findByUserName(String username);

    UserDTO save(UserDTO userDTO) throws Exception;

    UserDTO update(UserDTO userDTO);

    void delete(String username);

    List<UserDTO> listAllByRole(String role);

    Boolean checkIfUserCanBeDeleted(User user);


}
