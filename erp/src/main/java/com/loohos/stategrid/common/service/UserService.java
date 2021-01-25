package com.loohos.stategrid.common.service;

import com.loohos.stategrid.common.dao.DAO;
import com.loohos.stategrid.common.pojo.User;

public interface UserService extends DAO<User> {

    User getUserByName(String username);
}
