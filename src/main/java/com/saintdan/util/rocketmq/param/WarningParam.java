package com.saintdan.util.rocketmq.param;

import java.util.Arrays;
import java.util.List;

/**
 * 报警参数
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/20/16
 * @since JDK1.8
 */
public class WarningParam extends BaseMsgParam {

    private static final long serialVersionUID = 6904366375932935650L;

    // 联系人信息,姓名和手机，以双竖线分割
    private ContactsParam contacts;

    // 报警的阈值
    private String threshold;

    public ContactsParam getContacts() {
        return contacts;
    }

    public void setContacts(ContactsParam contacts) {
        this.contacts = contacts;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    /**
     * 将联系人列表转为String
     *
     * @return      联系人String
     */
    public String contactsStr() {
        final String DOUBLE_VERTICAL_BAR = "||";
        return new String(new StringBuffer(this.contacts.getName()).append(DOUBLE_VERTICAL_BAR).append(this.contacts.getPhone()));
    }
}
