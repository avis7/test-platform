package com.bionic.university.dao;

import com.bionic.university.entity.Answer;
import com.bionic.university.interceptor.TxInterceptorBinding;

/**
 * Created by c266 on 28.07.2015.
 */
@TxInterceptorBinding
public class AnswerDAO extends AbstractDAO<Answer> {
    public AnswerDAO() {
        super(Answer.class);
    }
}
