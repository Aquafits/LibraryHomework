package com.aquafits.library.data.model.users;

import com.aquafits.library.data.model.books.Book;
import com.aquafits.library.data.model.strategies.BorrowStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String email;

    private String password;

    private BorrowStrategy strategy;

    private String department;

    private String nickname;

    private String phoneNumber;

    private Role role;

    public BigDecimal getPenalty() {
        return strategy.getPenalty();
    }

    public boolean borrowBook(Book book) {
        return strategy.borrowBook(book);
    }

    public boolean returnBook(Book book) {
        return strategy.returnBook(book);
    }
}