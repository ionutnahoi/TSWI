package com.project.tswi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    public User( @NonNull String username, @NonNull String password, String email, @NonNull String firstName, @NonNull String lastName, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Column(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    @Column(unique = true)
    private String email;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @OneToMany(mappedBy = "user_who_borrowed")
    @JsonIgnore
    List<Borrow> borrowList;

    @OneToMany(mappedBy = "borrowed_book")
    @JsonIgnore
    List<Borrow> booked_borrow_list;

    @OneToOne
    private Role role = new Role();

}
