package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.ons.model.v20151214.OnsPublishCreateResponse;
import com.aliyuncs.ons.model.v20151214.OnsPublishDeleteResponse;
import com.aliyuncs.ons.model.v20151214.OnsPublishGetResponse;
import com.aliyuncs.ons.model.v20151214.OnsPublishListResponse;
import com.saintdan.util.rocketmq.constant.CommonConstant;
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

    /**
     * 查看指定Publisher
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testShow() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setUserId(CommonConstant.PRODUCER + SAINTDAN_TEST1);
        OnsPublishGetResponse.PublishInfoDo publishInfoDo = publisher.show(param);
        System.out.println(JSON.toJSONString(publishInfoDo));
    }

    /**
     * 查看所有Publisher
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testShowAll() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        OnsPublishListResponse response = publisher.showAll(param);
        System.out.println(JSON.toJSONString(response));
    }

    /**
     * 在指定Topic下创建Publisher
     *
     * <p>
     *     测试通过
     * </p>
     * <pre>
     *     一个topic下,只能创建一个Publisher
     * </pre>
     */
    @Test
    public void testCreate() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic(SAINTDAN_TEST2);
        param.setUserId(CommonConstant.PRODUCER + SAINTDAN_TEST2);
        OnsPublishCreateResponse response = publisher.create(param);
        System.out.println(JSON.toJSONString(response));
    }

    /**
     * 删除指定Topic下的Publisher
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testDelete() throws Exception {
        BaseMsgParam param = getBaseMsgParam();
        param.setTopic(SAINTDAN_TEST2);
        param.setUserId(SAINTDAN_TEST2);
        OnsPublishDeleteResponse response = publisher.delete(param);
        System.out.println(JSON.toJSONString(response));
    }

    private Publisher publisher = new Publisher();
}
