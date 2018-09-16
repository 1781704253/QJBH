package com.bwie.zhanglu.application;

import android.app.Application;

import com.bwie.zhanglu.util.ExceptionUtil;

import org.xutils.x;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        初始化
        x.Ext.init(this);

        ExceptionUtil.getInstance().initException(this);
    }
}
