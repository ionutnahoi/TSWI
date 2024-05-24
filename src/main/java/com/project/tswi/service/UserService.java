package com.project.tswi.service;

import com.project.tswi.entity.Role;
import com.project.tswi.entity.User;
import com.project.tswi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    private final BookService bookService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user.getId() == null) {
            throw new UsernameNotFoundException("Username not found in Database!");
        }
        return user;
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
            user.setRole(Role.USER);
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

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
