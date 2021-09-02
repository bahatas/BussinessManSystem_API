package com.cybertek.businessmansystem_api.repository;

import com.cybertek.businessmansystem_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
