package com.saintdan.util.rocketmq;

import com.saintdan.util.rocketmq.mqtt.MqttMsg;
import com.saintdan.util.rocketmq.param.MqttMsgParam;
import org.junit.Test;

/**
 * 测试mqtt方式
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/19/15
 * @since JDK1.8
 */
public class MqttTest {

    @Test
    public void testSend() throws Exception {
        MqttMsg mqttMsg = new MqttMsg();
        MqttMsgParam param = getParam();
        param.setUserId("你的发布者id");
        for (int i = 0; i < 10; i++) {
            param.setMessage("Hello MQTT producer." + i);
            mqttMsg.send(param);
        }
    }

    @Test
    public void testSubscribe() throws Exception {
        MqttMsg mqttMsg = new MqttMsg();
        MqttMsgParam param = getParam();
        param.setUserId("您的消费者ID");
        mqttMsg.subscribe(param);
    }

    private MqttMsgParam getParam() {
        MqttMsgParam param = new MqttMsgParam();
        param.setTopic("你的topic");
        param.setBroker("tcp://mqtt.ons.aliyun.com:1883");
        param.setAccessKey(TestConstant.ACCESS_KEY);
        param.setSecretKey(TestConstant.SECRET_KEY);
        param.setTag("tag1");
        param.setKey("key1");

        return param;
    }
}
