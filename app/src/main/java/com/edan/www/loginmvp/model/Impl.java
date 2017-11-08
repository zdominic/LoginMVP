package com.edan.www.loginmvp.model;

/**
 * 创建者     Zhangyu
 * 创建时间   2017/11/7 22:15
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author
 * 更新时间   $Date
 * 更新描述   ${TODO}
 */

public class Impl implements IUserModel {
    private User mInfo;

    @Override
    public void setLoginInfo(User info) {
        mInfo = info;
    }

    @Override
    public User getUser() {
        return mInfo;
    }
}
