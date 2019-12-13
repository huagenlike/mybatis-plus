package com.mzl.core.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @ClassName SexEnum
 * @Description: 枚举属性，实现 IEnum 接口如下
 * @Author may
 * @Date 2019/12/13 16:06
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements IEnum<Boolean> {

    //MALE(0, "男"), FEMALE(1, "女");
    //MySQL中tinyint(1)对应Java中的boolean类型,非0为true，0为false。
    MALE(false, "男"), FEMALE(true, "女");

    private Boolean value;
    private String desc;

    SexEnum(Boolean value, String desc){
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
