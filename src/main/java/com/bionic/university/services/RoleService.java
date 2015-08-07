package com.bionic.university.services;

import com.bionic.university.dao.RoleDAO;
import com.bionic.university.entity.Role;

import javax.inject.Inject;

/**
 * Created by c266 on 28.07.2015.
 */
public class RoleService {
    @Inject
    RoleDAO roleDAO;

    public Role findRole(int role_id){
        try {
            Role role = roleDAO.find(role_id);
            return role;
        }catch (Exception e){}
        return null;
    }

}
