package com.saintdan.util.rocketmq.ons.topic;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.*;
import com.saintdan.util.rocketmq.constant.CommonConstant;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.TopicParam;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Topic类
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/25/15
 * @since JDK1.8
 */
public class Topic {

    /**
     * 查看Topic, Topic参数为空查询列表, 否则查询指定Topic.
     *
     * @param param     请求参数
     * <p>
     *     请求参数 {@link OnsTopicListRequest}
     * </p>
     * <pre>
     *  名称	            类型	        是否必须	描述
     *  OnsRegionId	    String	    是	    当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *  OnsPlatform	    String	    否	    该请求来源，默认是从POP平台
     *  PreventCache	Long	    是	    用于CSRF校验，设置为系统当前时间即可
     *  Topic	        String	    否	    查询指定Topic时设置，否则查询所有Topic
     * </pre>
     *
     * @return          Topic列表或指定Topic
     * <p>
     *     {@link OnsTopicListResponse}
     * </p>
     * <pre>
     *  名称	             类型	                描述
     *  RequestId	     String	                为公共参数，每个请求独一无二
     *  HelpUrl	         String	                帮助链接
     *  Data	         List(PublishInfoDo)    返回所有已发布的Topic信息列表
     * </pre>
     *
     * <p>
     *     {@link OnsTopicListResponse.PublishInfoDo} 数据结构
     * </p>
     * <pre>
     *  成员	             类型	                描述
     *  id	             Long	                该发布信息在数据库中的ID
     *  channelId	     Integer	            该Topic所在区域ID，0-ALIYUN，1-CLOUD，2,3,4
     *  channelName	     String	                该Topic所在区域名称，ALIYUN,CLOUD,...
     *  regionId	     Long	                该Topic所在区域ID，就是ONSRegionList方法获取的内容
     *  regionName	     String	                该topic所在区域名称
     *  topic	         String	                Topic名称
     *  owner	         String	                Topic所有者编号
     *  relation	     Integer	            所有关系编号1--持有者，2--可以发布，4--可以订阅，6--可以发布和订阅
     *  relationName	 String	                所有关系名称，例如持有者、可订阅、可发布、可发布订阅
     *  status	         Integer	            当前状态编号（0 服务中 1 冻结 2 暂停）
     *  statusName	     String	                当前状态别名，服务中，
     *  appkey	         Integer	            null
     *  createTime	     Long	                创建时间
     *  updateTime	     Long	                更新时间
     *  remark	         String	                备注，可以不填
     * </pre>
     *
     * @throws ClientException
     */
    public List<OnsTopicListResponse.PublishInfoDo> showAll(TopicParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设定请求参数
        OnsTopicListRequest request = new OnsTopicListRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        // 查询指定Topic时载入
        if (StringUtils.isNotBlank(param.getTopic())) {
            request.setTopic(param.getTopic());
        }
        OnsTopicListResponse response = client.getAcsResponse(request);
        return response.getData();
    }

    /**
     * 查询指定Topic.
     *
     * @param param     请求参数
     * <p>
     *     请求参数 {@link OnsTopicListRequest}
     * </p>
     * <pre>
     *  名称	            类型	        是否必须	描述
     *  OnsRegionId	    String	    是	    当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *  OnsPlatform	    String	    否	    该请求来源，默认是从POP平台
     *  PreventCache	Long	    是	    用于CSRF校验，设置为系统当前时间即可
     *  Topic	        String	    否	    查询指定Topic时设置，否则查询所有Topic
     * </pre>
     *
     * @return          Topic列表或指定Topic
     * <p>
     *     {@link OnsTopicListResponse}
     * </p>
     * <pre>
     *  名称	             类型	                描述
     *  RequestId	     String	                为公共参数，每个请求独一无二
     *  HelpUrl	         String	                帮助链接
     *  Data	         List(PublishInfoDo)    返回所有已发布的Topic信息列表
     * </pre>
     *
     * <p>
     *     {@link OnsTopicListResponse.PublishInfoDo} 数据结构
     * </p>
     * <pre>
     *  成员	             类型	                描述
     *  id	             Long	                该发布信息在数据库中的ID
     *  channelId	     Integer	            该Topic所在区域ID，0-ALIYUN，1-CLOUD，2,3,4
     *  channelName	     String	                该Topic所在区域名称，ALIYUN,CLOUD,...
     *  regionId	     Long	                该Topic所在区域ID，就是ONSRegionList方法获取的内容
     *  regionName	     String	                该topic所在区域名称
     *  topic	         String	                Topic名称
     *  owner	         String	                Topic所有者编号
     *  relation	     Integer	            所有关系编号1--持有者，2--可以发布，4--可以订阅，6--可以发布和订阅
     *  relationName	 String	                所有关系名称，例如持有者、可订阅、可发布、可发布订阅
     *  status	         Integer	            当前状态编号（0 服务中 1 冻结 2 暂停）
     *  statusName	     String	                当前状态别名，服务中，
     *  appkey	         Integer	            null
     *  createTime	     Long	                创建时间
     *  updateTime	     Long	                更新时间
     *  remark	         String	                备注，可以不填
     * </pre>
     *
     * @throws ClientException
     */
    public OnsTopicGetResponse.PublishInfoDo show(TopicParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设定请求参数
        OnsTopicGetRequest request = new OnsTopicGetRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        List<OnsTopicGetResponse.PublishInfoDo> publishInfoDo = client.getAcsResponse(request).getData();
        if (publishInfoDo.isEmpty()) {
            return null;
        }
        // 获取列表第一个对象
        return publishInfoDo.get(CommonConstant.FIRST_ITEM);
    }

