package com.loohos.stategrid.common.controller;

import com.loohos.stategrid.common.pojo.Menu;
import com.loohos.stategrid.common.pojo.Token;
import com.loohos.stategrid.common.result.Result;
import com.loohos.stategrid.common.result.ResultFactory;
import com.loohos.stategrid.common.service.MenuService;
import com.loohos.stategrid.common.service.TokenService;
import com.loohos.stategrid.common.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Result add(@Param("menuName") String menuName,
                      @Param("srvName") String srvName,
                      @Param("name") String name,
                      @Param("tokenId") int tokenId){
        Menu menu = new Menu(menuName, srvName, name, tokenId);
        menuService.save(menu);
        return ResultFactory.buildSuccessResult("添加菜单 " + menu.getSrvName());
    }

    @GetMapping("/get")
    public Result get(String token){
        //通过token获取权限对应的菜单列表
        Token token1 = tokenService.getByName(token);
        List<Menu> menus = menuService.getByTokenId(token1.getId());
        return ResultFactory.buildSuccessResult(menus);
    }

    @GetMapping("/getList")
    public Result getList(){
        List<Menu> menus = menuService.getScrollData().getResultList();
        return ResultFactory.buildSuccessResult(menus);
    }

    @PostMapping("/delete")
    public Result delete(@Param("id") int id) {
        menuService.delete(id);
        return ResultFactory.buildSuccessResult("删除菜单成功.");
    }
}
