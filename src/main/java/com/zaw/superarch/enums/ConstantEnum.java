package com.zaw.superarch.enums;

/**
 * 常量枚举
 *
 * @author zhangaiwen
 */
public enum ConstantEnum {

    INT_CONSTANT,

    LONG_CONSTANT_ENUM;

    private void get(){
        // 获取枚举的值
        ConstantEnum[] values = ConstantEnum.values();
    }

}
