package com.bionic.university.dao;

import com.bionic.university.entity.UserAnswer;
import com.bionic.university.interceptor.TxInterceptorBinding;

/**
 * Created by Olexandr on 8/1/2015.
 */
@TxInterceptorBinding
public class UserAnswerDAO extends AbstractDAO<UserAnswer> {
    public UserAnswerDAO() {
        super(UserAnswer.class);
    }
}
