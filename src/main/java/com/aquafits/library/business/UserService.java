package com.aquafits.library.business;

import com.aquafits.library.data.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    Boolean saveUser(User user);

    Boolean deleteUser(String id);

    List<User> findAll();

    User findUserById(String id);

    User findUserByEmail(String email);

    boolean borrowBook(String userId, String bookId);

    BigDecimal getPenalty(String id);
}
