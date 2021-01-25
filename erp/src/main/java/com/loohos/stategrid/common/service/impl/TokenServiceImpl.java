package com.loohos.stategrid.common.service.impl;

import com.loohos.stategrid.common.dao.DaoSupport;
import com.loohos.stategrid.common.pojo.Token;
import com.loohos.stategrid.common.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Service
@Transactional
@SuppressWarnings("JpaQlInspection")
public class TokenServiceImpl extends DaoSupport<Token> implements TokenService {
    @Override
    public Token getByName(String tokenName) {
        Query query = em.createQuery("select o from Token o where o.tokenName = ?1");
        query.setParameter(1, tokenName);
        try {
            return (Token) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
