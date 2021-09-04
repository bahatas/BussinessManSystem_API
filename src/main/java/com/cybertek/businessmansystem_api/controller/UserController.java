package com.cybertek.businessmansystem_api.controller;


import com.cybertek.businessmansystem_api.dto.UserDTO;
import com.cybertek.businessmansystem_api.entity.ResponseWrapper;
import com.cybertek.businessmansystem_api.service.RoleService;
import com.cybertek.businessmansystem_api.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;



    //readAll
    @GetMapping
    public ResponseEntity<ResponseWrapper> create() {

        List<UserDTO> result = userService.listAllUser();

     return ResponseEntity.ok(new ResponseWrapper("Users succesfully retrieved",result));
    }



    //get by username
    @GetMapping("/{username}")
    public ResponseEntity<ResponseWrapper> getUSerByName(@PathVariable("username") String username) { //readAll

        UserDTO result = userService.findByUserName(username);

        return ResponseEntity.ok(new ResponseWrapper("User succesfully retrieved",result));
    }


    //create user
    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper>insertUser(@RequestBody UserDTO userDTO) throws Exception {

        UserDTO save = userService.save(userDTO);
        return ResponseEntity.ok(new ResponseWrapper(  "User has been created",save));
    }

    //update user
    @PutMapping("/update")
    public ResponseEntity<ResponseWrapper> editUser(@RequestBody UserDTO userDTO ) {
        UserDTO updatedUser = userService.update(userDTO);


        return ResponseEntity.ok(new ResponseWrapper("User has been updated",updatedUser));
    }



    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ResponseWrapper> deleteUSer(@PathVariable("username") String username) throws Exception {



        userService.delete(username);

        return ResponseEntity.ok(new ResponseWrapper("User has been deleted"));
    }

    @GetMapping("/role")
    public ResponseEntity<ResponseWrapper> readByRole(@RequestParam String role)  {



        List<UserDTO> users = userService.listAllByRole(role);


        return ResponseEntity.ok(new ResponseWrapper("Users Successfully retrieved by role",users));
    }
}
