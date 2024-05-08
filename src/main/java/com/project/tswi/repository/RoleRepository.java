package com.project.tswi.repository;

import com.project.tswi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
//    Role saveRole(Role role);

}
