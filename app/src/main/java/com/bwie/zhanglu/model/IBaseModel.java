package com.bwie.zhanglu.model;

import com.bwie.zhanglu.base.IBaseCallBack;

public interface IBaseModel {

    void HttpGetData(String my_name,String my_password, String regirectApi, IBaseCallBack iBaseCallBack);
}
