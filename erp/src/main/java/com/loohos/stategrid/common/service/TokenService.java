package com.loohos.stategrid.common.service;

import com.loohos.stategrid.common.dao.DAO;
import com.loohos.stategrid.common.pojo.Token;

public interface TokenService extends DAO<Token> {
    Token getByName(String tokenName);
}
