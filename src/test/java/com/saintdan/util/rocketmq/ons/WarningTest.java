package com.saintdan.util.rocketmq.ons;

import com.alibaba.fastjson.JSON;
import com.saintdan.util.rocketmq.TestConstant;
import com.saintdan.util.rocketmq.constant.CommonConstant;
import com.saintdan.util.rocketmq.ons.warning.AdminWarning;
import com.saintdan.util.rocketmq.ons.warning.Warning;
import com.saintdan.util.rocketmq.param.AdminWarningParam;
import com.saintdan.util.rocketmq.param.ContactsParam;
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
        AdminWarningParam param = getAdminWarningParam();
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
        WarningParam param = getWarningParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setUserId(CommonConstant.CONSUMER + SAINTDAN_TEST1);
        System.out.println(JSON.toJSONString(warning.show(param)));
    }

    /**
     * 创建报警
     *
     * <p>
     *     com.aliyuncs.exceptions.ClientException: ONS_INVOKE_ERROR : The url is not available. Please check it again.
     * </p>
     */
    @Test
    public void testCreate() throws Exception {
        WarningParam param = getWarningParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setUserId(CommonConstant.CONSUMER + SAINTDAN_TEST1);
        param.setContacts(new ContactsParam("test", "13344445555"));
        param.setThreshold("1000");
        System.out.println(JSON.toJSONString(warning.create(param)));
    }

    /**
     * 修改报警
     *
     * <p>
     *     未测试
     * </p>
     */
    @Test
    public void testUpdate() throws Exception {
        WarningParam param = getWarningParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setUserId(CommonConstant.CONSUMER + SAINTDAN_TEST1);
        param.setContacts(new ContactsParam("test", "13344445555"));
        param.setThreshold("100");
        System.out.println(JSON.toJSONString(warning.update(param)));
    }

    /**
     * 使能报警
     *
     * <p>
     *     未测试
     * </p>
     */
    @Test
    public void testEnable() throws Exception {
        WarningParam param = getWarningParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setUserId(CommonConstant.CONSUMER + SAINTDAN_TEST1);
        System.out.println(JSON.toJSONString(warning.enable(param)));
    }

    /**
     * 关闭报警
     *
     * <p>
     *     未测试
     * </p>
     */
    @Test
    public void testDisable() throws Exception {
        WarningParam param = getWarningParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setUserId(CommonConstant.CONSUMER + SAINTDAN_TEST1);
        System.out.println(JSON.toJSONString(warning.disable(param)));
    }

    /**
     * 删除报警
     *
     * <p>
     *     未测试
     * </p>
     */
    @Test
    public void testDelete() throws Exception {
        WarningParam param = getWarningParam();
        param.setTopic(SAINTDAN_TEST1);
        param.setUserId(CommonConstant.CONSUMER + SAINTDAN_TEST1);
        System.out.println(JSON.toJSONString(warning.delete(param)));
    }

    private AdminWarning adminWarning = new AdminWarning();

    private Warning warning = new Warning();
}
