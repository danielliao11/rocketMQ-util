package com.saintdan.util.rocketmq.param;

/**
 * Topic参数
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/25/15
 * @since JDK1.8
 */
public class TopicParam extends BaseParam {

    private static final long serialVersionUID = -5965341559316491898L;

    // 该请求来源，默认是从POP平台
    private String onsPlatform;

    // 用于CSRF校验，设置为系统当前时间即可
    private Long preventCache;

    // Topic名称，在一个用户下不可重复，即使区域不同
    private String topic;

    // Topic指定集群
    private String cluster;

    // Topic单个Broker上配置的队列数
    private Integer queueNum;

    // 设置该Topic是否为顺序消息
    private Boolean order;

    // 设置该Topic的QPS估计
    private Long qps;

    // Topic申请的状态,（0 服务中 1 冻结 2 暂停）
    private Integer status;

    // 备注，可以不填
    private String remark;

    // 应用的Key
    private String appkey;

    // 应用名
    private String appName;

    public String getOnsPlatform() {
        return onsPlatform;
    }

    public void setOnsPlatform(String onsPlatform) {
        this.onsPlatform = onsPlatform;
    }

    public Long getPreventCache() {
        return preventCache;
    }

    public void setPreventCache(Long preventCache) {
        this.preventCache = preventCache;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public Integer getQueueNum() {
        return queueNum;
    }

    public void setQueueNum(Integer queueNum) {
        this.queueNum = queueNum;
    }

    public Boolean getOrder() {
        return order;
    }

    public void setOrder(Boolean order) {
        this.order = order;
    }

    public Long getQps() {
        return qps;
    }

    public void setQps(Long qps) {
        this.qps = qps;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
