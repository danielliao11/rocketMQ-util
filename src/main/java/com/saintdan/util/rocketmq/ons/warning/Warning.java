package com.saintdan.util.rocketmq.ons.warning;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.*;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.WarningParam;

/**
 * 报警管理
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/20/16
 * @since JDK1.8
 */
public class Warning {

    /**
     * 获取报警信息
     *
     * <p>
     *     获取某一个消费集群的所有报警信息，如果不填cid和topic则列出用户名下所有的报警信息
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsWarnListRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      UserId	         String	      是	            管理员用户账号
     *      ConsumerId	     String	      是	            消费集群CID
     *      Topic	         String	      是	            消费的Topic名称
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsWarnListResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(WarnViewDO)	        查询结果
     * </pre>
     *
     * <p>
     *     {@link com.aliyuncs.ons.model.v20151214.OnsWarnListResponse.WarnViewDO} 数据结构
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      Name	         String	                    名称
     *      Value	         String	                    值
     *      Type	         String	                    类型
     *      Params	         String	                    参数
     *      Ari	             String	                    null
     * </pre>
     *
     * <p>
     *     {@link com.aliyuncs.ons.model.v20151214.OnsWarnListResponse.WarnViewDO.YunContact} 数据结构
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      AliyunPK	     String	                    阿里云用户ID
     *      ConsumerID	     String	                    消费集群CID
     *      Topic	         String	                    Topic名称
     *      Threshold	     String	                    null
     *      Status	         String	                    当前状态
     *      contacts	     List(YunContact)	        联系人信息
     * </pre>
     *
     * @throws ClientException
     */
    public OnsWarnListResponse show(WarningParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsWarnListRequest request = new OnsWarnListRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setConsumerId(param.getUserId());
        request.setTopic(param.getTopic());
        return client.getAcsResponse(request);
    }

    /**
     * 创建报警信息
     *
     * <p>
     *     针对某一个消费集群，创建对应的报警信息配置,重复创建一个联系人的会报错
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsWarnCreateRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      UserId	         String	      是	            管理员用户账号
     *      ConsumerId	     String	      是	            消费集群CID
     *      Topic	         String	      是	            消费的Topic名称
     *      Contacts	     String	      是	            联系人信息,姓名和手机，以双竖线分割
     *      Threshold	     String	      是	            报警的阈值
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsWarnCreateResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsWarnCreateResponse create(WarningParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsWarnCreateRequest request = new OnsWarnCreateRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setConsumerId(param.getUserId());
        request.setTopic(param.getTopic());
        request.setContacts(param.contactsStr());
        request.setThreshold(param.getThreshold());
        return client.getAcsResponse(request);
    }

    /**
     * 编辑报警信息
     *
     * <p>
     *     针对某一个消费集群，编辑并更新对应的报警信息，例如修改报警阀值
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsWarnEditorRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      UserId	         String	      是	            管理员用户账号
     *      ConsumerId	     String	      是	            消费集群CID
     *      Topic	         String	      是	            消费的Topic名称
     *      Contacts	     String	      是	            联系人信息,姓名和手机，以双竖线分割
     *      Threshold	     String	      是	            报警的阈值
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsWarnEditorResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsWarnEditorResponse update(WarningParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsWarnEditorRequest request = new OnsWarnEditorRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setConsumerId(param.getUserId());
        request.setTopic(param.getTopic());
        request.setContacts(param.contactsStr());
        request.setThreshold(param.getThreshold());
        return client.getAcsResponse(request);
    }

    /**
     * 使能报警信息
     *
     * <p>
     *     针对某一个消费集群，使能对应的报警信息
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsWarnEnableRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      UserId	         String	      是	            管理员用户账号
     *      ConsumerId	     String	      是	            消费集群CID
     *      Topic	         String	      是	            消费的Topic名称
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsWarnEnableResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsWarnEnableResponse enable(WarningParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsWarnEnableRequest request = new OnsWarnEnableRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setConsumerId(param.getUserId());
        request.setTopic(param.getTopic());
        return client.getAcsResponse(request);
    }

    /**
     * 关闭报警信息
     *
     * <p>
     *     针对某一个消费集群，关闭对应的报警信息
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsWarnDisableRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      UserId	         String	      是	            管理员用户账号
     *      ConsumerId	     String	      是	            消费集群CID
     *      Topic	         String	      是	            消费的Topic名称
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsWarnDisableResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsWarnDisableResponse disable(WarningParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsWarnDisableRequest request = new OnsWarnDisableRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setConsumerId(param.getUserId());
        request.setTopic(param.getTopic());
        return client.getAcsResponse(request);
    }

    /**
     * 删除报警信息
     *
     * <p>
     *     针对某一个消费集群，删除对应的报警信息配置，重复删除并不提示错误
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsWarnDeleteRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      UserId	         String	      是	            管理员用户账号
     *      ConsumerId	     String	      是	            消费集群CID
     *      Topic	         String	      是	            消费的Topic名称
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsWarnDeleteResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsWarnDeleteResponse delete(WarningParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsWarnDeleteRequest request = new OnsWarnDeleteRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setConsumerId(param.getUserId());
        request.setTopic(param.getTopic());
        return client.getAcsResponse(request);
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
