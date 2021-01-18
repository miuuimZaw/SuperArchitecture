package com.zaw.superarch.controller.resp;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回对象(通用)
 *
 * @author zhangaiwen
 */
@NoArgsConstructor
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 4645418252804366040L;

    private Integer code = 200;

    private String msg = "success";

    private T data;

    private Map<String, String> enumVal = new HashMap<>();

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(-1, msg);
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result<>(-1, msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(Integer code, String msg, T data) {
        Result<T> result = new Result<>(code, msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null, null);
    }

    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        if (StringUtils.isNotBlank(msg)) {
            result.setMsg(msg);
            result.setCode(200);
        }
        result.setData(data);
        return result;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }
}
