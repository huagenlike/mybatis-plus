package com.mzl.modular.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mzl.modular.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserServiceTest
 * @Description: TODO
 * @Author may
 * @Date 2019/12/10 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    // 插入一条记录（选择字段，策略插入）
    @Test
    public void save() {
        User user = new User();
        user.setUserName("李华");
        user.setUserAge(31);
        user.setUserEmail("lihua@163.com");
        user.setUserPhone("15010569968");
        userService.save(user);
    }


    // 插入（批量）
    @Test
    public void saveBatch() {
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setUserName("李四");
        user.setUserAge(26);
        user.setUserEmail("lisi@163.com");
        user.setUserPhone("13914226586");

        User user2 = new User();
        user2.setUserName("唐萍");
        user2.setUserAge(32);
        user2.setUserSex("1");
        user2.setUserEmail("tangping@163.com");
        user2.setUserPhone("13610259968");

        list.add(user);
        list.add(user2);

        userService.saveBatch(list);
    }
    /**
     * 插入（批量）
     * int batchSize 插入批次数量
     */
    @Test
    public void saveBatchOfBatchSize() {
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setUserName("王五");
        user.setUserAge(24);
        user.setUserEmail("wangwu@163.com");
        user.setUserPhone("13010256698");

        User user1 = new User();
        user1.setUserName("杨康");
        user1.setUserAge(24);
        user1.setUserEmail("yangkang@163.com");
        user1.setUserPhone("13655986695");

        User user2 = new User();
        user2.setUserName("赵六");
        user2.setUserAge(22);
        user2.setUserEmail("zhaoliu@163.com");
        user2.setUserPhone("13210225689");

        list.add(user);
        list.add(user1);
        list.add(user2);

        userService.saveBatch(list, 1);
    }

    /**
     * TableId 注解存在更新记录，否插入一条记录
     */
    @Test
    public void saveOrUpdate(){
        User user = new User();
        //user.setUserId(1);
        user.setUserName("王思燕");
        user.setUserAge(18);
        user.setUserEmail("wangsiyan@163.com");
        user.setUserPhone("15010557859");
        userService.saveOrUpdate(user);
    }

    /**
     * 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
     * T	        entity	        实体对象
     * Wrapper<T>	updateWrapper	实体对象封装操作类 UpdateWrapper
     */
    @Test
    public void saveOrUpdateOfUpdateWrapper(){
        User user = new User();
        user.setUserName("卢春");
        user.setUserAge(22);
        user.setUserEmail("luchun@163.com");
        user.setUserPhone("13612033365");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //id为10的数据不存在，所以执行的是INSERT，如果存在就是修改user对象中不为null的属性
        queryWrapper.lambda().eq(User::getUserId, 10);
        userService.saveOrUpdate(user, queryWrapper);
    }

    /**
     * 批量修改插入，如果数据存在，则是先查询，在修改。数据不存在，执行插入
     * Collection<T>	entityList	实体对象集合
     */
    @Test
    public void saveOrUpdateBatch(){
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setUserId(1);
        user.setUserAge(31);

        User user1 = new User();
        user1.setUserId(8);
        user1.setUserSex("1");

        User user2 = new User();
        user2.setUserName("李霞");
        user2.setUserAge(20);
        user2.setUserEmail("lixia@163.com");

        list.add(user);
        list.add(user1);
        list.add(user2);
        userService.saveOrUpdateBatch(list);
    }

    /**
     * 批量修改插入
     * Collection<T>	entityList	实体对象集合
     * int	batchSize	插入批次数量
     */
    @Test
    public void saveOrUpdateBatchOfBatchSize(){
        List<User> list = new ArrayList<User>();
        User user = new User();
        user.setUserId(1);
        user.setUserAge(32);

        User user1 = new User();
        user1.setUserId(8);
        user1.setCreateTime(LocalDateTime.now());

        User user2 = new User();
        user2.setUserName("江珊");
        user2.setUserAge(24);
        user2.setUserSex("1");
        user2.setUserPhone("15011425698");
        user2.setUserEmail("jiangshan@163.com");

        list.add(user);
        list.add(user2);
        list.add(user1);
        userService.saveOrUpdateBatch(list, 2);
    }
}
