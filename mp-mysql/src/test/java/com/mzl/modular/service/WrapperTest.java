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

import java.util.Arrays;
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
     * condition: 为true则使用 allEq 的条件，为false则不使用
     * params: key为数据库字段名,value为字段值
     * null2IsNull: 为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
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
     * eq 等于 =，ne 不等于 <>，gt 大于 >，ge 大于等于 >=，lt 小于 <，le 小于等于 <=
     * 以上几种方式，对应的方法几乎一样，拿一种举例即可，都通用。
     * condition: 为true则使用 eq 的条件，为false则不使用
     * column: 字段 或 Entity::getId
     * val: 值
     */
    @Test
    public void eq() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //lambda表达式
        //queryWrapper.lambda().eq(User::getUserId, 1);

        //queryWrapper.eq("USER_ID", 1);

        queryWrapper.eq(false, "USER_ID", 1);
        User user = userMapper.selectOne(queryWrapper);
    }

    /**
     * between BETWEEN 值1 AND 值2，notBetween NOT BETWEEN 值1 AND 值2
     * between 和 notBetween 用法相似
     * condition: 为true则使用 eq 的条件，为false则不使用
     * column: 字段或Entity::getId
     * Object: val1
     * Object: val2
     */
    @Test
    public void between() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //lambda表达式
//        queryWrapper.lambda().between(User::getUserId, 1, 20);

        //queryWrapper.between("user_id", 1, 20);

        queryWrapper.between(false, "user_id", 1, 20);
        List<User> list = userMapper.selectList(queryWrapper);
    }

    /**
     * like LIKE '%值%'，notLike NOT LIKE '%值%'，likeLeft LIKE '%值'，likeRight LIKE '值%'
     * 以上几种方式，对应的方法几乎一样，拿一种举例即可，都通用。
     * condition: 为true则使用 eq 的条件，为false则不使用
     * column: 字段 或 Entity::getId
     * val: 值
     */
    @Test
    public void like() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //lambda表达式
        //queryWrapper.lambda().like(User::getUserPhone, 1);

        //queryWrapper.like("user_phone", 1);

        queryWrapper.like(true, "user_phone", 1);
        List<User> list = userMapper.selectList(queryWrapper);
    }

    /**
     * isNull 字段 IS NULL，isNotNull 字段 IS NOT NULL
     * 以上几种方式，对应的方法几乎一样，拿一种举例即可，都通用。
     * condition: 为true则使用 eq 的条件，为false则不使用
     * column: 字段 或 Entity::getId
     */
    @Test
    public void isNull() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //lambda表达式
        //queryWrapper.lambda().isNull(User::getUserPhone);

        //queryWrapper.isNull("user_phone");

        queryWrapper.isNull(true, "user_phone");
        List<User> list = userMapper.selectList(queryWrapper);
    }

    /**
     * in 字段 IN (v0, v1, ...)，notIn 字段 NOT IN (v0, v1, ...)
     * 以上几种方式，对应的方法几乎一样，拿一种举例即可，都通用。
     * condition: 为true则使用 eq 的条件，为false则不使用
     * column: 字段 或 Entity::getId
     * inValue: 可以是list[Arrays.asList(1,12,13)] 也可以是多个参数[1,2,3,4,5]
     */
    @Test
    public void in() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<Integer> ids = Arrays.asList(1,12,13);

        //lambda表达式
        //queryWrapper.lambda().in(User::getUserId, 1, 12, 13);
        queryWrapper.lambda().in(User::getUserId, ids);

//        queryWrapper.in("user_id", 1, 12, 13);

//        queryWrapper.in(true, "user_id", 1, 12, 13);
        List<User> list = userMapper.selectList(queryWrapper);
    }

    /**
     * inSql 字段 IN ( sql语句 )，notInSql 字段 NOT IN ( sql语句 )
     * 以上几种方式，对应的方法几乎一样，拿一种举例即可，都通用。
     * condition: 为true则使用 inSql 的条件，为false则不使用
     * column: 字段 或 Entity::getId
     * inValue: 值
     */
    @Test
    public void inSql() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //lambda表达式
        //queryWrapper.lambda().inSql(User::getUserId, "1, 12, 13");
        queryWrapper.lambda().inSql(User::getUserId, "select user_id from t_user where user_id > 10");

        //queryWrapper.inSql("user_id", "1,12,13");
        //SELECT user_id,user_name,user_age,user_sex,user_email,user_phone,create_time,create_by,update_time,update_by FROM t_user WHERE (user_id IN (?))
        //queryWrapper.inSql(true, "user_id", "1,12,13");
        List<User> list = userMapper.selectList(queryWrapper);
    }

    /**
     * groupBy GROUP BY 字段, ...
     * condition: 为true则使用 groupBy 的条件，为false则不使用
     * column: 字段 或 Entity::getId
     */
    @Test
    public void groupBy() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().inSql(User::getUserId, "select user_id from t_user where user_id > 10");
        queryWrapper.groupBy("user_id", "user_sex");
        List<User> list = userMapper.selectList(queryWrapper);
    }

    /**
     * groupBy GROUP BY 字段, ...
     * condition: 为true则使用 groupBy 的条件，为false则不使用
     * column: 字段 或 Entity::getId
     */
    @Test
    public void orderBy() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().inSql(User::getUserId, "select user_id from t_user where user_id > 10");
        queryWrapper.orderBy(true, false, "user_Id");
        List<User> list = userMapper.selectList(queryWrapper);
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
