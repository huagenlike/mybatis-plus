package com.mzl.modular.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzl.core.common.utils.FunctionCastUtil;
import com.mzl.modular.entity.User;
import com.mzl.modular.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * @ClassName WrapperTest
 * @Description: TODO
 * @Author may
 * @Date 2019/12/11 15:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * allEq
     * @param condition 为true则使用 allEq 的条件，为false则不使用
     * @param params key为数据库字段名,value为字段值
     * @param null2IsNull 为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
     */
    @Test
    public void allEq() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_sex", 1);
        map.put("user_email", null);

        //allEq1Demo1(map);
        //allEq1Demo2(map, false);
        //allEq1Demo3(true, map, false);

        //SELECT user_id,user_name,user_age,user_sex,user_email,user_phone,create_time,create_by,update_time,update_by FROM t_user WHERE (user_sex = ?)
        //key为数据库字段名,value为字段值，其中 "user_sex" 是和 map的key进行比较。
        BiPredicate<String, Object> filter = (x, y) -> x .equals("user_sex");
        //allEq1DemoOne(filter, map);
        //allEq1DemoTwo(filter, map, false);
        allEq1DemoThree(true, filter, map, false);
    }

    /**
     * allEq 方法1
     * @param params key为数据库字段名,value为字段值
     */
    public void allEq1Demo1(Map<String, Object> params){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(params);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * allEq 方法2
     * @param params key为数据库字段名,value为字段值
     * @param null2IsNull 为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
     */
    public void allEq1Demo2(Map<String, Object> params, boolean null2IsNull){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(params, null2IsNull);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * allEq 方法3
     * @param condition 为true则使用 allEq 的条件，为false则不使用
     * @param params key为数据库字段名,value为字段值
     * @param null2IsNull 为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
     */
    public void allEq1Demo3(boolean condition, Map<String, Object> params, boolean null2IsNull){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(condition, params, null2IsNull);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * allEq 过滤函数 方法一
     * @param filter x是字段,y是对应的值,使用Lambda表达式进行条件的校验
     * @param params key为数据库字段名,value为字段值
     */
    public void allEq1DemoOne(BiPredicate<String, Object> filter, Map<String, Object> params){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(filter, params);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * allEq 过滤函数 方法二
     * @param filter
     * @param params
     * @param null2IsNull
     */
    public void allEq1DemoTwo(BiPredicate<String, Object> filter, Map<String, Object> params, boolean null2IsNull){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(filter, params, null2IsNull);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * allEq 过滤函数 方法三
     * @param condition
     * @param filter
     * @param params
     * @param null2IsNull
     */
    public void allEq1DemoThree(boolean condition, BiPredicate<String, Object> filter, Map<String, Object> params, boolean null2IsNull){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(condition, filter, params, null2IsNull);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
