package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.ons.model.v20151214.OnsPublishCreateResponse;
import com.aliyuncs.ons.model.v20151214.OnsPublishDeleteResponse;
import com.aliyuncs.ons.model.v20151214.OnsPublishGetResponse;
import com.aliyuncs.ons.model.v20151214.OnsPublishListResponse;
import com.saintdan.util.rocketmq.ons.publish.Publisher;
import com.saintdan.util.rocketmq.param.BaseMsgParam;
import org.junit.Test;

/**
 * 发布者测试
 * <p>
 *     所有测试通过.
 * </p>
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 12/18/15
 * @since JDK1.8
 */
public class PublisherTest extends BaseTest {

    @Test
    public void testShow() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic("你的topic");
        param.setUserId("你的发布者id");
        OnsPublishGetResponse.PublishInfoDo publishInfoDo = publisher.show(param);
        System.out.println(JSON.toJSONString(publishInfoDo));
    }

    @Test
    public void testShowAll() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        OnsPublishListResponse response = publisher.showAll(param);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testCreate() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic("你的topic");
        param.setUserId("你的发布者id");
        OnsPublishCreateResponse response = publisher.create(param);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testDelete() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic("你的topic");
        param.setUserId("你的发布者id");
        OnsPublishDeleteResponse response = publisher.delete(param);
        System.out.println(JSON.toJSONString(response));
    }

    private Publisher publisher = new Publisher();
}
