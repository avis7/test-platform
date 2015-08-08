package com.bionic.university.services;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.entity.Role;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by c266 on 28.07.2015.
 */
public class RoleService {
    @Inject
    RoleDAO roleDAO;

    public List<Role> findAllRole() {
        return roleDAO.findAll();
    }

}
