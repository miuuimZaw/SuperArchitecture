package com.zaw.superarch.controller;

import com.zaw.superarch.controller.resp.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author zhangaiwen
 */
@Log4j2
@Api(tags = "主接口")
@RestController
@RequestMapping("main/")
public class MainController {

    @ApiResponses({@ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @ApiOperation(value = "测试接口1", notes = "获取后台字段", responseContainer = "String", response = String.class)
    @GetMapping("test")
    public String test() {
        return "hello ";
    }

    @ApiOperation(value = "【Double】最大值最小值")
    @GetMapping("getDoubleInfo")
    public Result getDoubleMinAndMax() {
        log.info("double max value:{}, min value:{}", Double.MAX_VALUE, Double.MIN_VALUE);
        ArrayList<String> list1 = new ArrayList(); //第一种 情况
        ArrayList list2 = new ArrayList<String>(); //第二种 情况

        return Result.success();
    }
}
