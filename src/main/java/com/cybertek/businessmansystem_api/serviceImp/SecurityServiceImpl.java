package com.cybertek.businessmansystem_api.serviceImp;


import com.cybertek.businessmansystem_api.dto.UserDTO;
import com.cybertek.businessmansystem_api.entity.User;
import com.cybertek.businessmansystem_api.mapper.MapperUtil;
import com.cybertek.businessmansystem_api.service.SecurityService;
import com.cybertek.businessmansystem_api.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    private UserService userService;
    private MapperUtil mapperUtil;

    public SecurityServiceImpl(UserService userService, MapperUtil mapperUtil) {
        this.userService = userService;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user= userService.findByUserName(s);

       if (user== null){
           throw new UsernameNotFoundException("This user does not exist.");
       }

       return new org.springframework.security.core.userdetails
               .User(user.getId().toString(),user.getPassWord(),listAuthorities(user));
    }

    @Override
    public User loadUser(String param) {
        UserDTO user = userService.findByUserName(param);
        return mapperUtil.convert(user, new User());

    }


    private Collection<? extends GrantedAuthority> listAuthorities(UserDTO user){
        List<GrantedAuthority> authorityList = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getDescription());
        authorityList.add(authority);

        return  authorityList;
    }
}
