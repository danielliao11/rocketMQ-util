package com.saintdan.util.rocketmq.ons.message;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.*;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.BaseMsgParam;
import org.apache.commons.lang3.StringUtils;

/**
 * 消息类
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/27/15
 * @since JDK1.8
 */
public class Message {

    /**
     * 发送信息
     * <p>
     *      根据用户指定的ONS区域，发送一条消息，发消息时至少需要指定Topic、发送集群名PID以及消息体。发消息之前，发布关系和Topic必须存在，否则提示没有权限。
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsMessageSendRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ProducerId	     String	      是	            消息发送集群的PID
     *      Topic	         String	      是	            消息的Topic
     *      Tag	             String	      否	            该消息的过滤标签
     *      Key	             String	      否	            消息的Key，尽量保证全局唯一
     *      Message	         String	      是	            消息体内容
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsMessageSendResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         String	                    查询结果集合
     * </pre>
     */
    public OnsMessageSendResponse send(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsMessageSendRequest request = new OnsMessageSendRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setProducerId(param.getUserId());
        request.setMessage(param.getMessage());
        if (StringUtils.isNotBlank(param.getTag())) {
            request.setTag(param.getTag());
        }
        if (StringUtils.isNotBlank(param.getKey())) {
            request.setKey(param.getKey());
        }
        return client.getAcsResponse(request);
    }

    /**
     * 推送消费消息
     * <p>
     *      根据用户指定的ONS区域，让ONS将消息推送到指定的消费客户端上，因此需要指定推送的目标客户端信息。
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsMessagePushRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      ConsumerId	     String	      是	            消息发送集群的PID
     *      ClientId	     String	      是	            消费端实例的ID，类似于IP@端口号
     *      MsgId	         String	      是	            指定消息的MsgID编号
     *      Topic	         String	      是	            消息的Topic
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsMessagePushResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     * </pre>
     */
    public OnsMessagePushResponse push(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsMessagePushRequest request = new OnsMessagePushRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setConsumerId(param.getUserId());
        request.setClientId(param.getClientId());
        request.setMsgId(param.getMsgId());
        request.setTopic(param.getTopic());
        return client.getAcsResponse(request);
    }

    /**
     * 查询消息轨迹
     * <p>
     *      根据用户指定的MsgID以及Topic查询该消息的状态，主要是消费状态以及是否有异常
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsMessageTraceRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      MsgId	         String	      是	            指定消息的MsgID编号
     *      Topic	         String	      是	            消息的Topic
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsMessageTraceResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(MessageTrack)	        查询结果集合
     * </pre>
     *
     * <p>
     *     {@link OnsMessageTraceResponse.MessageTrack} 数据结构
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      ConsumerGroup	 String	                    消费集群名称
     *      TrackType	     String	                    当前状态
     *      ExceptionDesc	 String	                    异常描述
     * </pre>
     */
    public OnsMessageTraceResponse track(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsMessageTraceRequest request = new OnsMessageTraceRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setMsgId(param.getMsgId());
        return client.getAcsResponse(request);
    }

    /**
     * 根据Key查询消息
     * <p>
     *      根据Key和Topic查询消息，目前暂未开通该功能，查询时会返回找不到该消息
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsMessageTraceRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      Topic	         String	      是	            需要查询的消息的Topic
     *      Key	             String	      否	            需要查询的消息的MsgKey
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsMessageGetByKeyResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(MessageTrack)	        查询结果集合
     * </pre>
     *
     * <p>
     *     {@link OnsMessageGetByKeyResponse.OnsRestMessageDo} 数据结构
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      Topic	         String	                    消息的Topic
     *      Flag	         Integer	                null
     *      PropertyList	 List(MessageProperty)	    消息属性列表
     *      Body	         String	                    消息体
     *      QueueId	         Integer	                所在Queue的编号
     *      StoreSize	     Integer	                消息大小
     *      QueueOffset	     Long	                    在队列中的偏移地址
     *      SysFlag	         Integer	                null
     *      BornTimestamp	 Long	                    生成时间戳
     *      BornHost	     String	                    生成该消息的客户端实例
     *      StoreTimestamp	 Long	                    被Broker存储的时间戳
     *      StoreHost	     String	                    存储该消息的服务器实例
     *      MsgId	         String	                    消息ID
     *      CommitLogOffset	 Long	                    消息落盘时在存储文件中的偏移地址
     *      BodyCRC	         Integer	                消息体CRC校验值
     *      ReconsumeTimes	 Integer	                消息重试消费的次数
     *      PreparedTransactionOffset	Long	        如果是事务消息，指的是提交给服务器的事务消息ID
     * </pre>
     */
    public OnsMessageGetByKeyResponse searchByKey(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsMessageGetByKeyRequest request = new OnsMessageGetByKeyRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        request.setKey(param.getKey());
        return client.getAcsResponse(request);
    }

