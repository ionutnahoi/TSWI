package com.project.tswi.service;

import com.project.tswi.entity.Role;
import com.project.tswi.entity.User;
import com.project.tswi.repository.RoleRepository;
import com.project.tswi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    private final BookService bookService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user.getId() == null) {
            throw new UsernameNotFoundException("Username not found in Database!");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

       authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public Optional<User> getbyid(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(roleRepository.findRoleByName("USER"));
            userRepository.save(user);
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void update(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            userRepository.save(user);
        }
    }

    public User getUserByNameOrEmail(Optional<String> name, Optional<String> email) {
        return userRepository.getUserByNameOrEmail(name, email);
    }

    public Object login(String username, String password) {
        return userRepository.login(username, password).isPresent() ? (User) userRepository.login(username, password).get() : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveRole(Role role) {
        if (roleRepository.findRoleByName(role.getName()) == null) {
            roleRepository.save(role);
        }
    }

    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findRoleByName(roleName);
        user.setRole(role);
        userRepository.save(user);
    }
}
