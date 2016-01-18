package com.saintdan.util.rocketmq.simple;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

/**
 * 自定义消息监听者
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/20/15
 * @since JDK1.8
 */
public class CustomMessageListener implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext context) {
        System.out.println("Receive message: " + message);
        return Action.CommitMessage;
    }
}
