package com.loohos.stategrid.common.service.impl;

import com.loohos.stategrid.common.dao.DaoSupport;
import com.loohos.stategrid.common.pojo.User;
import com.loohos.stategrid.common.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Service
@Transactional
@SuppressWarnings("JpaQlInspection")
public class UserServiceImpl extends DaoSupport<User> implements UserService {
    @Override
    public User getUserByName(String username) {
        Query query = em.createQuery("select o from User o where o.username = ?1");
        query.setParameter(1, username);
        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
