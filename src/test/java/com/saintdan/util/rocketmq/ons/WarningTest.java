package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.saintdan.util.rocketmq.TestConstant;
import com.saintdan.util.rocketmq.constant.CommonConstant;
import com.saintdan.util.rocketmq.ons.warning.AdminWarning;
import com.saintdan.util.rocketmq.ons.warning.Warning;
import com.saintdan.util.rocketmq.param.AdminWarningParam;
import com.saintdan.util.rocketmq.param.WarningParam;
import org.junit.Test;

/**
 * 报警测试
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/20/16
 * @since JDK1.8
 */
public class WarningTest extends BaseTest {

    /**
     * 管理员操作
     */
    @Test
    public void testAdminWarning() throws Exception {
        AdminWarningParam param = (AdminWarningParam) getBaseMsgParam();
        param.setUserId(TestConstant.ADMIN_ACCOUNT);
        param.setTopic(SAINTDAN_TEST1);
        System.out.println(JSON.toJSONString(adminWarning.operate(param)));
    }

    /**
     * 查看报警信息
     *
     * <p>
     *     测试通过
     * </p>
     */
    @Test
    public void testShow() throws Exception {
        WarningParam param = (WarningParam) getBaseMsgParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setUserId(CommonConstant.CONSUMER + SAINTDAN_TEST1);
        System.out.println(JSON.toJSONString(warning.show(param)));
    }

    private AdminWarning adminWarning = new AdminWarning();

    private Warning warning = new Warning();
}
