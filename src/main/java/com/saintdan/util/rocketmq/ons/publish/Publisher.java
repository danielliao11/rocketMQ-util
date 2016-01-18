package com.saintdan.util.rocketmq.ons.publish;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.*;
import com.saintdan.util.rocketmq.constant.CommonConstant;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.BaseMsgParam;

import java.util.List;

/**
 * 信息发布类
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/27/15
 * @since JDK1.8
 */
public class Publisher {

    /**
     * 查询发布信息
     * <p>
     *      根据Producer_ID和Topic查询发布信息
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsPublishGetRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ProducerId	     String	      是	            需要查询的发布关系的发布集群ID
     *      Topic	         String	      是	            需要查询的Topic
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsPublishGetResponse.PublishInfoDo}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(PublishInfoDo)	    查询结果集合
     * </pre>
     *
     * <p>
     *     {@link OnsTopicListResponse.PublishInfoDo} 数据结构
     * </p>
     * <pre>
     *      成员	             类型	                描述
     *      id	             Long	                该发布信息在数据库中的ID
     *      channelId	     Integer	            该Topic所在区域ID，0-ALIYUN，1-CLOUD，2,3,4
     *      channelName	     String	                该Topic所在区域名称，ALIYUN,CLOUD,...
     *      regionId	     Long	                该Topic所在区域ID，就是ONSRegionList方法获取的内容
     *      regionName	     String	                该topic所在区域名称
     *      topic	         String	                Topic名称
     *      owner	         String	                Topic所有者编号
     *      relation	     Integer	            所有关系编号1--持有者，2--可以发布，4--可以订阅，6--可以发布和订阅
     *      relationName	 String	                所有关系名称，例如持有者、可订阅、可发布、可发布订阅
     *      status	         Integer	            当前状态编号（0 服务中 1 冻结 2 暂停）
     *      statusName	     String	                当前状态别名，服务中，
     *      appkey	         Integer	            null
     *      createTime	     Long	                创建时间
     *      updateTime	     Long	                更新时间
     *      remark	         String	                备注，可以不填
     * </pre>
     *
     * @throws ClientException
     */
    public OnsPublishGetResponse.PublishInfoDo show(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsPublishGetRequest request = new OnsPublishGetRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setProducerId(param.getUserId());
        List<OnsPublishGetResponse.PublishInfoDo> publishInfoDoList = client.getAcsResponse(request).getData();
        if (publishInfoDoList.isEmpty()) {
            return null;
        }
        return publishInfoDoList.get(CommonConstant.FIRST_ITEM);
    }

    /**
     * 获取所有发布信息
     * <p>
     *     获取当前用户所拥有的所有发布信息
     * </p>
     * @param param     请求参数
     * <p>
     *      {@link OnsPublishListRequest}
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
     *     {@link OnsPublishListResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(PublishInfoDo)	    查询结果集合
     * </pre>
     *
     * @throws ClientException
     */
    public OnsPublishListResponse showAll(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsPublishListRequest request = new OnsPublishListRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        return client.getAcsResponse(request);
    }

    /**
     * 注册发布信息
     * <p>
     *     根据用户指定的ONS区域，注册发布信息，
     *     需要指定Topic和PID，因此，需要首先创建Topic，否则提示没有权限。
     *     多次调用会抛出已存在发布关系的异常。
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsPublishCreateRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ProducerId	     String	      是	            需要创建的发布关系的发布集群ID
     *      Topic	         String	      是	            发布的Topic名称
     * </pre>
     * @return      返回参数
     * <p>
     *     {@link OnsPublishCreateResponse}
     * </p>
     * <pre>
     *      名称	             类型	      描述
     *      RequestId	     String	      为公共参数，每个请求独一无二
     *      HelpUrl	         String	      帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsPublishCreateResponse create(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsPublishCreateRequest request = new OnsPublishCreateRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setProducerId(param.getUserId());
        return client.getAcsResponse(request);
    }

    /**
     * 删除发布信息
     * <p>
     *     根据ClientId和Topic删除创建过的发布关系，首先该发布关系需要存在。
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsPublishDeleteRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ProducerId	     String	      是	            需要删除的发布关系的发布集群ID
     *      Topic	         String	      是	            需要删除的发布关系对应的Topic
     * </pre>
     *
     * @return      返回参数
     * <p>
     *     {@link OnsPublishDeleteResponse}
     * </p>
     * <pre>
     *      名称	             类型	      描述
     *      RequestId	     String	      为公共参数，每个请求独一无二
     *      HelpUrl	         String	      帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsPublishDeleteResponse delete(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsPublishDeleteRequest request = new OnsPublishDeleteRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setProducerId(param.getUserId());
        return client.getAcsResponse(request);
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
