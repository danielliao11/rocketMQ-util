package com.saintdan.util.rocketmq.simple;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.aliyun.openservices.ons.api.*;
import com.saintdan.util.rocketmq.constant.CommonConstant;
import com.saintdan.util.rocketmq.param.FilterParam;
import com.saintdan.util.rocketmq.param.SimpleMsgParam;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 普通信息
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/20/15
 * @since JDK1.8
 */
public class SimpleMsg {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /**
     * 发送普通消息
     *
     * @param param     参数
     * @throws UnsupportedEncodingException 
     */
    public void send(SimpleMsgParam param) throws UnsupportedEncodingException {
        // 创建发送者
        Producer producer = ONSFactory.createProducer(initProperties(param));
        producer.start();
        SendResult result = producer.send(initMessage(param));
        System.out.println("Send result is: " + result);
        if (StringUtils.isNotBlank(result.getMessageId())) {
            producer.shutdown();
        }
    }

    /**
     * 普通订阅消费
     *
     * @param param     参数
     * @throws Exception
     */
    public void consume(SimpleMsgParam param) throws Exception {
        // 创建订阅者
        Consumer consumer = ONSFactory.createConsumer(initProperties(param));
        consumer.subscribe(param.getTopic(), param.getTag(), new CustomMessageListener());
        consumer.start();
        Thread.sleep(Long.MAX_VALUE);
        System.out.println("Consumer started");
    }

    /**
     * 推送方式订阅消费消息
     * <pre>
     *     PushConsume用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
     *     但是实际PushConsume内部是使用长轮询Pull方式从Broker拉消息，然后再回调用户Listener方法<br>
     * </pre>
     *
     * @param param     参数
     * @throws Exception
     */
    public void pushConsume(SimpleMsgParam param) throws Exception {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        if (StringUtils.isBlank(param.getConsumerGroup())) {
            consumer.setConsumerGroup(param.getConsumerGroup());
        }

        /**
         * 订阅指定topic下所有消息<br>
         * 注意：一个consumer对象可以订阅多个topic</br>
         * 按照过滤条件订阅各话题
         */
        for (FilterParam filterParam : param.getFilterParams()) {
            consumer.subscribe(filterParam.getTopic(), filterParam.getTag());
        }

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.registerMessageListener(new CustomMessageListenerConcurrently());

        consumer.start();
        System.out.println("Subscribe start.");
    }

    /**
     * 拉取方式订阅消费消息
     *
     * @param param     参数
     * @throws Exception
     */
    public void pullConsume(SimpleMsgParam param) throws Exception {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer();
        if (StringUtils.isBlank(param.getConsumerGroup())) {
            consumer.setConsumerGroup(param.getConsumerGroup());
        }
        consumer.start();
        // 设置消息队列Set
        Set<MessageQueue> messageQueueSet = consumer.fetchSubscribeMessageQueues(param.getTopic());
        // 消费每个队列中的消息
        for (MessageQueue mq : messageQueueSet) {
            System.out.println("Consume from the queue: " + mq);
            SINGLE_MQ: while (true) {
                PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                System.out.println("Pull result is: " + pullResult);
                putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                switch (pullResult.getPullStatus()) {
                    case FOUND: // 有新消息
                        break;
                    case NO_NEW_MSG: // 没有新消息
                        break SINGLE_MQ;
                    case NO_MATCHED_MSG: // 没有匹配的消息
                        break;
                    case OFFSET_ILLEGAL: // 非法的offset
                        break;
                    default:
                        break;
                }
            }
        }
        consumer.shutdown();
    }

    // --------------------------
    // PRIVATE METHODS
    // --------------------------

    /**
     * 初始化配置
     *
     * @param param     参数
     * @return          配置
     */
    private Properties initProperties(SimpleMsgParam param) {
        Properties properties = new Properties();
        // 按照 clientId 设置生产者,消费者ID
        if (param.getUserId().contains(CommonConstant.PRODUCER)) {
            properties.put(PropertyKeyConst.ProducerId, param.getUserId());
        }
        if (param.getUserId().contains(CommonConstant.CONSUMER)) {
            properties.put(PropertyKeyConst.ConsumerId, param.getUserId());
        }
        properties.put(PropertyKeyConst.AccessKey, param.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey, param.getSecretKey());
        return properties;
    }

    /**
     * 初始化消息
     *
     * @param param     参数
     * @return          消息
     * @throws UnsupportedEncodingException 
     */
    private Message initMessage(SimpleMsgParam param) throws UnsupportedEncodingException {
        return new Message(
                param.getTopic(), // 消息 topic
                param.getTag(), // 过滤标签
                param.getKey(), // 过滤key
                param.getMessage().getBytes("UTF-8") // 消息体
        );
    }

    private static final Map<MessageQueue, Long> offsetMap = new HashMap<MessageQueue, Long>();

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetMap.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offsetMap.get(mq);
        if (offset != null)
            return offset;
        return 0L;
    }
}
