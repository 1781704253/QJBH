package com.bwie.zhanglu.presenter;

import com.bwie.zhanglu.base.IBasePresenter;

public abstract class IPresenter extends IBasePresenter {


    public abstract void regirectGetData(String my_name, String my_password);


    public abstract void loginGetData(String my_name,String my_password);
}
