package com.loohos.stategrid.common.controller;

import com.loohos.stategrid.common.pojo.Token;
import com.loohos.stategrid.common.result.Result;
import com.loohos.stategrid.common.result.ResultFactory;
import com.loohos.stategrid.common.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public Result add(@Param("tokenName") String tokenName,
                      @Param("introduction") String introduction) {
        Token token = new Token(tokenName, introduction);
        tokenService.save(token);
        return ResultFactory.buildSuccessResult("添加权限成功");
    }

    @GetMapping("/getList")
    public Result getList(){
        List<Token> tokens = tokenService.getScrollData().getResultList();
        return ResultFactory.buildSuccessResult(tokens);
    }

    @PostMapping("/delete")
    public Result delete(@Param("id") int id) {
        tokenService.delete(id);
        return ResultFactory.buildSuccessResult("删除权限成功");
    }
}
