package com.edan.www.loginmvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edan.www.loginmvp.presenter.LoginPersenter;
import com.edan.www.loginmvp.view.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.username_et)
    EditText mUsernameEt;
    @BindView(R.id.password_et)
    EditText mPasswordEt;
    @BindView(R.id.login_button)
    Button mLoginButton;
    @BindView(R.id.clear_button)
    Button mClearButton;
    private Unbinder mBind;
    private LoginPersenter mPersenter;
    private ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(MainActivity.this);
        mPersenter = new LoginPersenter(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @OnClick({R.id.login_button, R.id.clear_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:    //登录
                mPersenter.requestNet();
                break;
            case R.id.clear_button:     //清空输入框
                mPersenter.clearData();
                break;
        }
    }


    @Override
    public String getUsername() {
        return mUsernameEt.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEt.getText().toString();
    }

    @Override
    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void failed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void clear() {
        mUsernameEt.setText("");
        mPasswordEt.setText("");
    }

    @Override
    public void showDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressBar != null && mProgressBar.isShowing()) {
                    mProgressBar.dismiss();
                }
                mProgressBar = ProgressDialog.show(MainActivity.this, "", "正在获取中...");
            }
        });
    }

    @Override
    public void dissMissDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressBar != null && mProgressBar.isShowing()) {
                    mProgressBar.dismiss();
                }
            }
        });
    }


}
