package com.aquafits.library.data.dao.impl;

import com.aquafits.library.data.dao.BookDao;
import com.aquafits.library.data.Mock;
import com.aquafits.library.data.model.books.Book;
import com.aquafits.library.data.model.books.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private List<Book> books = Mock.getInstance().books;
    private List<Category> categories = Mock.getInstance().categories;

    @Override
    public boolean saveBook(Book book) {
        if (book.getId() == null) {
            book.setId("" + (books.size() + 1));
        }else{
            Book b = findBookById(book.getId());
            if (b != null) {
                deleteBook(b.getId());
            }
        }

        books.add(book);
        if (!categories.contains(book.getCategory())) {
            categories.add(book.getCategory());
        }
        return true;
    }

    @Override
    public boolean deleteBook(String id) {
        return books.removeIf(b -> b.getId().equals(id));
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public List<Book> findBookByCategoryName(String name) {
        List<Book> ret = new ArrayList<>();
        for (Book b : books) {
            if (b.getCategory().getName().equals(name)) {
                ret.add(b);
            }
        }
        return ret;
    }

    @Override
    public Book findBookById(String id) {
        for (Book b : books) {
            if (id.equals(b.getId())) {
                return b;
            }
        }
        return null;
    }

    @Override
    public Book findBookByName(String name) {
        for (Book b : books) {
            if (name.equals(b.getName())) {
                return b;
            }
        }
        return null;
    }

}
