package com.aquafits.library.data.dao;

import com.aquafits.library.data.model.books.Book;

import java.util.List;

public interface BookDao {

    boolean saveBook(Book book);

    boolean deleteBook(String id);

    List<Book> findAll();

    List<Book> findBookByCategoryName(String name);

    Book findBookById(String id);

    Book findBookByName(String name);
}
