package com.aquafits.library.data.dao;

import com.aquafits.library.data.model.User;

import java.util.List;

public interface UserDao {
    Boolean saveUser(User user);

    Boolean deleteUser(String id);

    List<User> findAll();

    User findUserById(String id);

    User findUserByEmail(String email);
}
