package com.cybertek.businessmansystem_api.repository;

import com.cybertek.businessmansystem_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    User findByUserName(String username);

    List<User> findAllByRoleDescriptionIgnoreCase(String description);

    //todo @Transactional
    void deleteByUserName(String username);

}
