package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.ons.model.v20151214.OnsMessagePushResponse;
import com.aliyuncs.ons.model.v20151214.OnsMessageSendRequest;
import com.aliyuncs.ons.model.v20151214.OnsMessageSendResponse;
import com.saintdan.util.rocketmq.ons.message.Message;
import com.saintdan.util.rocketmq.param.BaseMsgParam;
import org.junit.Test;

import java.util.UUID;

/**
 * 消息测试
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 12/18/15
 * @since JDK1.8
 */
public class MessageTest extends BaseTest {

    /**
     * 发送消息
     * <p>
     *     测试通过
     * </p>
     *
     * @throws Exception
     */
    @Test
    public void testSend() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic("你的topic");
        param.setUserId("你的发布者id");
        param.setMessage("test");
        param.setTag("test");
        OnsMessageSendResponse response = message.send(param);
        System.out.println(JSON.toJSONString(response));
    }

    /**
     * 推送消息
     * <p>
     *     TODO 测试未通过
     *     com.aliyuncs.exceptions.ClientException: BIZ_PUSH_MESSAGE_TO_CONSUMER_ERROR : Push message to consumer directly failed. Please try again later.
     * </p>
     *
     * @throws Exception
     */
    @Test
    public void testPush() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setUserId("您的消费者ID");
        param.setClientId("目标客户端地址,比如:10.146.137.139@65531");
        param.setMsgId(UUID.randomUUID().toString());
        param.setTopic("你的topic");
        OnsMessagePushResponse response = message.push(param);
        System.out.println(JSON.toJSONString(response));
    }

    private Message message = new Message();
}
