package com.zaw.superarch.consumer;

import com.alibaba.fastjson.JSON;
import com.zaw.superarch.conf.RmqConsumerConfig;
import com.zaw.superarch.constant.RmqConstant;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 工作流消费者
 *
 * @author zhangaiwen
 */
@Log4j2
@Configuration
public class DemoConsumer extends RmqConsumerConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            super.listener(RmqConstant.WPRK_FLOW_RMQ_GROUP, RmqConstant.WPRK_FLOW_TOPIC_GPSUNBIND, RmqConstant.WOKR_FLOW_COMMON_TAG);
        } catch (MQClientException e) {
            log.error("消费工作流回调消息失败, topic:{}, tag:{}, 异常信息:{}", RmqConstant.WPRK_FLOW_RMQ_GROUP, RmqConstant.WPRK_FLOW_TOPIC_GPSUNBIND, e);
        }
    }

    @Override
    public ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs) {
        for (MessageExt msg : msgs) {
            String msgStr = new String(msg.getBody(), StandardCharsets.UTF_8);
            if (this.consumerHandler(msgStr)) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        }
        log.info("工作流回调消息 >>> size={}", msgs.size());
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /**
     * 消息处理
     *
     * @param reqStr 消息
     */
    private Boolean consumerHandler(String reqStr) {
        log.info("工作流回调消息:{}", reqStr);
        Object workflowCompleteReq = JSON.parseObject(reqStr, Object.class);
        if (true) {
            log.info("WorkFlowEndReq:{}", workflowCompleteReq);
            // TODO 数据处理
        }
        return false;
    }

}



