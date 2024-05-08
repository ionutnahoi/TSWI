package com.project.tswi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrow")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Borrow {
    public enum number_of_days_to_borrow {
        ONE_WEEK(1),
        TWO_WEEKS(2),
        THREE_WEEKS(3),
        FOR_WEEKS(4);
        private int weeks;

        number_of_days_to_borrow(int i) {
            this.weeks = i;
        }

        public int getWeeks() {
            return weeks;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_borrow;

    private LocalDate date_when_borrowed;
    private LocalDate date_when_return;

    private Long id_owner_book;

    public Borrow(number_of_days_to_borrow days, User user_who_borrowed) {
        this.date_when_borrowed = LocalDate.now();
        this.date_when_return = LocalDate.now().plusDays(Long.parseLong(String.valueOf(days)));
        this.user_who_borrowed = user_who_borrowed;
    }

    @ManyToOne()
    @JoinColumn
    private User user_who_borrowed;

    @ManyToOne()
    @JoinColumn(name = "id_book")
    private Book borrowed_book;
}
