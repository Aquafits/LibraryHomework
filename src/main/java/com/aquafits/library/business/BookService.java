package com.aquafits.library.business;

import com.aquafits.library.data.model.books.Book;

import java.util.List;

public interface BookService {
    boolean saveBook(Book book);

    boolean deleteBook(String id);

    List<Book> findAll();

    List<Book> findBookByCategoryName(String name);

    Book findBookById(String id);

    Book findBookByName(String name);
}
