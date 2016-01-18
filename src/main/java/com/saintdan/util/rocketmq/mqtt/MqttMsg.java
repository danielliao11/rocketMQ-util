package com.saintdan.util.rocketmq.mqtt;

import com.saintdan.util.rocketmq.constant.CommonConstant;
import com.saintdan.util.rocketmq.param.BaseMsgParam;
import com.saintdan.util.rocketmq.param.MqttMsgParam;
import com.saintdan.util.rocketmq.tools.MacSignature;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTT消息制造
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/19/15
 * @since JDK1.8
 */
public class MqttMsg {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /**
     * MQTT发送消息
     * 参数详情参照 {@link MqttMsgParam} 以及 {@link BaseMsgParam}
     *
     * @param param     参数
     * @throws Exception
     */
    public void send(MqttMsgParam param) throws Exception {
        MqttClient client = initMqttClient(param);
        MqttConnectOptions options = configMqttConnectOptions(param);
        // 密码
        options.setPassword((MacSignature.publishSignature(param.getUserId(), param.getSecretKey())).toCharArray());
        // 连接消息服务
        client.connect(options);
        // 设置连接初始时间
//        Long start = System.currentTimeMillis();
//        String msg = JSON.toJSONString(
//                MessageFactory.generateMessage(param.getTopic(), param.getTag(), param.getKey(), param.getMessage()));
        final MqttMessage message = new MqttMessage( param.getMessage().getBytes("UTF-8"));
        message.setQos(CommonConstant.DEFAULT_QOS);
        //System.out.println("Message pushed at " + new Date().toString() + ", content is: " + msg);
        //System.out.println("Message body is: " + new String(param.getMessage().getBytes()));
        client.publish(param.getTopic(), message);
//        System.out.println("Use " + (System.currentTimeMillis() - start) + " ms");
        // 设置callback
        client.setCallback(new CustomMqttCallback());
    }

    /**
     * MQTT接收消息
     * 参数详情参照 {@link MqttMsgParam} 以及 {@link BaseMsgParam}
     *
     * @param param     参数
     * @throws Exception
     */
    public void subscribe(MqttMsgParam param) throws Exception {
        MqttClient client = initMqttClient(param);
        MqttConnectOptions options = configMqttConnectOptions(param);
        // 密码
        options.setPassword((MacSignature.subSignature(param.getTopic(), param.getUserId(), param.getSecretKey())).toCharArray());
        // 配置Server url
        options.setServerURIs(new String[]{param.getBroker()});
        // session是否清空
        options.setCleanSession(false);
        // 连接消息服务
        client.connect(options);
        client.subscribe(param.getTopic());
        System.out.println("Subscribe success");
        // 设置callback
        client.setCallback(new CustomMqttCallback());
        Thread.sleep(Integer.MAX_VALUE);
    }

    // --------------------------
    // PRIVATE METHODS
    // --------------------------

    /**
     * 初始化客户端
     *
     * @param param     参数
     * @return          客户端实例
     * @throws Exception
     */
    private MqttClient initMqttClient(MqttMsgParam param) throws Exception {
        // 配置持久化
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        // 创建MQTT客户端
        return new MqttClient(param.getBroker(), param.getUserId(), memoryPersistence);
    }

    /**
     * 配置连接选项
     *
     * @param param     参数
     * @return          连接配置
     * @throws Exception
     */
    private MqttConnectOptions configMqttConnectOptions(MqttMsgParam param) throws Exception {
        // 配置MQTT选项
        MqttConnectOptions options = new MqttConnectOptions();
        // MQTT协议版本
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        // 用户名
        options.setUserName(param.getAccessKey());
        return options;
    }
}
