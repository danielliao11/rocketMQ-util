package com.saintdan.util.rocketmq.param;

/**
 * 普通消息参数
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/19/15
 * @since JDK1.8
 */
public class SimpleMsgParam extends BaseMsgParam {

    private static final long serialVersionUID = -8354617726159214610L;

    private String publisherGroup;

    private String consumerGroup;

    private FilterParam[] filterParams;

    public String getPublisherGroup() {
        return publisherGroup;
    }

    public void setPublisherGroup(String publisherGroup) {
        this.publisherGroup = publisherGroup;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public FilterParam[] getFilterParams() {
        return filterParams;
    }

    public void setFilterParams(FilterParam[] filterParams) {
        this.filterParams = filterParams;
    }
}
