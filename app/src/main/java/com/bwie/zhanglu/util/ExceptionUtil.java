package com.bwie.zhanglu.util;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

public class ExceptionUtil implements Thread.UncaughtExceptionHandler {
    private static final ExceptionUtil ourInstance = new ExceptionUtil();
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public static ExceptionUtil getInstance() {
        return ourInstance;
    }

    private ExceptionUtil() {
    }

    private Context context;

    public void initException(Context context){
//        获取默认异常捕获
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
//        设置
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.i("aaa",e+""+"................."+t+"");
        Intent intent = new Intent("com.bwie.zhanglu.view.ui.errer");

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);

        Process.killProcess(Process.myPid());
    }
}
