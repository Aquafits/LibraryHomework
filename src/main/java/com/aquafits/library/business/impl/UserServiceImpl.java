package com.aquafits.library.business.impl;

import com.aquafits.library.business.UserService;
import com.aquafits.library.data.dao.BookDao;
import com.aquafits.library.data.dao.UserDao;
import com.aquafits.library.data.model.Book;
import com.aquafits.library.data.model.Contract;
import com.aquafits.library.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
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
    public Boolean saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public Boolean deleteUser(String id) {
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
        if(user.isBorrowable()){
            Book book = bookDao.findBookById(bookId);

            Calendar now = Calendar.getInstance();
            Calendar toReturn = Calendar.getInstance();
            toReturn.add(Calendar.DATE, user.getStrategy().getMaxBorrowDays());

            Contract contract = new Contract(null, book, now, toReturn, false);

            user.getContracts().add(contract);

            userDao.saveUser(user);

            return true;
        }
        return false;
    }

    @Override
    public BigDecimal getPenalty(String id) {
        User user = userDao.findUserById(id);
        return user.getPenalty();
    }
}
