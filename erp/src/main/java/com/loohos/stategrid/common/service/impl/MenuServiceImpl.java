package com.loohos.stategrid.common.service.impl;

import com.loohos.stategrid.common.dao.DaoSupport;
import com.loohos.stategrid.common.pojo.Menu;
import com.loohos.stategrid.common.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@SuppressWarnings("JpaQlInspection")
public class MenuServiceImpl extends DaoSupport<Menu> implements MenuService {
    @Override
    public List<Menu> getByTokenId(int tokenId) {
        Query query = em.createQuery("select o from Menu o where o.tokenId = ?1");
        query.setParameter(1, tokenId);
        try {
            if(query.getResultList().size() > 0) {
                return query.getResultList();
            } else {
                System.out.println("此权限无对应菜单.");
                return new ArrayList<>();
            }
        } catch (Exception exception) {
            System.out.println("此权限无对应菜单.");
            return new ArrayList<>();
        }
    }

    @Override
    public int getMaxId() {
        Query query = em.createQuery("select max(o.id) from Menu o");
        try {
            return (int) query.getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }
}
