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
