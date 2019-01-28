package com.aquafits.library.data.dao;

import com.aquafits.library.data.model.users.User;

import java.util.List;

public interface UserDao {
    boolean saveUser(User user);

    boolean deleteUser(String id);

    List<User> findAll();

    User findUserById(String id);

    User findUserByEmail(String email);
}
