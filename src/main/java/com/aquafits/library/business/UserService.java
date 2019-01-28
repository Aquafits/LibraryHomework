package com.aquafits.library.business;

import com.aquafits.library.data.model.users.User;

import javax.security.auth.message.AuthException;
import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    boolean deleteUser(String id);

    List<User> findAll();

    User findUserById(String id);

    User findUserByEmail(String email);

    boolean borrowBook(String userId, String bookId);

    BigDecimal getPenalty(String id);

    boolean authUser(String email, String password) throws AuthException;

    boolean authAdmin(String email, String password) throws AuthException;

}
