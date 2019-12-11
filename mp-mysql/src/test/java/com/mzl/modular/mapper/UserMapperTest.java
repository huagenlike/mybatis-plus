package com.mzl.modular.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mzl.modular.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserMapperTest
 * @Description: TODO
 * @Author may
 * @Date 2019/12/11 14:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    // 插入一条记录
    @Test
    public void insert(){
        User user = new User();
        user.setUserName("王小强");
        user.setUserAge(19);
        userMapper.insert(user);
    }

    // 根据 entity 条件，删除记录
    @Test
    public void delete(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_ID", 11);
        userMapper.delete(queryWrapper);
    }

    // 删除（根据ID 批量删除）
    @Test
    public void deleteBatchIds(){
        List<Integer> list = Arrays.asList(4, 5);
        userMapper.deleteBatchIds(list);
    }

    // 根据 ID 删除
    @Test
    public void deleteById(){
        userMapper.deleteById(3);
    }

    // 根据 columnMap 条件，删除记录
    @Test
    public void deleteByMap(){
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("USER_ID", 2);
        userMapper.deleteByMap(columnMap);
    }

    // 根据 whereEntity 条件，更新记录
    @Test
    public void update(){
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.ge("USER_ID", 10);
        int rs = userMapper.update(user, updateWrapper);
    }
    // 根据 ID 修改
    @Test
    public void updateById(){
        User user = new User();
        user.setUserId(14);
        user.setUpdateBy("may");
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }
}
