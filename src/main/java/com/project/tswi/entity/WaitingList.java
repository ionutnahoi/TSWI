package com.project.tswi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "waitinglist")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WaitingList {
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
