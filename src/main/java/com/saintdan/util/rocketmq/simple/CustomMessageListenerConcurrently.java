package com.saintdan.util.rocketmq.simple;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 自定义当前消息监听
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/20/15
 * @since JDK1.8
 */
public class CustomMessageListenerConcurrently implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
        /**
         * 这里可以详细描述各话题,各标签信息的消费逻辑
         * 例如
         * <pre>
            MessageExt msg = msgs.get(0);
                if (msg.getTopic().equals("topic1")) {
                // 执行topic1的消费逻辑
                if (msg.getTags() != null && msg.getTags().equals("tag1")) {
                // 执行tag1的消费逻辑
                }
            }
         * </pre>
         */
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
