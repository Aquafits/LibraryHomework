package com.aquafits.library.data.dao.impl;

import com.aquafits.library.data.Mock;
import com.aquafits.library.data.dao.UserDao;
import com.aquafits.library.data.model.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private List<Contract> contracts = Mock.getInstance().contracts;
    private List<BorrowStrategy> strategies = Mock.getInstance().strategies;
    private List<User> users = Mock.getInstance().users;


    @Override
    public Boolean saveUser(User user) {
        if (user.getId() == null) {
            user.setId("" + (users.size() + 1));
        } else {
            User u = findUserById(user.getId());
            if (u != null) {
                deleteUser(u.getId());
            }
        }

        users.add(user);
        if (!strategies.contains(user.getStrategy())) {
            strategies.add(user.getStrategy());
        }
        for (Contract c : user.getContracts()) {
            if (!contracts.contains(c)) {
                c.setId(""+(contracts.size()+1));
                contracts.add(c);
            }
        }

        return null;
    }

    @Override
    public Boolean deleteUser(String id) {
        User user = findUserById(id);
        if (user.getContracts() != null) {
            contracts.removeIf(c -> user.getContracts().contains(c));
        }
        users.removeIf(u -> u.getId().equals(user.getId()));
        return true;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findUserById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}
