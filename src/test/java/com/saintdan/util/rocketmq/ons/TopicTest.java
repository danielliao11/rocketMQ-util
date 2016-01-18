package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.ons.model.v20151214.OnsTopicCreateResponse;
import com.aliyuncs.ons.model.v20151214.OnsTopicListResponse;
import com.saintdan.util.rocketmq.ons.topic.Topic;
import com.saintdan.util.rocketmq.param.TopicParam;
import org.junit.Test;

/**
 * Topic测试
 * <p>
 *     所有测试通过.
 * </p>
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/25/15
 * @since JDK1.8
 */
public class TopicTest extends BaseTest {

    @Test
    public void testShowAllTopics() throws Exception {
        for (OnsTopicListResponse.PublishInfoDo publishInfoDo : topic.showAll(getTopicParam())) {
            System.out.println(JSON.toJSONString(publishInfoDo));
        }
    }

    @Test
    public void testShow() throws Exception {
        TopicParam param = getTopicParam();
        param.setTopic("你的topic");
        System.out.println(JSON.toJSONString(topic.show(param)));
    }

    @Test
    public void testTopicOffset() throws Exception {
        TopicParam param = getTopicParam();
        param.setTopic("你的topic");
        System.out.println(JSON.toJSONString(topic.showTopicOffset(param)));

    }

    @Test
    public void testCreate() throws Exception {
        TopicParam param = getTopicParam();
        param.setTopic("你的topic");
        param.setQueueNum(2);
        param.setOrder(false);
        param.setQps(1000L);
        param.setRemark("test2");
        param.setStatus(0);
        param.setCluster("hzshare");
        OnsTopicCreateResponse response = topic.create(param);
        System.out.println("requestId is: " + response.getRequestId());
        System.out.println("helpUrl is: " + response.getHelpUrl());
    }

    @Test
    public void testDelete() throws Exception {
        TopicParam param = getTopicParam();
        param.setTopic("LinZi_Topic_Test2");
        param.setCluster("hzshare");
        System.out.println(JSON.toJSONString(topic.delete(param)));
    }

    private Topic topic = new Topic();
}
