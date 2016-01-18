package com.saintdan.util.rocketmq.param;

/**
 * 基础消息参数
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/19/15
 * @since JDK1.8
 */
public class BaseMsgParam extends BaseParam {

    private static final long serialVersionUID = 1277894026608857860L;

    // 话题
    private String topic;

    // 服务
    private String broker;

    // 用户id
    private String UserId;

    // 客户端id
    private String clientId;

    // 消息体
    private String message;

    // 消息id
    private String msgId;

    // 过滤标签
    private String tag;

    // 过滤key
    private String key;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
