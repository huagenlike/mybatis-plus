package com.mzl.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 企业工商表
 * </p>
 *
 * @author lhg
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BA_COMPANY")
@KeySequence(value = "COMPANY_ID_SEQ", clazz = Long.class)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "COMPANY_ID", type = IdType.INPUT)
    private Long companyId;


    /**
     * 关联code
     */
    @TableField("COMPANY_CODE")
    private String companyCode;

    /**
     * 公司名称
     */
    @TableField("COMPANY_NAME")
    private String companyName;

    /**
     * 法定代表人类型：
        1 内部人员
        2 外部人员
     */
    @TableField("LEGAL_PERSON_TYPE")
    private String legalPersonType;

    /**
     * 法定代表人关联code
     */
    @TableField("LEGAL_PERSON_CODE")
    private String legalPersonCode;

    /**
     * 法定代表人
     */
    @TableField("LEGAL_PERSON_NAME")
    private String legalPersonName;

    /**
     * 公司英文名字
     */
    @TableField("ENGLISH_COMPANY_NAME")
    private String englishCompanyName;

    /**
     * 年检日期（每年的3、6、9月，仅能选择一个）
     */
    @TableField("ANNUAL_INSPECTION_DATE")
    private String annualInspectionDate;

    /**
     * 注册资本
     */
    @TableField("REGISTERED_CAPITAL")
    private BigDecimal registeredCapital;

    /**
     * 币种：
        RMB 人民币
        USD 美元
        JPY 日元
     */
    @TableField("CURRENCY")
    private String currency;

    /**
     * 企业类型：
     * LLC 有限责任公司
     * JSC 股份有限公司
     * LP 有限合伙企业
     * GP 普通合伙企业
     * PRC个人独资企业
     * SP个体工商户
     */
    @TableField("COMPANY_CATEGORY")
    private String companyCategory;

    /**
     * 营业期限（企业营业结束时间）
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("OPERATING_PERIOD")
    private Date operatingPeriod;

    /**
     * 项目名称
     */
    @TableField("PROJECT_NAME")
    private String projectName;

    /**
     * 注册股数
     */
    @TableField("NUMBER_REGISTERED_SHARES")
    private BigDecimal numberRegisteredShares;

    /**
     * 成立日期（企业营业开始时间）
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("ESTABLISHED_DATE")
    private Date establishedDate;

    /**
     * 统一社会信用代码或公司注册编号
     */
    @TableField("CICOMPANY_CODE")
    private String cicompanyCode;

    /**
     * 注册地址
     */
    @TableField("REGISTERED_ADDRESS")
    private String registeredAddress;

    /**
     * 经营范围
     */
    @TableField("BUSINESS_SCOPE")
    private String businessScope;

    /**
     * 段值
     */
    @TableField("SEGMENT_VALUE")
    private String segmentValue;

    /**
     * 版本
     */
    @TableField("VERSION")
    private String version;

    @TableField(value = "ORG_CODE", fill = FieldFill.INSERT)
    private String orgCode;

    @TableField(value = "DEPARTMENT_CODE", fill = FieldFill.INSERT)
    private String departmentCode;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "CREATED_DATE", fill = FieldFill.INSERT)
    private Date createdDate;

    /**
     * 最后修改时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "LAST_UPDATED_DATE", fill = FieldFill.UPDATE)
    private Date lastUpdatedDate;

    /**
     * 创建人
     */
    @TableField(value = "CREATED_BY", fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 最后修改人
     */
    @TableField(value = "LAST_UPDATED_BY", fill = FieldFill.UPDATE)
    private String lastUpdatedBy;

    /**
     * 状态：
        1 暂存中
        2 审批中
        3 营业
        4 注销
        5 变更中
        0 删除
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建人名称
     */
    @TableField(value = "CREATED_BY_NAME", fill = FieldFill.INSERT)
    private String createdByName;

    /**
     * 修改人名称
     */
    @TableField(value = "LAST_UPDATED_BY_NAME", fill = FieldFill.UPDATE)
    private String lastUpdatedByName;

    /**
     * 所属区域：
     0 境内
     1 境外
     */
    @TableField("COMPANY_REGION")
    private String companyRegion;

    /**
     * 是否内部：
     0 否
     1 是
     */
    @TableField("IS_INTERNAL")
    private String isInternal;
}
