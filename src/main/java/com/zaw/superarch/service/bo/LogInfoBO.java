package com.zaw.superarch.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一日志对象
 *
 * @author zhangaiwen
 */
@AllArgsConstructor
@Data
public class LogInfoBO {

    /** 方法名 */
    private String methodName;

    /** 方法描述 */
    private String annoDesc;
}
