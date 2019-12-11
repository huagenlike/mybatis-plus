package com.mzl.modular.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzl.modular.entity.User;
import org.apache.ibatis.annotations.Param;
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

/**
 * @ClassName UserMapperTest
 * @Description: TODO
 * @Author may
 * @Date 2019/12/11 14:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperSelectTest {

    @Autowired
    private UserMapper userMapper;

    // 根据 ID 查询
    @Test
    public void selectById() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    // 根据 entity 条件，查询一条记录,，如果出现多条记录，则会报错
    @Test
    public void selectOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_sex", 1).last("limit 1");
        userMapper.selectOne(queryWrapper);
    }

    // 查询（根据ID 批量查询）
    @Test
    public void selectBatchIds() {
        List<Integer> ids = Arrays.asList(11, 12, 13);
        List<User> list = userMapper.selectBatchIds(ids);
        list.forEach(System.out::println);
    }

    // 根据 entity 条件，查询全部记录
    @Test
    public void selectList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(User::getUserId, 12);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    // 查询（根据 columnMap 条件）
    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("USER_SEX", "0");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    // 根据 Wrapper 条件，查询全部记录
    @Test
    public void selectMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(User::getUserId, 12);
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        System.out.println(list);
    }

    // 根据 Wrapper 条件，查询全部记录。注意： 只返回第一个字段的值
    @Test
    public void selectObjs() {
        String[] arr = {"user_id"};
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /**
         * orderBy:
         * condition: true为使用排序
         * isAsc: false -> desc, true -> asc
         * columns: 对应数据库字段
         */
        queryWrapper.lambda().ge(User::getUserId, 12).orderBy(true, false, User::getUserId);
        List<Object> list = userMapper.selectObjs(queryWrapper);
        System.out.println(list);
    }

    // 根据 entity 条件，查询全部记录（并翻页）
    @Test
    public void selectPage() {
        Page<User> page = new Page<>();
        page.setSize(2);
        page.setPages(1);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(User::getUserId, 12);

        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        System.out.println(userPage);
    }

    // 根据 Wrapper 条件，查询全部记录（并翻页）
    @Test
    public void selectMapsPage() {
        Page<Map<String, Object>> mapPage = new Page<>();
        mapPage.setSize(2);
        mapPage.setPages(1);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(User::getUserId, 12);
        Page<Map<String, Object>> page = userMapper.selectMapsPage(mapPage, queryWrapper);
        System.out.println(page);
    }

    // 根据 Wrapper 条件，查询总记录数
    @Test
    public void selectCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(User::getUserId, 12);
        userMapper.selectCount(queryWrapper);
    }

}
