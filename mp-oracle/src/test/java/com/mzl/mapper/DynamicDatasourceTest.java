package com.mzl.mapper;

import com.mzl.pojo.Branch;
import com.mzl.pojo.Company;
import com.mzl.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: lhg
 * @date: Created in 2020/9/2 9:31
 * @version:
 * @modified By:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DynamicDatasourceTest {

    @Autowired
    private BranchMapper branchMapper;

    @Autowired
    private CompanyService companyService;

    /**
     * @description 查询倍格租赁系统数据库
     * @author lhg
     * @date 2020/9/2 10:29
     * @return
     */
    @Test
    public void selectById() {
        Branch branch = branchMapper.selectById(10160);
        System.out.println(branch);
    }

    /**
     * @description 查询企业工商数据库
     * @author lhg
     * @date 2020/9/2 10:29
     * @return
     */
    @Test
    public void selectByCompanyId() {
        Company company = companyService.getById(1071);
        System.out.println(company);
    }

}
