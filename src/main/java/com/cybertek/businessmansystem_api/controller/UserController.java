package com.cybertek.businessmansystem_api.controller;


import com.cybertek.businessmansystem_api.dto.UserDTO;
import com.cybertek.businessmansystem_api.entity.ResponseWrapper;
import com.cybertek.businessmansystem_api.service.RoleService;
import com.cybertek.businessmansystem_api.service.UserService;
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



    @GetMapping("/{username}")
    public ResponseEntity<ResponseWrapper> getUSerByName(@PathVariable("username") String username) { //readAll

        UserDTO result = userService.findByUserName(username);

        return ResponseEntity.ok(new ResponseWrapper("User succesfully retrieved",result));
    }


    @PostMapping("/create")
    public String insertUser(Model model, UserDTO userDTO) {

        userService.save(userDTO);
        return "redirect:/user/create";
    }

    @GetMapping("/update/{userName}")
    public String editUser(@PathVariable("userName") String username,Model model) {
        model.addAttribute("updatedUser", userService.findByUserName(username));
        model.addAttribute("userList", userService.listAllUser());
        model.addAttribute("roles", roleService.listAllRoles());

        return "pages/user/user-update";
    }

    @PostMapping("/update/{username}")
    public String updateUser(Model model, @PathVariable("username") String username,UserDTO userDTO) {
        userService.update(userDTO);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUSer(Model model, @PathVariable("username") String username) {


        userService.delete(username);

        return "redirect:/user/create";
    }
}
