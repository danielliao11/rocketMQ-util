package com.saintdan.util.rocketmq.ons;

import com.saintdan.util.rocketmq.TestConstant;
import com.saintdan.util.rocketmq.constant.CommonConstant;
import com.saintdan.util.rocketmq.param.BaseMsgParam;
import com.saintdan.util.rocketmq.param.BaseParam;
import com.saintdan.util.rocketmq.param.TopicParam;

/**
 * 测试基类
 * <p>
 *     为测试用例提供参数
 * </p>
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 12/18/15
 * @since JDK1.8
 */
public class BaseTest {

    protected BaseParam getParam() {
        BaseParam param = new BaseParam();
        param.setRegionId(CommonConstant.REGION_ID);
        param.setAccessKey(TestConstant.ACCESS_KEY);
        param.setSecretKey(TestConstant.SECRET_KEY);
        return param;
    }

    protected BaseMsgParam getBaseMsgParam() {
        BaseMsgParam param = new BaseMsgParam();
        param.setRegionId(CommonConstant.REGION_ID);
        param.setAccessKey(TestConstant.ACCESS_KEY);
        param.setSecretKey(TestConstant.SECRET_KEY);
        param.setOnsRegionId(HANG_ZHOU);
        return param;
    }

    protected TopicParam getTopicParam() {
        TopicParam param = new TopicParam();
        param.setRegionId(CommonConstant.REGION_ID);
        param.setAccessKey(TestConstant.ACCESS_KEY);
        param.setSecretKey(TestConstant.SECRET_KEY);
        param.setOnsRegionId(HANG_ZHOU);
        return param;
    }

    private static final String HANG_ZHOU = "hangzhou";

}
