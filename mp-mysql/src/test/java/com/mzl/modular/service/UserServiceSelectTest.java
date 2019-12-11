package com.mzl.modular.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzl.modular.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

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

    // 查询所有
    @Test
    public void list(){
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    // 查询列表
    @Test
    public void listByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserSex, "0");
        List<User> list = userService.list(queryWrapper);
        list.forEach(System.out::println);
    }

    // 查询（根据ID 批量查询）
    @Test
    public void listByIds(){
        List<Integer> ids = Arrays.asList(1,2,3,4);
        List<User> list = userService.listByIds(ids);
        list.forEach(System.out::println);
    }

    // 查询（根据 columnMap 条件）
    @Test
    public void listByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("USER_SEX", "0");
        map.put("USER_NAME", "李四");
        List<User> list = userService.listByMap(map);
        list.forEach(System.out::println);
    }

    // 查询所有列表
    @Test
    public void listMaps(){
        List<Map<String, Object>> maps = userService.listMaps();
        System.out.println(maps);
    }

    // 查询列表
    @Test
    public void listMapsByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserSex, "0");
        List<Map<String, Object>> maps = userService.listMaps(queryWrapper);
        System.out.println(maps);
    }

    // 查询全部记录
    @Test
    public void listObjs(){
        List<Object> listObjs = userService.listObjs();
        listObjs.forEach(System.out::println);
    }

    // 查询全部记录
    @Test
    public void listObjsByMapper(){

    }
    // 根据 Wrapper 条件，查询全部记录
    @Test
    public void listObjsByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserSex, "0");
        List<Object> list = userService.listObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    // 根据 Wrapper 条件，查询全部记录
    @Test
    public void listObjsByWrapperAndMapper(){

    }

    // 无条件翻页查询
    @Test
    public void page(){
        Page<User> page = new Page<>();
        page.setSize(2);
        page.setPages(1);
        Page<User> userPage = userService.page(page);
        System.out.println(userPage);
    }

    // 翻页查询
    @Test
    public void pageByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserSex, "0");

        Page<User> page = new Page<>();
        page.setSize(2);
        page.setPages(1);
        Page<User> userPage = userService.page(page, queryWrapper);
        System.out.println(userPage);
    }

    // 无条件翻页查询
    @Test
    public void pageMaps(){
        Page<Map<String, Object>> mapPage = new Page<>();
        mapPage.setPages(1);
        mapPage.setSize(1);
        Page<Map<String, Object>> page = userService.pageMaps(mapPage);
        System.out.println(page);
    }

    // 翻页查询
    @Test
    public void pageMapsByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserSex, "0");

        Page<Map<String, Object>> mapPage = new Page<>();
        mapPage.setPages(1);
        mapPage.setSize(2);
        Page<Map<String, Object>> page = userService.pageMaps(mapPage, queryWrapper);
        System.out.println(page);
    }

    // 查询总记录数
    @Test
    public void count(){
        int count = userService.count();
        System.out.println(count);
    }

    // 根据 Wrapper 条件，查询总记录数
    @Test
    public void countByWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserSex, "0");
        int count = userService.count(queryWrapper);
        System.out.println(count);
    }

    // 链式查询 普通
    @Test
    public void query(){
        User user = userService.query().eq("USER_ID", 1).one();
        System.out.println(user);
    }

    // 链式查询 lambda 式。注意：不支持 Kotlin
    @Test
    public void lambdaQuery(){
        List<User> list = userService.lambdaQuery().eq(User::getUserId, 1).list();
        list.forEach(System.out::println);
    }
}
