package com.zaw.superarch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqttGatewayTest {

    @Resource
    private MqttGateway gateway;

    @Test
    public void sendToMqtt() {
        gateway.sendToMqtt("hello sksksksk","mqtt_topic_1");
    }
}