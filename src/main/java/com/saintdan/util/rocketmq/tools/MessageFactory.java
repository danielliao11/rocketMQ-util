package com.saintdan.util.rocketmq.tools;

import java.io.UnsupportedEncodingException;

import com.aliyun.openservices.ons.api.Message;

/**
 * 消息装配工厂
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/19/15
 * @since JDK1.8
 */
public class MessageFactory {

    /**
     * 装配信息
     *
     * @param topic     话题
     * @param tag       过滤标签
     * @param key       过滤key
     * @param msg       消息体
     * @return          消息对象
     * @throws UnsupportedEncodingException 
     */
    public static Message generateMessage(String topic, String tag, String key, String msg) throws UnsupportedEncodingException {
        return new Message(
                topic,
                tag,
                key,
                msg.getBytes("UTF-8")
        );
    }
}
