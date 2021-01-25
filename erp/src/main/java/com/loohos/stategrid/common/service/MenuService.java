package com.loohos.stategrid.common.service;

import com.loohos.stategrid.common.dao.DAO;
import com.loohos.stategrid.common.pojo.Menu;

import java.util.List;

public interface MenuService extends DAO<Menu> {
    List<Menu> getByTokenId(int tokenId);

    int getMaxId();
}
