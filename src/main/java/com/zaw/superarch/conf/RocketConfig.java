package com.zaw.superarch.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * RocketMQ 配置文件
 *
 * @author zhangaiwen
 */
@Data
@Component
public class RocketConfig {

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String rocketmqAddress;

    @Value("${rocketmq.consumer.consumeThreadMax}")
    private Integer consumerThreadMax;

    @Value("${rocketmq.consumer.consumeThreadMin}")
    private Integer consumerThreadMin;
}
