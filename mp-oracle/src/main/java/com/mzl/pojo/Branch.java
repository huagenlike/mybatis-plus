package com.mzl.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("BIG_BRANCH")
@KeySequence(value = "BRANCH_ID_SEQ", clazz = Long.class)
public class Branch {

    @TableId(value = "BRANCH_ID", type = IdType.INPUT)
    private Long branchId;

    private String branchName;

    private String branchContacts;

    private String branchContactsMobile;

    private String branchIntroduction;

    private String branchContactsEmail;

    private String branchAddress;

    private BigDecimal foregroundShow;

    private BigDecimal deleteMark;

    private Date createdDate;

    private String createdBy;

    private Date lastUpdatedDate;

    private String lastUpdatedBy;

    private String orgCode;

    private String departmentCode;

    private String branchCode;

    private String relevanceOrgCode;

    private String city;

    private String branchRelationname;

    private String branchBank;

    private BigDecimal bankAccount;

    private String branchCompany;

    private BigDecimal splitRatio;

    private BigDecimal rentalTaxRate;

    private BigDecimal serviceTaxRate;

    private BigDecimal propertyTaxRate;

    /**
     * 非数据库字段：
     *  1、@TableField(exist = false)
     *  2、private static String remark;
     *  3、private transient String remark;
     */
    @TableField(exist = false)
    private String remark;

}