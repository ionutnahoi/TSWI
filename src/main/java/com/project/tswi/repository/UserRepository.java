package com.project.tswi.repository;

import com.project.tswi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.username=:name  or u.email= :email")
    User getUserByNameOrEmail(Optional<String> name, Optional<String> email);

    @Query("SELECT u from User u where u.username=:username and u.password=:password")
    Optional<User> login(String username, String password);

    User findByUsername(String username);

    Optional<User> findByEmail(String username);

//    @Query("INSERT INTO waitinglist(id_book,id_user) values(idBook,idUser) ")
//    void addWaiting(long idUser, long idBook);
}