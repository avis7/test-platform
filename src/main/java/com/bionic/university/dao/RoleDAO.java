package com.bionic.university.dao;

import com.bionic.university.entity.Role;
import com.bionic.university.interceptor.TxInterceptorBinding;

@TxInterceptorBinding
public class RoleDAO extends AbstractDAO<Role> {
    public RoleDAO() {
        super(Role.class);
    }
}
