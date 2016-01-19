package com.saintdan.util.rocketmq.ons.warning;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.OnsWarnAdminRequest;
import com.aliyuncs.ons.model.v20151214.OnsWarnAdminResponse;
import com.saintdan.util.rocketmq.enums.WarnOperateType;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.BaseMsgParam;

/**
 * 管理员警告操作
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/19/16
 * @since JDK1.8
 */
public class AdminWarning {

    /**
     * 管理员警告操作
     *
     * <p>
     *     使用系统开通的管理员账号去操作某个消费集群的报警信息，非管理员账号不可调用
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsWarnAdminRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      UserId	         String	      是	            管理员户账号
     *      ConsumerId	     String	      是	            消费集群CID
     *      Topic	         String	      是	            消费的Topic名称
     *      Type	         String	      是	            操作类型 {@link WarnOperateType}
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsWarnAdminResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsWarnAdminResponse operate(BaseMsgParam param, WarnOperateType type) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsWarnAdminRequest request = new OnsWarnAdminRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setUserId(request.getUserId());
        request.setConsumerId(param.getClientId());
        request.setTopic(param.getTopic());
        request.setType(type.description());
        return client.getAcsResponse(request);
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
