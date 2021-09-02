package com.cybertek.businessmansystem_api.controller;


import com.cybertek.businessmansystem_api.dto.UserDTO;
import com.cybertek.businessmansystem_api.service.RoleService;
import com.cybertek.businessmansystem_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.listAllRoles());
        model.addAttribute("userList", userService.listAllUser());

        return "pages/user/user-create";
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
