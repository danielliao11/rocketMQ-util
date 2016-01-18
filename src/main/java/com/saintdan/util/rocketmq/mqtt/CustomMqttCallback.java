package com.saintdan.util.rocketmq.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 自定义mqtt callback
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/19/15
 * @since JDK1.8
 */
public class CustomMqttCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println(topic);
        System.out.println("Message arrived: " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println(iMqttDeliveryToken.getMessageId());
    }
}
