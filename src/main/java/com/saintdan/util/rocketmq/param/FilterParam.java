package com.saintdan.util.rocketmq.param;

import java.io.Serializable;

/**
 * 话题,标签过滤条件
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 11/20/15
 * @since JDK1.8
 */
public class FilterParam implements Serializable {

    private static final long serialVersionUID = 2529079727557341081L;

    // 话题
    private String topic;

    // 标签
    private String tag;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
