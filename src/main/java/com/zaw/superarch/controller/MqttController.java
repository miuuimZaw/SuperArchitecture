package com.zaw.superarch.controller;

import com.zaw.superarch.service.MqttGateway;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangaiwen
 */
@Api(value = "模拟消息", tags = "MQTT拟接口")
@RestController
@RequestMapping("mqtt")
public class MqttController {

    @Resource
    private MqttGateway mqttGateway;

    @ApiOperation(value = "发送MQTT消息", notes = "用于测试")
    @PostMapping("/sendMsg")
    public String sendMqtt(String sendData) {
        mqttGateway.sendToMqtt(sendData, "hello");
        return "OK";
    }
}
