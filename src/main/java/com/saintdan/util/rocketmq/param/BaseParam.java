package com.saintdan.util.rocketmq.param;

import java.io.Serializable;

/**
 * 基础参数
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/25/15
 * @since JDK1.8
 */
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 5008569862267684535L;

    // 地域
    private String regionId;

    // 当前查询ONS所在区域，可以通过OnsRegionList方法获取
    private String onsRegionId;

    // 访问KEY
    private String accessKey;

    // 密钥
    private String secretKey;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getOnsRegionId() {
        return onsRegionId;
    }

    public void setOnsRegionId(String onsRegionId) {
        this.onsRegionId = onsRegionId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
