package com.mzl.modular.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.mzl.core.common.enums.SexEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer userAge;

    /**
     * 性别：0 男 1女
     */
    private SexEnum userSex;

    public SexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(SexEnum userSex) {
        this.userSex = userSex;
    }

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 电话
     */
    private String userPhone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 逻辑删除注解，以实体上的为准，忽略全局。 即先查找注解再查找全局，都没有则此表没有逻辑删除。
     * 若确需查找删除数据，如老板需要查看历史所有数据的统计汇总信息，请单独手写sql。
     */
    @TableLogic
    private Integer deleted;

}
