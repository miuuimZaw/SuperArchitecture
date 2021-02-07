package com.zaw.superarch.constant;

/**
 * RocketMQ 基础信息
 *
 * @author zhangaiwen
 */
public interface RmqConstant {

    // todo 这边用枚举更好

    /**
     * 工作流rmq group
     */
    String WPRK_FLOW_RMQ_GROUP = "IOT-GROUP";

    /**
     * 工作流执行回调rmq topic
     */
    String WPRK_FLOW_TOPIC_GPSUNBIND = "eim_wf_callback";

    /**
     * 消息队列通用tag
     */
    String WOKR_FLOW_COMMON_TAG = "*";
}
