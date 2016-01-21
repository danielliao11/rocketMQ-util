package com.saintdan.util.rocketmq.param;

import java.io.Serializable;

/**
 * 联系人参数
 *
 * TODO ONS管理平台中,可创建多个联系人,现在拼接方法不明.可能会有变化.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/20/16
 * @since JDK1.8
 */
public class ContactsParam implements Serializable {

    private static final long serialVersionUID = -2840099188586979815L;

    private String name;

    private String phone;

    public ContactsParam() {

    }

    public ContactsParam(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
