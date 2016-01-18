package com.saintdan.util.rocketmq.ons.subscribe;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.*;
import com.saintdan.util.rocketmq.constant.CommonConstant;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.BaseMsgParam;

import java.util.List;

/**
 * 消息订阅类
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/27/15
 * @since JDK1.8
 */
public class Subscriber {

    /**
     * 查询指定订阅信息
     * <p>
     *     根据Topic和ConsumerID信息查询对应的订阅关系，如果没有该订阅关系，会提示没有权限
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsSubscriptionGetRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ConsumerId	     String	      是	            需要查询的订阅关系的消费集群ID
     *      Topic	         String	      是	            需要查询的Topic
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsSubscriptionGetResponse.SubscribeInfoDo}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(PublishInfoDo)	    查询结果集合
     * </pre>
     *
     * <p>
     *     {@link OnsSubscriptionGetResponse.SubscribeInfoDo} 数据结构
     * </p>
     * <pre>
     *      成员	             类型	                描述
     *      id	             Long	                订阅信息在数据库中的索引编号
     *      channelId	     Integer	            该Topic所在区域ID，0-ALIYUN，1-CLOUD，2,3,4
     *      channelName	     String	                该Topic所在区域名称，ALIYUN,CLOUD,...
     *      regionId	     Long	                该Topic所在区域ID，就是ONSRegionList方法获取的内容
     *      regionName	     String	                该topic所在区域名称
     *      topic	         String	                Topic名称
     *      owner	         String	                Topic所有者编号
     *      status	         Integer	            当前状态编号（0 服务中 1 冻结 2 暂停）
     *      statusName	     String	                当前状态别名，服务中，
     *      createTime	     Long	                创建时间
     *      updateTime	     Long	                更新时间
     * </pre>
     * @throws ClientException
     */
    public OnsSubscriptionGetResponse.SubscribeInfoDo show(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsSubscriptionGetRequest request = new OnsSubscriptionGetRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setConsumerId(param.getUserId());
        List<OnsSubscriptionGetResponse.SubscribeInfoDo> subscribeInfoDos = client.getAcsResponse(request).getData();
        if (subscribeInfoDos.isEmpty()) {
            return null;
        }
        return subscribeInfoDos.get(CommonConstant.FIRST_ITEM);
    }

    /**
     * 获取所有订阅信息
     * <p>
     *     获取当前用户的所有订阅信息
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsSubscriptionListRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsSubscriptionListResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(PublishInfoDo)	    查询结果集合
     * </pre>
     * @throws ClientException
     */
    public OnsSubscriptionListResponse showAll(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsSubscriptionListRequest request = new OnsSubscriptionListRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        return client.getAcsResponse(request);
    }

    /**
     * 注册订阅信息
     * <p>
     *     注册订阅信息，创建订阅组。
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsSubscriptionCreateRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ConsumerId	     String	      是	            创建的消息消费集群的CID
     *      Topic	         String	      是	            需要订阅的Topic
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsSubscriptionCreateResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     * @throws ClientException
     */
    public OnsSubscriptionCreateResponse create(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsSubscriptionCreateRequest request = new OnsSubscriptionCreateRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setConsumerId(param.getUserId());
        return client.getAcsResponse(request);
    }

    /**
     * 删除订阅信息
     * <p>
     *     删除订阅关系
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsSubscriptionDeleteRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ConsumerId	     String	      是	            需要删除的消息消费集群的CID
     *      Topic	         String	      是	            订阅的Topic
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsSubscriptionDeleteResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     * @throws ClientException
     */
    public OnsSubscriptionDeleteResponse delete(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsSubscriptionDeleteRequest request = new OnsSubscriptionDeleteRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setConsumerId(param.getUserId());
        return client.getAcsResponse(request);
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
