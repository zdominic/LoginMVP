package com.edan.www.loginmvp.view;

/**
 * 创建者     Zhangyu
 * 创建时间   2017/11/7 22:07
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author
 * 更新时间   $Date
 * 更新描述   ${TODO}
 */

public interface ILoginView {
    String getUsername();

    String getPassword();

    void success();

    void failed();

    void clear();

    void showDialog();

    void dissMissDialog();

}
