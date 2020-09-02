package com.mzl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzl.mapper.CompanyMapper;
import com.mzl.pojo.Company;
import com.mzl.service.CompanyService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lhg
 * @date: Created in 2020/9/2 9:57
 * @version:
 * @modified By:
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
}