    /**
     * 根据MsgID查询消息
     * <p>
     *      根据MsgID查询消息
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsMessageGetByMsgIdRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      MsgId	         String	      是	            需要查询的消息的MsgId
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsMessageGetByMsgIdResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         Data	                    查询结果集合
     * </pre>
     *
     * <p>
     *     {@link OnsMessageGetByMsgIdResponse.Data} 数据结构
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      Topic	         String	                    消息的Topic
     *      Flag	         Integer	                null
     *      PropertyList	 List(MessageProperty)	    消息属性列表
     *      Body	         String	                    消息体
     *      QueueId	         Integer	                所在Queue的编号
     *      StoreSize	     Integer	                消息大小
     *      QueueOffset	     Long	                    在队列中的偏移地址
     *      SysFlag	         Integer	                null
     *      BornTimestamp	 Long	                    生成时间戳
     *      BornHost	     String	                    生成该消息的客户端实例
     *      StoreTimestamp	 Long	                    被Broker存储的时间戳
     *      StoreHost	     String	                    存储该消息的服务器实例
     *      MsgId	         String	                    消息ID
     *      CommitLogOffset	 Long	                    消息落盘时在存储文件中的偏移地址
     *      BodyCRC	         Integer	                消息体CRC校验值
     *      ReconsumeTimes	 Integer	                消息重试消费的次数
     *      PreparedTransactionOffset	Long	        如果是事务消息，指的是提交给服务器的事务消息ID
     * </pre>
     */
    public OnsMessageGetByMsgIdResponse searchByMsgId(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsMessageGetByMsgIdRequest request = new OnsMessageGetByMsgIdRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setMsgId(param.getMsgId());
        return client.getAcsResponse(request);
    }

    /**
     * 根据Topic查询消息
     * <p>
     *      根据Topic查询消息状态，默认从该topic的各个队列，拉取最新的数据，每个队列最多4条。
     * </p>
     *
     * @param param     请求参数
     * <p>
     *      {@link OnsMessageGetByTopicRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      Topic	         String	      是	            需要查询的消息的Topic
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsMessageGetByTopicResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(MessageTrack)	        查询结果集合
     * </pre>
     *
     * <p>
     *     {@link OnsMessageGetByTopicResponse.OnsRestMessageDo} 数据结构
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      Topic	         String	                    消息的Topic
     *      Flag	         Integer	                null
     *      PropertyList	 List(MessageProperty)	    消息属性列表
     *      Body	         String	                    消息体
     *      QueueId	         Integer	                所在Queue的编号
     *      StoreSize	     Integer	                消息大小
     *      QueueOffset	     Long	                    在队列中的偏移地址
     *      SysFlag	         Integer	                null
     *      BornTimestamp	 Long	                    生成时间戳
     *      BornHost	     String	                    生成该消息的客户端实例
     *      StoreTimestamp	 Long	                    被Broker存储的时间戳
     *      StoreHost	     String	                    存储该消息的服务器实例
     *      MsgId	         String	                    消息ID
     *      CommitLogOffset	 Long	                    消息落盘时在存储文件中的偏移地址
     *      BodyCRC	         Integer	                消息体CRC校验值
     *      ReconsumeTimes	 Integer	                消息重试消费的次数
     *      PreparedTransactionOffset	Long	        如果是事务消息，指的是提交给服务器的事务消息ID
     * </pre>
     */
    public OnsMessageGetByTopicResponse searchByTopic(BaseMsgParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsMessageGetByTopicRequest request = new OnsMessageGetByTopicRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        request.setTopic(param.getTopic());
        return client.getAcsResponse(request);
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
