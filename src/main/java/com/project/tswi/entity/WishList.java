package com.project.tswi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wishlist")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)

    private Long id;
    @JoinColumn()
    @ManyToOne
    private User user;
    @JoinColumn()
    @ManyToOne
    private Book book;

}
