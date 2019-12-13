package com.mzl.modular.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzl.core.common.enums.SexEnum;
import com.mzl.core.common.utils.FunctionCastUtil;
import com.mzl.modular.entity.User;
import com.mzl.modular.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
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

    @Autowired
    private IUserService userService;

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

        queryWrapper.eq(true, "USER_ID", 1);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
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
     * orderByAsc ORDER BY 字段, ... ASC，orderByDesc 排序：ORDER BY 字段, ... DESC，orderBy ORDER BY 字段, ...
     * condition: 为true则使用 groupBy 的条件，为false则不使用
     * isAsc: false 为 DESC，true 为 ASC
     * column: 字段 或 Entity::getId
     */
    @Test
    public void orderBy() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().inSql(User::getUserId, "select user_id from t_user where user_id > 10");
        //queryWrapper.orderBy(true, true, "user_Id");
        //queryWrapper.orderByAsc("user_Id");
        queryWrapper.orderByDesc("user_Id");
        //queryWrapper.orderByDesc("user_Id", "user_sex");
        List<User> list = userMapper.selectList(queryWrapper);
    }

    /**
     * HAVING ( sql语句 )
     * HAVING语句通常与GROUP BY语句联合使用,用来过滤由GROUP BY语句返回的记录集。 HAVING语句的存在弥补了WHERE关键字
     * condition: 为true则使用 groupBy 的条件，为false则不使用
     * sqlHaving:
     * params:
     */
    @Test
    public void having() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.groupBy("user_sex").having("sum(user_age) > 10");
        queryWrapper.groupBy("user_sex").having("sum(user_age) > {0}", 20);
        List<User> list = userMapper.selectList(queryWrapper);
    }

    /**
     * or 拼接 OR，and AND 嵌套
     * condition: 为true则使用 or 的条件，为false则不使用
     * 主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)
     */
    @Test
    public void or() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // WHERE (user_sex = ? OR user_phone LIKE ?)
        queryWrapper.eq("user_sex", "1").or().likeLeft("user_phone", "150");
        // OR 嵌套
        // WHERE (user_sex = ? OR ( (user_email IS NOT NULL AND user_phone LIKE ?) ))
        //queryWrapper.eq("user_sex", "1").or(i -> i.isNotNull("user_email").likeLeft("user_phone", "150"));
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * nested 正常嵌套 不带 AND 或者 OR
     * condition: 为true则使用 nested 的条件，为false则不使用
     */
    @Test
    public void nested() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //WHERE ( (user_id >= ? AND user_sex = ?) )
        queryWrapper.nested(i -> i.ge("user_id", 10).eq("user_sex", "1"));
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * apply 拼接 sql
     * 该方法可用于数据库函数 动态入参的params对应前面applySql内部的{index}部分.这样是不会有sql注入风险的,反之会有!
     * condition: 为true则使用 apply 的条件，为false则不使用
     * applySql: sql
     * params: 参数
     */
    @Test
    public void apply() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.apply("user_id = 1");
        // WHERE (date_format(create_time,'%Y-%m-%d') = '2019-12-11')
        //queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = '2019-12-11'");
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-12-11");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * last 无视优化规则直接拼接到 sql 的最后
     * 只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
     * condition: 为true则使用 last 的条件，为false则不使用
     * lastSql: sql
     */
    @Test
    public void last() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-12-11");
        queryWrapper.last("limit 1");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * exists 拼接 EXISTS ( sql语句 )，notExists 拼接 NOT EXISTS ( sql语句 )
     * condition: 为true则使用 exists 的条件，为false则不使用
     * notExistsSql: sql
     */
    @Test
    public void exists() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //SELECT user_id,user_name,user_age,user_sex,user_email,user_phone,create_time,create_by,update_time,update_by FROM t_user WHERE (user_id >= ? AND EXISTS (select user_id from t_user where user_age > 25))
        queryWrapper.ge("user_id", 10).exists("select user_id from t_user where user_age > 25");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * QueryWrapper
     * 继承自 AbstractWrapper ,自身的内部属性 entity 也用于生成 where 条件
     * 及 LambdaQueryWrapper, 可以通过 new QueryWrapper().lambda() 方法获取
     * 以上方分法为两类.
     * 第二类方法为:过滤查询字段(主键除外),入参不包含 class 的调用前需要wrapper内的entity属性有值! 这两类方法重复调用以最后一次为准
     */
    @Test
    public void queryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // SELECT user_id,user_name FROM t_user
        //queryWrapper.select("user_id", "user_name");
        queryWrapper.select(i -> i.getProperty().startsWith("userSex"));
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 继承自 AbstractWrapper ,自身的内部属性 entity 也用于生成 where 条件
     * 及 LambdaUpdateWrapper, 可以通过 new UpdateWrapper().lambda() 方法获取!
     */
    @Test
    public void updateWrapper() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("update_time", LocalDateTime.now()).setSql("update_by='lucy'");
        //UPDATE t_user SET update_time=?,update_by='may'
        //userMapper.update(new User(), updateWrapper);

        //将实体类user中的值 更新到条件数据内
        //UPDATE t_user SET user_name=?, user_age=?, user_sex=?, user_email=?, user_phone=?, create_time=?, create_by=?, update_time=?, update_by=?, update_time=?,update_by='lucy'
        User user = userMapper.selectById(1);
        //userMapper.update(user, updateWrapper);

        user.setUserName("李娟");
        user.setUserSex(SexEnum.FEMALE);
        user.setUserAge(20);
        updateWrapper.eq("user_id", 1);
        // UPDATE t_user SET user_name=?, user_age=?, user_sex=?, user_email=?, user_phone=?, create_time=?, create_by=?, update_time=?, update_by=?, update_time=?,update_by='lucy' WHERE (user_id = ?)
        userMapper.update(user, updateWrapper);
    }

    /**
     * 在使用了mybatis-plus之后, 自定义SQL的同时也想使用Wrapper的便利应该怎么办？ 在mybatis-plus版本3.0.7得到了完美解决 版本需要大于或等于3.0.7, 以下两种方案取其一即可
     * 方案一，在mapper.java的方法，用注解写sql
     * 方案二，在mapper.xml文件中，写对应的sql
     */
    @Test
    public void wrapper() {
        Wrapper<User> wrapper =  (Wrappers.<User>lambdaQuery().eq(User::getUserId, 1));
        // select * from t_user WHERE (user_id = ?)
        List<User> list = userService.getAll(wrapper);
        list.forEach(System.out::println);
    }

    /**
     * XML 自定义分页
     */
    @Test
    public void selectPageVo() {
        Page page = new Page();
        page.setPages(2);
        page.setSize(1);
        IPage<User> iPage = userService.selectPageVo(page, "1");
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
