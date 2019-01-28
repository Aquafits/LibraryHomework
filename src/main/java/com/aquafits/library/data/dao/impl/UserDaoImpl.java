package com.aquafits.library.data.dao.impl;

import com.aquafits.library.data.Mock;
import com.aquafits.library.data.dao.UserDao;
import com.aquafits.library.data.model.strategies.BorrowStrategy;
import com.aquafits.library.data.model.users.Contract;
import com.aquafits.library.data.model.users.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private List<Contract> contracts = Mock.getInstance().contracts;
    private List<BorrowStrategy> strategies = Mock.getInstance().strategies;
    private List<User> users = Mock.getInstance().users;


    @Override
    public boolean saveUser(User user) {
        if (user.getId() == null) {
            user.setId("" + (users.size() + 1));
        } else {
            User u = findUserById(user.getId());
            if (u != null) {
                deleteUser(u.getId());
            }
        }

        users.add(user);
        BorrowStrategy strategy = user.getStrategy();
        if(!strategies.contains(strategy)){
            //strategy做了自增创建
            strategies.add(strategy);
        }
        for (Contract c : user.getStrategy().getContracts()) {
            if (!contracts.contains(c)) {
                c.setId(""+(contracts.size()+1));
                contracts.add(c);
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        User user = findUserById(id);
        BorrowStrategy strategy = user.getStrategy();

        if (strategy.getContracts() != null) {
            contracts.removeIf(c -> strategy.getContracts().contains(c));
        }
        strategies.removeIf(s -> s.getId().equals(strategy.getId()));
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
