package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    /**
     * 显示所有Topic
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testShowAllTopics() throws Exception {
        for (OnsTopicListResponse.PublishInfoDo publishInfoDo : topic.showAll(getTopicParam())) {
            System.out.println(JSON.toJSONString(publishInfoDo));
        }
    }

    /**
     * 显示指定Topic
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testShow() throws Exception {
        TopicParam param = getTopicParam();
        param.setTopic(SAINTDAN_TEST1);
        System.out.println(JSON.toJSONString(topic.show(param)));
    }

    /**
     * 显示指定Topic偏移量
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testTopicOffset() throws Exception {
        TopicParam param = getTopicParam();
        param.setTopic(SAINTDAN_TEST1);
        System.out.println(JSON.toJSONString(topic.showTopicOffset(param)));

    }

    /**
     * 创建Topic
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testCreate() throws Exception {
        TopicParam param = getTopicParam();
        param.setTopic(SAINTDAN_TEST2);
        param.setQueueNum(2);
        param.setOrder(false);
        param.setQps(1000L);
        param.setRemark("test2");
        param.setStatus(0);
        param.setCluster("hzshare");
        System.out.println(JSON.toJSONString(topic.create(param)));
    }

    /**
     * 删除指定Topic
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testDelete() throws Exception {
        TopicParam param = getTopicParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setCluster("hzshare");
        System.out.println(JSON.toJSONString(topic.delete(param)));
    }

    private Topic topic = new Topic();
}
