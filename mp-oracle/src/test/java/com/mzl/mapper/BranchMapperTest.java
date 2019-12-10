package com.mzl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mzl.pojo.Branch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BranchMapperTest
 * @Description: TODO
 * @Author may
 * @Date 2019/11/29 11:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BranchMapperTest {

    @Autowired
    private BranchMapper branchMapper;

    /**
     * 插入一条记录
     * @param entity 实体对象
     * @return 插入成功记录数
     */
    @Test
    public void insert() {
        Branch branch = new Branch();
        branch.setBranchName("测试数据");
        branch.setRemark("不是数据库字段");
        int insert = branchMapper.insert(branch);
    }

    /**
     * 根据 ID 删除
     * @param id 主键ID
     * @return 删除成功记录数
     */
    @Test
    public void  deleteById(){
        int i = branchMapper.deleteById(10045L);
    }

    /**
     * 根据 columnMap 条件，删除记录
     * @param columnMap 表字段 map 对象
     * @return 删除成功记录数
     */
    @Test
    public void deleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("BRANCH_ID", 10047);
        //map.put("DELETE_MARK", 0);
        int i = branchMapper.deleteByMap(map);
    }

    /**
     * 根据 entity 条件，删除记录
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @return 删除成功记录数
     */
    @Test
    public void delete(){
        QueryWrapper<Branch> wrapper = Wrappers.query();
        wrapper.eq("BRANCH_ID", 10047);
        branchMapper.delete(wrapper);
    }

    /**
     * 删除（根据ID 批量删除）
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return 删除成功记录数
     */
    @Test
    public void deleteBatchIds(){
        List<Integer> idList = Arrays.asList(10048, 10049);
        int i = branchMapper.deleteBatchIds(idList);
    }

    /**
     * 根据 ID 修改
     * @param entity 实体对象
     * @return 修改成功记录数
     */
    @Test
    public void updateById() {
        Branch branch = new Branch();
        branch.setBranchId(10045L);
        branch.setBranchContacts("小木子");
        branchMapper.updateById(branch);//根据id进行更新，没有传值的属性就不会更新
    }

    /**
     * 根据 whereEntity 条件，更新记录
     * @param entity        实体对象 (set 条件值,可为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     * @return 修改成功记录数
     */
    @Test
    public void update1() {
        QueryWrapper<Branch> wrapper = Wrappers.query();
        wrapper.eq("BRANCH_ID", 10045);
        Branch branch = new Branch();
        branch.setBranchContacts("小木木");
        branchMapper.update(branch, wrapper);//根据id进行更新，没有传值的属性就不会更新
    }
}
