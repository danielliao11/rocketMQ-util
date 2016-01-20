package com.saintdan.util.rocketmq.param;

import com.saintdan.util.rocketmq.enums.WarnOperateType;

/**
 * 报警参数
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 1/20/16
 * @since JDK1.8
 */
public class AdminWarningParam extends BaseMsgParam {

    private static final long serialVersionUID = 6904366375932935650L;

    // 管理账户ID
    private String adminAccount;

    // 操作类型
    private WarnOperateType type;

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public WarnOperateType getType() {
        return type;
    }

    public void setType(WarnOperateType type) {
        this.type = type;
    }
}
