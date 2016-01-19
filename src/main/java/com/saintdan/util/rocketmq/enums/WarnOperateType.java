package com.saintdan.util.rocketmq.enums;

/**
 * 警告操作类型
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/19/16
 * @since JDK1.8
 */
public enum WarnOperateType implements IntentStateWithDescription {

    VIEW("view"),
    CREATE("create"),
    EDIT("edit"),
    DELETE("delete"),
    ENABLE("enable"),
    DISABLE("disable");

    private final String description;

    WarnOperateType(String description) {
        this.description = description;
    }

    @Override
    public String description() {
        return description;
    }
}
