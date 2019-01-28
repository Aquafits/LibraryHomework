package com.aquafits.library.business.impl;

import com.aquafits.library.business.BookService;
import com.aquafits.library.data.dao.BookDao;
import com.aquafits.library.data.model.books.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    @Override
    public boolean deleteBook(String id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findBookByCategoryName(String name) {
        return bookDao.findBookByCategoryName(name);
    }

    @Override
    public Book findBookById(String id) {
        return bookDao.findBookById(id);
    }

    @Override
    public Book findBookByName(String name) {
        return bookDao.findBookByName(name);
    }
}
