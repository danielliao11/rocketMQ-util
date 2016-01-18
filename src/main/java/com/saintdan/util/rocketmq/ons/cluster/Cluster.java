package com.saintdan.util.rocketmq.ons.cluster;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.OnsClusterListRequest;
import com.aliyuncs.ons.model.v20151214.OnsClusterListResponse;
import com.aliyuncs.ons.model.v20151214.OnsClusterNamesRequest;
import com.aliyuncs.ons.model.v20151214.OnsClusterNamesResponse;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.BaseParam;

/**
 * 集群
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/18/16
 * @since JDK1.8
 */
public class Cluster {

    /**
     * 查询集群列表
     * <p>
     *     根据用户指定的ONS区域，查询当前Regin下所有集群信息，也可以查询指定集群
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsClusterListRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域，可以通过OnsRegionList方法获取
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     *      Cluster	         String	      是	            如果设置则查询指定集群，否则查询全部集群
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsClusterListResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(PublishInfoDo)	    查询到的集群信息
     * </pre>
     *
     * <p>
     *     {@link OnsClusterListResponse.ClusterInfoDataDo} 数据结构
     * </p>
     * <pre>
     *      成员	             类型	                描述
     *      ClusterName	     String	                当前Broker所属集群
     *      BrokerInfoList	 BrokerInfoList	List(BrokerInfoDataDo)	Broker名称
     * </pre>
     *
     * <p>
     *     {@link com.aliyuncs.ons.model.v20151214.OnsClusterListResponse.ClusterInfoDataDo.BrokerInfoDataDo} 数据结构
     * </p>
     * <pre>
     *      成员	             类型	                描述
     *      ClusterName	     String	                当前Broker所属集群
     *      BrokerName	     String	                Broker名称
     *      BrokerId	     Integer	            Broker编号
     *      BrokerAddr	     String	                Broker地址，为IP：端口号
     *      BrokerIp	     String	                Broker地址
     *      Version	         String	                Broker版本号
     *      InTPS	         Float	                Broker当前写入TPS
     *      OutTPS	         Float	                Broker当前读取TPS
     *      InTotalYest	     Float	                昨天一天的写入统计
     *      OutTotalYest	 Float	                昨天一天的读取统计
     *      InTotalToday	 Float	                今天一天的读取统计
     *      OutTotalToday	 Float	                今天一天的读取统计
     * </pre>
     *
     * @throws ClientException
     */
    public OnsClusterListResponse show(BaseParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsClusterListRequest request = new OnsClusterListRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        return client.getAcsResponse(request);
    }

    /**
     * 查询集群名称列表
     * <p>
     *     根据用户指定的ONS区域，查询当前Regin下所有集群名称
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsClusterNamesRequest}
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
     *     {@link OnsClusterListResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(PublishInfoDo)	    查询到的集群信息
     * </pre>
     *
     * @throws ClientException
     */
    public OnsClusterNamesResponse showNames(BaseParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsClusterNamesRequest request = new OnsClusterNamesRequest();
        request.setOnsRegionId(param.getOnsRegionId());
        request.setPreventCache(System.currentTimeMillis());
        request.setAcceptFormat(FormatType.JSON);
        return client.getAcsResponse(request);
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
