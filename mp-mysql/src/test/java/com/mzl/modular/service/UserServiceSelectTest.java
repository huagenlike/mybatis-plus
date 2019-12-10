package com.mzl.modular.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mzl.modular.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName UserServiceTest
 * @Description: TODO
 * @Author may
 * @Date 2019/12/10 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceSelectTest {

    @Autowired
    private IUserService userService;

    // 根据 ID 查询
    @Test
    public void getById(){
        User user = userService.getById(1);
        System.err.println(user);
    }

    // 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
    @Test
    public void getOne(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("USER_ID", 2).orderByDesc("USER_ID").last("limit 1");
        User user = userService.getOne(queryWrapper);
        System.err.println(user);

    }

    // 根据 Wrapper，查询一条记录
    @Test
    public void getOneOfThrowEx(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("USER_ID", 2).orderByDesc("USER_ID");
        //第二个参数设置 false 的话，不会抛出异常
        User user = userService.getOne(queryWrapper, false);
        //System.err.println(user);
    }

    // 根据 Wrapper，查询一条记录
    @Test
    public void getMap(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("USER_EMAIL", "163").orderByDesc("USER_ID", "USER_SEX");
        Map<String, Object> map = userService.getMap(queryWrapper);
        System.err.println(map);
    }

    // 根据 Wrapper，查询一条记录
    @Test
    public void getObj(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserSex, "0");
        //第二个参数设置 false 的话，不会抛出异常, 默认 true
        //User one = userService.getObj(queryWrapper);
    }
}
