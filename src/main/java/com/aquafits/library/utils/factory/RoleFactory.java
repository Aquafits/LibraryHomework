package com.aquafits.library.utils.factory;

import com.aquafits.library.data.Mock;
import com.aquafits.library.data.model.users.Role;

import java.util.List;

public class RoleFactory {
    private static RoleFactory ourInstance = new RoleFactory();

    public static RoleFactory getInstance() {
        return ourInstance;
    }

    private List<Role> roles = Mock.getInstance().roles;

    private RoleFactory() {
    }

    public Role getRole(String role) {
        Role ret = null;
        for (Role r : roles) {
            if (r.getName().equals(role) && (!r.getName().equals("Admin"))) {
                return r;
            }
            if (r.getName().equals("Generic")) {
                ret = r;
            }
        }
        return ret;
    }
}
