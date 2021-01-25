package com.loohos.stategrid.common.controller;

import com.loohos.stategrid.common.pojo.Token;
import com.loohos.stategrid.common.pojo.User;
import com.loohos.stategrid.common.result.Result;
import com.loohos.stategrid.common.result.ResultCode;
import com.loohos.stategrid.common.result.ResultFactory;
import com.loohos.stategrid.common.service.TokenService;
import com.loohos.stategrid.common.service.UserService;
import com.loohos.stategrid.common.utils.HttpClientUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/register")
    public Result register(@RequestBody User user){
        String msg = "";
        User userExisted = userService.getUserByName(user.getUsername());
        if (userExisted != null) {
            msg = "用户名已被使用";
            return ResultFactory.buildFailResult(msg);
        }
        //生成默认16位的salt
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        //设置hash算法迭代次数
        int hashedTimes = 2;
        String encodedPassword = new SimpleHash("md5", user.getPassword(), salt, hashedTimes).toString();
        //存储salt， 和编码后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        user.setCreatedDate(new Date());
//        Token token = tokenService.getIdByName(tokenName);
        userService.save(user);
        msg = "用户注册成功！";
        return ResultFactory.buildSuccessResult(msg);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        String userName = user.getUsername();
        String password = user.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        try {
            subject.login(usernamePasswordToken);
            User u = userService.getUserByName(userName);
            Token token = tokenService.find(u.getTokenId());
            return ResultFactory.buildResult(ResultCode.LOGIN_SUCCESS, "登陆成功", token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("登录验证失败");
        }
    }

    @GetMapping("/info")
    public Result info(String token){
        Token token1 = tokenService.getByName(token);
        return ResultFactory.buildSuccessResult(token1);
    }

    @GetMapping("/logout")
    public Result logout(String token){
        System.out.println(token);
        return ResultFactory.buildSuccessResult("登出成功");
    }

    @GetMapping("/getList")
    public Result getList(){
        List<User> users = userService.getScrollData().getResultList();
        return ResultFactory.buildSuccessResult(users);
    }

    @PostMapping("/delete")
    public Result delete(@Param("id") int id){
        userService.delete(id);
        return ResultFactory.buildSuccessResult("删除用户成功");
    }
}
