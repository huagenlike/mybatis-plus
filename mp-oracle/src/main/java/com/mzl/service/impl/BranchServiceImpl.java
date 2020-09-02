package com.mzl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzl.mapper.BranchMapper;
import com.mzl.pojo.Branch;
import com.mzl.service.BranchService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lhg
 * @date: Created in 2020/9/2 9:57
 * @version:
 * @modified By:
 */
@DS("dataSourceTwo")
@Service
public class BranchServiceImpl extends ServiceImpl<BranchMapper, Branch> implements BranchService {
}
