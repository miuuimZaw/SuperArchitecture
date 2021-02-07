package com.zaw.superarch.conf;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * RMQ消费消息配置   TODO  搭建RMQ服务器
 *
 * @author zhangaiwen
 */
@Configuration
public abstract class RmqConsumerConfig {

    @Resource
    private RocketConfig config;

    public void listener(String wprkFlowRmqGroup, String topic, String tags) throws MQClientException {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(wprkFlowRmqGroup + "_" + topic);
//        consumer.setNamesrvAddr(config.getRocketmqAddress());
//        consumer.subscribe(topic, tags);
//        String uidStr = UUID.randomUUID().toString().replace("-", "");
//        consumer.setInstanceName(uidStr);
//        consumer.setConsumeThreadMax(config.getConsumerThreadMax());
//        consumer.setConsumeThreadMin(config.getConsumerThreadMin());
//        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> RmqConsumerConfig.this.dealBody(msgs));
//        // 如果非第一次启动,那么按照上次消费的位置继续消费
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//        consumer.start();
    }

    public abstract ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs);

}
