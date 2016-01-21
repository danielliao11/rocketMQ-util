package com.saintdan.util.rocketmq.ons.consumption;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.OnsConsumerAccumulateRequest;
import com.aliyuncs.ons.model.v20151214.OnsConsumerAccumulateResponse;
import com.aliyuncs.ons.model.v20151214.OnsConsumerGetConnectionRequest;
import com.aliyuncs.ons.model.v20151214.OnsConsumerGetConnectionResponse;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.BaseMsgParam;

/**
 * 消费管理
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 12/4/15
 * @since JDK1.8
 */
public class Consumption {

    /**
     * 查询消费堆积
     * <p>
     *      根据用户指定的ONS区域，查询指定Consumer的堆积情况，包含统计信息等
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsConsumerAccumulateRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ConsumerId	     String	      是	            需要查询的消费端CID
     *      Detail	         Boolean	  否	            是否查询详细信息，包含各个队列的情况，默认为否
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsConsumerAccumulateResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         String	                    指定consumer的消费堆积情况
     * </pre>
     *
     * <p>
     *     {@link OnsConsumerAccumulateResponse.Data} 数据结构
     * </p>
     * <pre>
     *      成员	             类型	                    描述
     *      OnLine	         Boolean	                当前消费者是否在线
     *      TotalDiff	     Long	                    总堆积数，各个topic的队列之和
     *      ConsumeTps	     Float	                    当前消费TPS
     *      LastTimestamp	 Long	                    最后更新时刻
     *      DelayTime	     Long	                    延迟时间
     *      DetailInTopicList	List(OnsConsumerAccumulateResponse.Data.DetailInTopicDo)	各个Topic具体情况
     * </pre>
     *
     * <p>
     *     {@link OnsConsumerAccumulateResponse.Data.DetailInTopicDo} 数据结构
     * </p>
     * <pre>
     *      成员	             类型	                    描述
     *      Topic	         String	                    Topic名称
     *      ConsumeTps	     Float	                    消费TPS
     *      LastTimestamp	 Long	                    最后更新时刻
     *      DelayTime	     Long	                    延迟时间
     *      OffsetList	     List(ConsumeQueueOffset)	各个队列的具体情况
     * </pre>
     *
     * <P>
     *     {@link OnsConsumerAccumulateResponse.Data.DetailInTopicDo.ConsumeQueueOffset} 数据结构
     * </P>
     * <pre>
     *      成员	             类型	                    描述
     *      Topic	         Integer	                Topic名称
     *      BrokerName	     Long	                    所在Broker名称
     *      QueueId	         Long	                    队列编号
     *      BrokerOffset	 String	                    该队列所在Broker的最大Offset
     *      ConsumerOffset	 Long	                    该队列consumer端的最大Offset
     *      LastTimestamp	 String	                    最后更新时刻
     * </pre>
     *
     * @throws ClientException
     */
    public OnsConsumerAccumulateResponse accumulate(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsConsumerAccumulateRequest request = new OnsConsumerAccumulateRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setDetail(true);
        request.setConsumerId(param.getClientId());
        return client.getAcsResponse(request);
    }

    /**
     * 查询Consumer连接
     * <p>
     *     根据用户指定的ONS区域，查询指定Consumer集群的连接信息，如果该集群没有上线，则会抛出BIZ_CONSUMER_NOT_ONLINE异常
     * </p>
     *
     * @param param         请求参数
     * <p>
     *     {@link OnsConsumerGetConnectionRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	                描述
     *      OnsRegionId	     String	      是	                        当前查询ONS所在区域，可以通过ONSRegionList方法获取
     *      OnsPlatform	     String	      否	                        该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	                        用于CSRF校验，设置为系统当前时间即可
     *      ConsumerId	     String	      是	                        需要查询的消费端CID
     * </pre>
     *
     * @return              返回参数
     * <p>
     *     {@link OnsConsumerGetConnectionResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         String	                    指定consumer的连接信息
     * </pre>
     *
     * <p>
     *     {@link OnsConsumerGetConnectionResponse.Data.ConnectionDo} 数据结构
     * </p>
     * <pre>
     *      成员	             类型	                    描述
     *      ClientId	     String	                    消费实例的ID
     *      ClientAddr	     String	                    该消费实例的地址和端口
     *      Language	     String	                    消费端语言
     *      Version	         String	                    消费端版本
     * </pre>
     *
     * @throws ClientException
     */
    public OnsConsumerGetConnectionResponse getConnection(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsConsumerGetConnectionRequest request = new OnsConsumerGetConnectionRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setConsumerId(param.getClientId());
        return client.getAcsResponse(request);
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
