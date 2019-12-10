package com.mzl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzl.pojo.Branch;
import org.junit.Assert;
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
 * @ClassName RetrieveTest
 * @Description: TODO
 * @Author may
 * @Date 2019/11/29 15:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RetrieveTest {

    @Autowired
    private BranchMapper branchMapper;

    @Test
    public void select() {
        List<Branch> branchList = branchMapper.selectList(null);
        /**
         * 断言
         * 断言是为了方便调试程序，并不是发布程序的组成部分。
         *
         * assert格式
         * （1）assert [boolean 表达式]
         *      如果[boolean表达式]为true，则程序继续执行。
         *      如果为false，则程序抛出AssertionError，并终止执行。
         * （2）assert[boolean 表达式 : 错误表达式 （日志）]
         *      如果[boolean表达式]为true，则程序继续执行。
         *      如果为false，则程序抛出java.lang.AssertionError，输出[错误信息]。
         */
        Assert.assertEquals(9, branchList.size());
        branchList.forEach(System.out::println);
    }

    /**
     * 根据 ID 查询
     * @param id 主键ID
     * @return 实体
     */
    @Test
    public void selectById() {
        Branch branch = branchMapper.selectById(10000);
        System.out.println(branch);
    }

    /**
     * 查询（根据ID 批量查询）
     * @param idList 主键ID列表(不能为 null 以及 empty)
     * @return 实体集合
     */
    @Test
    public void selectBatchIds() {
        List<Integer> idList = Arrays.asList(10000, 10180, 10222);
        List<Branch> branchList = branchMapper.selectBatchIds(idList);
        branchList.forEach(System.out::println);
    }

    /**
     * 查询（根据 columnMap 条件）
     * @param columnMap 表字段 map 对象
     * @return 实体集合
     */
    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("DELETE_MARK", 1);
        //key应该是表的字段，而不是实体类的属性
        //map.put("branchName", "北京倍格·本源");
        List<Branch> branchList = branchMapper.selectByMap(map);
        branchList.forEach(System.out::println);
    }

    /**
     * 根据 entity 条件，查询一条记录
     * @param queryWrapper 实体对象
     * @return 实体
     * 如果逻辑非唯一该方法不会自动替您 limit 1 你需要 wrapper.last("limit 1") 设置唯一性。
     */
    @Test
    public void selectOne() {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        queryWrapper.eq("DELETE_MARK", 1);
        Branch branch = branchMapper.selectOne(queryWrapper);
        System.out.println(branch);
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     * @param queryWrapper 实体对象
     * @return 满足条件记录数
     */
    @Test
    public void selectCount(){
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        queryWrapper.eq("DELETE_MARK", 1);
        Integer count = branchMapper.selectCount(queryWrapper);
        System.out.println(count);
    }

    /**
     * 根据 entity 条件，查询全部记录
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 实体集合
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        //QueryWrapper<Branch> query = Wrappers.<Branch>query();
        queryWrapper.like("DELETE_MARK", "安").eq("BRANCH_CONTACTS_MOBILE", "13709148593");
        List<Branch> branchList = branchMapper.selectList(queryWrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 字段映射对象 Map 集合
     */
    @Test
    public void selectMaps(){
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        queryWrapper.eq("DELETE_MARK", 1);
        List<Map<String, Object>> maps = branchMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     * 注意： 只返回第一个字段的值
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 字段映射对象集合
     */
    @Test
    public void selectObjs(){
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        queryWrapper.eq("DELETE_MARK", 1);
        List<Object> objects = branchMapper.selectObjs(queryWrapper);
        objects.forEach(System.out::println);
    }

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     * @return 实体分页对象
     */
    @Test
    public void selectPage(){
        Page<Branch> page = new Page<>();
        page.setPages(1);
        page.setSize(5);

        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        queryWrapper.eq("DELETE_MARK", 1);

        IPage<Branch> branchIPage = branchMapper.selectPage(page, queryWrapper);
        System.out.println(branchIPage);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作类
     * @return 字段映射对象 Map 分页对象
     */
    @Test
    public void selectMapsPage(){
        Page<Branch> page = new Page<>();
        page.setPages(1);
        page.setSize(5);

        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        queryWrapper.eq("DELETE_MARK", 1);
        //IPage<Map<String, Object>> mapIPage = branchMapper.selectMapsPage(page, queryWrapper);
        //System.out.println(mapIPage);
    }

    /**
     * WHERE BRANCH_NAME like '%安%' and BRANCH_ID between 10000 AND 20000 AND BRANCH_CONTACTS_MOBILE IS NOT NULL
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        //QueryWrapper<Branch> query = Wrappers.<Branch>query();
        queryWrapper.like("BRANCH_NAME", "安").between("BRANCH_ID", 10000, 20000).isNotNull("BRANCH_CONTACTS_MOBILE");
        List<Branch> branchList = branchMapper.selectList(queryWrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * WHERE BRANCH_NAME like '%西安' and BRANCH_ID >= 10000 ORDER BY BRANCH_CONTACTS_MOBILE DESC,BRANCH_ID ASC
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        //QueryWrapper<Branch> query = Wrappers.<Branch>query();
        queryWrapper.likeLeft("BRANCH_NAME", "西安").or().ge("BRANCH_ID", 10000).orderByDesc("BRANCH_CONTACTS_MOBILE").orderByAsc("BRANCH_ID");
        List<Branch> branchList = branchMapper.selectList(queryWrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * to_date('2019-07-12 00:00:00', 'yyyy-MM-dd hh24:mi:ss') = LAST_UPDATED_DATE and branch_id in(select BRANCH_ID from BIG_BRANCH where BRANCH_NAME like '%本源')
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        //QueryWrapper<Branch> query = Wrappers.<Branch>query();
        queryWrapper.apply("to_date('2019-07-12 00:00:00', 'yyyy-MM-dd hh24:mi:ss') = LAST_UPDATED_DATE")
                .inSql("BRANCH_ID", "select BRANCH_ID from BIG_BRANCH where BRANCH_NAME like '%本源'");
        List<Branch> branchList = branchMapper.selectList(queryWrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * WHERE (BRANCH_NAME LIKE '本源%' AND ( (BRANCH_ID < 20000 OR BRANCH_CONTACTS_MOBILE IS NOT NULL) ))
     * qw 在这里代表的是 queryWrapper 这个对象,lamdab表达式
     */
    @Test
    public void selectByWrapper5() {
        QueryWrapper<Branch> queryWrapper = new QueryWrapper<Branch>();
        //QueryWrapper<Branch> query = Wrappers.<Branch>query();
        queryWrapper.likeRight("BRANCH_NAME", "本源").and(qw -> qw.lt("BRANCH_ID",20000).or().isNotNull("BRANCH_CONTACTS_MOBILE"));
        List<Branch> branchList = branchMapper.selectList(queryWrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * WHERE (BRANCH_NAME LIKE '本源%' OR ( (BRANCH_ID BETWEEN 10000 AND 20000 AND BRANCH_CONTACTS_MOBILE IS NOT NULL) ))
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<Branch> wrapper = Wrappers.query();
        wrapper.likeRight("BRANCH_NAME", "本源").or(
                qw -> qw.between("BRANCH_ID", 10000, 20000).isNotNull("BRANCH_CONTACTS_MOBILE")
        );
        List<Branch> branchList = branchMapper.selectList(wrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * WHERE ( BRANCH_ID < 20000 OR BRANCH_CONTACTS_MOBILE IS NOT NULL ) AND BRANCH_NAME LIKE '本源%'
     */
    @Test
    public void selectByWrapper7() {
        QueryWrapper<Branch> wrapper = Wrappers.query();
        wrapper.nested(qw -> qw.lt("BRANCH_ID", 20000).or().isNotNull("BRANCH_CONTACTS_MOBILE"))
                .likeRight("BRANCH_NAME", "本源");
        List<Branch> branchList = branchMapper.selectList(wrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * 查询年龄为10221, 10222, 10180, 10000
     * WHERE BRANCH_ID IN (?,?,?)
     */
    @Test
    public void selectByWrapper8() {
        QueryWrapper<Branch> wrapper = Wrappers.query();
        wrapper.in("BRANCH_ID", Arrays.asList(10221, 10222, 10180, 10000));
        List<Branch> branchList = branchMapper.selectList(wrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * 查询一条数据 -- MySQL方法
     * limit 1
     */
    @Test
    public void selectByWrapper9() {
        QueryWrapper<Branch> wrapper = Wrappers.query();
        wrapper.in("BRANCH_ID", Arrays.asList(10221, 10222, 10180, 10000)).last("limit 1");
        List<Branch> branchList = branchMapper.selectList(wrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * 只查"BRANCH_ID", "BRANCH_NAME" 字段
     * SELECT BRANCH_ID,BRANCH_NAME FROM BIG_BRANCH WHERE (BRANCH_ID IN (?,?,?,?))
     */
    @Test
    public void selectByWrapper8Supper() {
        QueryWrapper<Branch> wrapper = Wrappers.query();
        wrapper.select("BRANCH_ID", "BRANCH_NAME").in("BRANCH_ID", Arrays.asList(10221, 10222, 10180, 10000));
        List<Branch> branchList = branchMapper.selectList(wrapper);
        branchList.forEach(System.out::println);
    }

    /**
     * 查不包含"BRANCH_ID", "BRANCH_NAME" 的字段
     * SELECT BRANCH_ID,branch_contacts,branch_introduction,city,department_code,branch_address,org_code,relevance_org_code,branch_contacts_email,rental_tax_rate,branch_bank,bank_account,last_updated_by,foreground_show,branch_name,property_tax_rate,split_ratio,service_tax_rate,branch_code,branch_company,delete_mark,last_updated_date,branch_contacts_mobile,created_date,created_by,branch_relationname FROM BIG_BRANCH WHERE (BRANCH_ID IN (?,?,?,?))
     *
     *  --需要注意打印的sql，字段是大写还是小写，否则不会隐藏
     * SELECT BRANCH_ID,branch_contacts,branch_introduction,city,department_code,branch_address,org_code,relevance_org_code,branch_contacts_email,rental_tax_rate,branch_bank,bank_account,last_updated_by,foreground_show,branch_name,branch_code,created_by,branch_relationname FROM BIG_BRANCH WHERE (BRANCH_ID IN (?,?,?,?))
     */
    @Test
    public void selectByWrapper8Supper1() {
        QueryWrapper<Branch> wrapper = Wrappers.query();

        wrapper.select(Branch.class, info ->
                !info.getColumn().equals("property_tax_rate") &&
                !info.getColumn().equals("split_ratio") &&
                !info.getColumn().equals("service_tax_rate") &&
                !info.getColumn().equals("branch_company") &&
                !info.getColumn().equals("delete_mark") &&
                !info.getColumn().equals("last_updated_date") &&
                !info.getColumn().equals("branch_contacts_mobile") &&
                !info.getColumn().equals("created_date")).in("BRANCH_ID", Arrays.asList(10221, 10222, 10180, 10000));
        List<Branch> branchList = branchMapper.selectList(wrapper);
        branchList.forEach(System.out::println);
    }
}
