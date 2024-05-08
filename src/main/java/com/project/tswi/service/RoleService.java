package com.project.tswi.service;

import com.project.tswi.entity.Role;
import com.project.tswi.repository.RoleRepository;
import com.project.tswi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
