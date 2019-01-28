package com.aquafits.library.data.model.strategies;

import com.aquafits.library.data.model.books.Book;
import com.aquafits.library.data.model.users.Contract;
import com.aquafits.library.exceptions.BorrowException;
import com.aquafits.library.exceptions.ReturnException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BorrowStrategy {

    protected String id;

    protected String name;

    protected int maxItems;

    protected int maxBorrowDays;

    protected List<Contract> contracts;

    abstract public boolean borrowBook(Book book) throws BorrowException;

    abstract public boolean returnBook(Book book) throws ReturnException;

    abstract public BigDecimal getPenalty();
}
