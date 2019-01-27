package com.aquafits.library.data.dao;

import com.aquafits.library.data.model.Book;

import java.util.List;

public interface BookDao {

    Boolean saveBook(Book book);

    Boolean deleteBook(String id);

    List<Book> findAll();

    List<Book> findBookByCategoryName(String name);

    Book findBookById(String id);

    Book findBookByName(String name);
}
