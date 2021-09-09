package com.cybertek.businessmansystem_api.controller;


import com.cybertek.businessmansystem_api.dto.UserDTO;
import com.cybertek.businessmansystem_api.entity.ResponseWrapper;
import com.cybertek.businessmansystem_api.entity.User;
import com.cybertek.businessmansystem_api.entity.common.AuthenticationRequest;
import com.cybertek.businessmansystem_api.mapper.MapperUtil;
import com.cybertek.businessmansystem_api.service.UserService;
import com.cybertek.businessmansystem_api.util.JWTUtil;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private MapperUtil mapperUtil;

    //TODO create confirmation token service and implementations and repo
//    @Autowired
//    private ConfirmationToken confirmationToken;


    @PostMapping("/authenticate")
    public ResponseEntity<ResponseWrapper> doLogin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(authentication);

        UserDTO foundUser = userService.findByUserName(username);

        User convertedUser = mapperUtil.convert(foundUser,new User());



  /* TODO      is Enable part is now NULL it causes bug

      if (!foundUser.isEnables()){

            throw new Exception("Please verify your user");
        } */


        String jwtToken = jwtUtil.generateToken(convertedUser);
        return ResponseEntity.ok(new ResponseWrapper("login Successful", jwtToken));
    }


    @GetMapping("/confirmation")
    public ResponseEntity<ResponseWrapper> confirmEmail(@RequestParam("token") String token ){

//todo confirmation token part is not implemented needs: REPO / SERVICE / IMPL.

//        ConfirmmationToken confirmmationToken = confirmationService.readByToken(token);
//        UserDTO  confirmUser = userService.confirm(confirmmationToken.getUser());
//        confirmationTokenService.delete(confirmmationToken);
//
//        return ResponseEntity.ok(new ResponseWrapper("User Has been Confirmed", confirmUser));
        return ResponseEntity.ok(new ResponseWrapper("User Has been Confirmed"));

    }

}
