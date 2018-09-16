package com.bwie.zhanglu.view.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.bwie.zhanglu.presenter.IPresenterImpl;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements IBaseView{

    @ViewInject(R.id.ed_name)
    private EditText ed_name;
        @ViewInject(R.id.ed_pwd)
    private EditText ed_pwd;

        private IPresenterImpl iPresenter;
    private boolean flag;
    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//            初始化布局
        x.view().inject(this);

//        实现IPresenterImpl
        iPresenter = new IPresenterImpl();

//        绑定弱引用
        iPresenter.onAttachView(this);

//        定义SharedPreferences
        config = getSharedPreferences("config", MODE_PRIVATE);

        flag = config.getBoolean("flag", false);

        if (flag){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }


    }

    /***
     * 点击登录以及注册
     *
     */
    @Event({R.id.text_regirect,R.id.btn_login})
    private void onClick(View view){

//        获取值
        String my_name = ed_name.getText().toString();
        String my_password = ed_pwd.getText().toString();


        switch (view.getId()){
//            注册
            case R.id.text_regirect:
                iPresenter.regirectGetData(my_name,my_password);

                break;

//                登录
            case R.id.btn_login:
                iPresenter.loginGetData(my_name,my_password);

                break;
        }
    }

    @Override
    public void loginSuccess(String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                flag = true;
                SharedPreferences.Editor edit = config.edit();
                edit.putBoolean("flag",flag);
                edit.commit();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void errer(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void regirectSuccess(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPresenter.isView()){
            iPresenter.destroy();
        }
    }
}
