package com.edan.www.loginmvp.presenter;

import com.edan.www.loginmvp.model.IUserModel;
import com.edan.www.loginmvp.model.Impl;
import com.edan.www.loginmvp.model.User;
import com.edan.www.loginmvp.view.ILoginView;

/**
 * 创建者     Zhangyu
 * 创建时间   2017/11/7 22:16
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author
 * 更新时间   $Date
 * 更新描述   ${TODO}
 */

public class LoginPersenter {

    private ILoginView mView;
    private IUserModel mModel;

    public LoginPersenter(ILoginView view) {
        this.mView = view;
        mModel = new Impl();
    }

    private void success() {
        if (mView != null) {
            mView.success();
        }
    }

    private void failed() {
        if (mView != null) {
            mView.failed();
        }
    }

    //提供给view层调用
    public void requestNet() {
        getNetData();
    }

    public void getNetData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mView.showDialog();
                    Thread.sleep(3000);
                    User user = new User(mView.getUsername(), mView.getPassword());
                    mModel.setLoginInfo(user);
                    User user1 = mModel.getUser();
                    if (user1.getUsername().endsWith("zy")&&user1.getPassword().endsWith("123")){
                        mView.success();
                    }else {
                        mView.failed();
                    }
                    mView.dissMissDialog();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    public void clearData() {
        if (mView != null) {
            mView.clear();
        }
    }
}
