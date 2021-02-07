package com.zaw.superarch.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * MQTT网关接口
 *
 * @author zhangaiwen
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    /**
     * 若指定topic,则向指定的主题发送消息;若未指定,使用默认配置的主题
     *
     * @param data 发送的消息内容
     * @param topic 主题
     */
    void sendToMqtt(String data,@Header(MqttHeaders.TOPIC) String topic);

}
