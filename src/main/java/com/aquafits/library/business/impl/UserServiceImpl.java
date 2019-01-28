package com.aquafits.library.business.impl;

import com.aquafits.library.business.UserService;
import com.aquafits.library.data.dao.BookDao;
import com.aquafits.library.data.dao.UserDao;
import com.aquafits.library.data.model.books.Book;
import com.aquafits.library.data.model.users.User;
import com.aquafits.library.exceptions.BorrowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BookDao bookDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, BookDao bookDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    @Override
    public boolean saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public boolean deleteUser(String id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public boolean borrowBook(String userId, String bookId) {
        User user = userDao.findUserById(userId);
        Book book = bookDao.findBookById(bookId);
        try {
            user.borrowBook(book);
            userDao.saveUser(user);
            return true;
        }catch (BorrowException e){
            return false;
        }
    }

    @Override
    public BigDecimal getPenalty(String id) {
        User user = userDao.findUserById(id);
        return user.getPenalty();
    }

    @Override
    public boolean authUser(String email, String password) throws AuthException {
        User user = userDao.findUserByEmail(email);
        if(user == null || user.getRole().getName().equals("Admin")){
            throw new AuthException("Wrong user role");
        }else if (!password.equals(user.getPassword())) {
            throw new AuthException("Wrong email or password");
        }
        return true;
    }

    @Override
    public boolean authAdmin(String email, String password) throws AuthException {
        User user = userDao.findUserByEmail(email);
        if(user == null || !user.getRole().getName().equals("Admin")){
            throw new AuthException("Wrong user role");
        }else if (!password.equals(user.getPassword())) {
            throw new AuthException("Wrong email or password");
        }
        return true;
    }
}
