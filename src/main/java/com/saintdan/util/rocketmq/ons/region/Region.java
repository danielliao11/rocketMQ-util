package com.saintdan.util.rocketmq.ons.region;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.ons.model.v20151214.OnsRegionListRequest;
import com.aliyuncs.ons.model.v20151214.OnsRegionListResponse;
import com.saintdan.util.rocketmq.factory.IAcsClientFactory;
import com.saintdan.util.rocketmq.param.BaseParam;

import java.util.List;

/**
 * 地区类
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/25/15
 * @since JDK1.8
 */
public class Region {

    /**
     * 获取用户的Region信息
     * <p>
     *     根据用户指定的ONS区域，查询用户所涉及的所有的ONS的region信息。该region信息用于在发送其他请求时使用。因为对ONS的其他操作都是要基于region来查询。
     * </p>
     *
     * @param param     请求参数
     * <p>
     *     {@link OnsRegionListRequest}
     * </p>
     * <pre>
     *      名称	             类型	      是否必须	    描述
     *      OnsRegionId	     String	      是	            当前查询ONS所在区域
     *      OnsPlatform	     String	      否	            该请求来源，默认是从POP平台
     *      PreventCache	 Long	      是	            用于CSRF校验，设置为系统当前时间即可
     * </pre>
     *
     * @return          返回参数
     * <p>
     *     {@link OnsRegionListResponse}
     * </p>
     * <pre>
     *      名称	             类型	                    描述
     *      RequestId	     String	                    为公共参数，每个请求独一无二
     *      HelpUrl	         String	                    帮助链接
     *      Data	         List(PublishInfoDo)	    查询到的集群信息
     * </pre>
     *
     * <p>
     *     {@link OnsRegionListResponse.RegionDo} 数据结构
     * </p>
     * <pre>
     *      成员	             类型	                描述
     *      Id	             Long	                数据库中编号
     *      RegionId	     String	                regionId名称
     *      RegionName	     String	                region别名
     *      ChannelId	     Long	                所在channel编号
     *      ChannelName	     String	                所在channel别名
     *      Owner	         String	                null
     *      Cluster	         String	                集群信息
     *      Status	         Integer	            状态值
     *      IsShare	         Integer	            是否共享
     *      CreateTime	     Long	                创建时间
     *      UpdateTime	     Long	                最后更新时间
     * </pre>
     *
     * @throws ClientException
     */
    public List<OnsRegionListResponse.RegionDo> showAll(BaseParam param) throws ClientException {
        // 初始化client
        IAcsClient client = clientFactory.newIAcsClient(param);
        // 设置参数
        OnsRegionListRequest request = new OnsRegionListRequest();
        request.setAcceptFormat(FormatType.JSON);
        request.setPreventCache(System.currentTimeMillis());
        OnsRegionListResponse response = client.getAcsResponse(request);
        return response.getData();
    }

    private IAcsClientFactory clientFactory = new IAcsClientFactory();
}
