package com.saintdan.util.rocketmq;

import com.saintdan.util.rocketmq.param.FilterParam;
import com.saintdan.util.rocketmq.param.SimpleMsgParam;
import com.saintdan.util.rocketmq.simple.SimpleMsg;
import org.junit.Test;

/**
 * 普通消息测试
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/20/15
 * @since JDK1.8
 */
public class SimpleTest {

    @Test
    public void testSend() throws Exception {
        SimpleMsg simpleMsg = new SimpleMsg();
        SimpleMsgParam param = getParam();
        param.setUserId("你的发布者id");
        param.setMessage("Hello MQTT producer.");
        simpleMsg.send(param);
    }

    @Test
    public void testConsume() throws Exception {
        SimpleMsg simpleMsg = new SimpleMsg();
        SimpleMsgParam param = getParam();
        param.setUserId("您的消费者ID");
        simpleMsg.consume(param);
    }

    @Test
    public void testPushConsume() throws Exception {
        SimpleMsg simpleMsg = new SimpleMsg();
        SimpleMsgParam param = getParam();
        param.setUserId("您的消费者ID");
        param.setFilterParams(getFilter());
        simpleMsg.pushConsume(param);
    }

    @Test
    public void testPullConsume() throws Exception {
        SimpleMsg simpleMsg = new SimpleMsg();
        SimpleMsgParam param = getParam();
        param.setUserId("您的消费者ID");
        param.setConsumerGroup("LinZiSubscribers");
        simpleMsg.pullConsume(param);
    }

    private SimpleMsgParam getParam() {
        SimpleMsgParam param = new SimpleMsgParam();
        param.setTopic("你的topic");
        param.setBroker("tcp://mqtt.ons.aliyun.com:1883");
        param.setAccessKey(TestConstant.ACCESS_KEY);
        param.setSecretKey(TestConstant.SECRET_KEY);
        param.setTag("tag1");
        param.setKey("key1");

        return param;
    }

    private FilterParam[] getFilter() {
        FilterParam param1 = new FilterParam();
        param1.setTopic("你的topic1");
        param1.setTag("tag1");
        FilterParam param2 = new FilterParam();
        param2.setTopic("你的topic2");
        param2.setTag("tag2");
        FilterParam param3 = new FilterParam();
        param3.setTopic("你的topic3");
        param3.setTag("tag3");
        return new FilterParam[]{param1, param2, param3};
    }
}
