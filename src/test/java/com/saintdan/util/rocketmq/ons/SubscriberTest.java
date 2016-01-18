package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.ons.model.v20151214.OnsSubscriptionCreateResponse;
import com.aliyuncs.ons.model.v20151214.OnsSubscriptionDeleteResponse;
import com.aliyuncs.ons.model.v20151214.OnsSubscriptionGetResponse;
import com.aliyuncs.ons.model.v20151214.OnsSubscriptionListResponse;
import com.saintdan.util.rocketmq.ons.subscribe.Subscriber;
import com.saintdan.util.rocketmq.param.BaseMsgParam;
import org.junit.Test;

/**
 * 订阅测试
 * <p>
 *     测试全部通过.
 * </p>
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 12/18/15
 * @since JDK1.8
 */
public class SubscriberTest extends BaseTest {

    @Test
    public void testShow() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic("你的topic");
        param.setUserId("您的消费者ID");
        OnsSubscriptionGetResponse.SubscribeInfoDo info = subscriber.show(param);
        System.out.println(JSON.toJSONString(info));
    }

    @Test
    public void testShowAll() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        OnsSubscriptionListResponse response = subscriber.showAll(param);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testCreate() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic("你的topic");
        param.setUserId("您的消费者ID");
        OnsSubscriptionCreateResponse response = subscriber.create(param);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testDelete() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic("你的topic");
        param.setUserId("您的消费者ID");
        OnsSubscriptionDeleteResponse response = subscriber.delete(param);
        System.out.println(JSON.toJSONString(response));
    }

    private Subscriber subscriber = new Subscriber();
}
