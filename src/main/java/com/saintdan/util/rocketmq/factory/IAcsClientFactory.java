package com.saintdan.util.rocketmq.factory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.saintdan.util.rocketmq.param.BaseParam;

/**
 * IAcs client 工厂
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/26/15
 * @since JDK1.8
 */
public class IAcsClientFactory {

    public IAcsClient newIAcsClient(BaseParam param) {
        // 初始化profile
        IClientProfile profile = DefaultProfile.getProfile(param.getRegionId(), param.getAccessKey(), param.getSecretKey());
        // 初始化client
        return new DefaultAcsClient(profile);
    }
}
