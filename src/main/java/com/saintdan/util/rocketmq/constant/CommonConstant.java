package com.saintdan.util.rocketmq.constant;

/**
 * 通用常量
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/24/15
 * @since JDK1.8
 */
public interface CommonConstant {

    String REGION_ID = "cn-hangzhou";

    String PRODUCER = "PID_";

    String CONSUMER = "CID_";

    Integer FIRST_ITEM = 0;

    /**
     * QoS=0：最多一次，有可能重复或丢失。
     * QoS=1：至少一次，有可能重复。
     * Client[Qos=1,DUP=0重复次数,MessageId=x] --> PUBLISH --> Server收到后，存储Message，发布，删除，向Client回发PUBACK
     * Client收到PUBACK后，删除Message；如果未收到PUBACK，设置DUP++，重新发送，Server端重新发布，所以有可能重复发送消息。
     * QoS=2：只有一次，确保消息只到达一次（用于比较严格的计费系统）。
     */
    Integer DEFAULT_QOS = 2;
}