    /**
     * 查询Topic当前位点.
     *
     * @param param     请求参数
     * <p>
     *     请求参数 {@link OnsTopicListRequest}
     * </p>
     * <pre>
     *  名称	            类型	        是否必须	描述
     *  OnsRegionId	    String	    是	    当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *  OnsPlatform	    String	    否	    该请求来源，默认是从POP平台
     *  PreventCache	Long	    是	    用于CSRF校验，设置为系统当前时间即可
     *  Topic	        String	    否	    查询指定Topic时设置，否则查询所有Topic
     *  Detail	        Bool	    否	    是否查询详细的信息，默认不查询
     * </pre>
     *
     * @return          Topic当前位点
     * <p>
     *     {@link OnsTopicListResponse}
     * </p>
     * <pre>
     *  名称	             类型	                描述
     *  RequestId	     String	                为公共参数，每个请求独一无二
     *  HelpUrl	         String	                帮助链接
     *  Data	         List(PublishInfoDo)    返回所有已发布的Topic信息列表
     * </pre>
     *
     * <p>
     *     {@link OnsTopicStatusResponse.Data} 数据结构
     * </p>
     * <pre>
     *  成员	             类型	                描述
     *  totalCount	     Long	                当前Topic的所有队列堆积总和
     *  lastTimeStamp	 Long	                当前Topic的最后更新时间
     *  offsetTable	     List(TopicQueueOffset)	实际各个队列的服务端位点数据
     * </pre>
     *
     * <p>
     *     {@link OnsTopicStatusResponse.Data.TopicQueueOffset} 数据结构
     * </p>
     * <pre>
     *  成员	                 类型	            描述
     *  topic	             String	            Topic名称
     *  brokerName	         String	            所在集群名称
     *  queueId	             Integer	        队列ID
     *  minOffset	         Long	            broker端存储消息最小位点
     *  maxOffset	         Long	            broker端存储消息最大位点
     *  lastUpdateTimestamp	 Long	            最后更新时间
     * </pre>
     *
     * @throws ClientException
     */
    public List<OnsTopicStatusResponse.Data.TopicQueueOffset> showTopicOffset(TopicParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设定请求参数
        OnsTopicStatusRequest request = new OnsTopicStatusRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setTopic(param.getTopic());
        request.setDetail(false);
        // 获得Topic当前位点
        return client.getAcsResponse(request).getData().getOffsetTable();
    }

    /**
     * 创建Topic
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsTopicCreateRequest}
     * </p>
     * <pre>
     *      名称	            类型	        是否必须	    描述
     *      OnsRegionId	    String	    是	        当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	    String	    否	        该请求来源，默认是从POP平台
     *      PreventCache	Long	    是	        用于CSRF校验，设置为系统当前时间即可
     *      Topic	        String	    是	        需要创建的topic名称，在一个用户下不可重复，即使区域不同
     *      Cluster	        String	    否	        创建topic到指定集群下，如果没有则创建到默认集群
     *      QueueNum	    Integer	    否	        指定创建topic单个Broker上配置的队列数
     *      Order	        Boolean	    否	        设置该Topic是否为顺序消息
     *      Qps	            Long	    否	        设置该Topic的QPS估计
     *      Status	        Integer	    否	        Topic申请的状态,（0 服务中 1 冻结 2 暂停）
     *      Remark	        String	    否	        备注，可以不填
     *      Appkey	        String	    否	        应用的Key
     *      AppName	        String	    否	        应用名
     * </pre>
     *
     * @return          返回信息
     * <p>
     *      {@link OnsTopicCreateResponse}
     * </p>
     * <pre>
     *      名称	            类型	        描述
     *      RequestId	    String	    为公共参数，每个请求独一无二
     *      HelpUrl	        String	    帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsTopicCreateResponse create(TopicParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设定请求参数
        OnsTopicCreateRequest request = new OnsTopicCreateRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setQueueNum(param.getQueueNum());
        request.setOrder(param.getOrder());
        request.setQps(param.getQps());
        request.setRemark(param.getRemark());
        request.setStatus(param.getStatus());
        request.setOnsRegionId(param.getOnsRegionId());
        request.setCluster(param.getCluster());
        request.setPreventCache(System.currentTimeMillis());
        return client.getAcsResponse(request);
    }

    /**
     * 根据用户指定的ONS区域，找到用户指定的Topic，删除。
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsTopicDeleteRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	    String	      是	            当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	    String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      Topic	        String	      是	            指定删除的Topic
     *      Cluster	        String	      否	            指定Topic所在的集群名称
     * </pre>
     * @return          返回参数
     * <p>
     *     {@link OnsTopicDeleteResponse}
     * </p>
     * <pre>
     *      名称	            类型	        描述
     *      RequestId	    String	    为公共参数，每个请求独一无二
     *      HelpUrl	        String	    帮助链接
     * </pre>
     *
     * @throws ClientException
     */
    public OnsTopicDeleteResponse delete(TopicParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设定请求参数
        OnsTopicDeleteRequest request = new OnsTopicDeleteRequest();
        request.setCluster(param.getCluster());
        request.setPreventCache(System.currentTimeMillis());
        request.setOnsRegionId(param.getOnsRegionId());
        request.setTopic(param.getTopic());
        return client.getAcsResponse(request);
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
