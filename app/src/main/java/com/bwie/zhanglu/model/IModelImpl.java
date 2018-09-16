package com.bwie.zhanglu.model;

import com.bwie.zhanglu.base.IBaseCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IModelImpl implements IBaseModel {


    @Override
    public void HttpGetData(String my_name, String my_password, String regirectApi, final IBaseCallBack iBaseCallBack) {
        FormBody body = new FormBody
                .Builder()
                .add("mobile",my_name)
                .add("password",my_password)
                .build();

        Request build = new Request.Builder()
                .url(regirectApi)
                .post(body)
                .build();

        new OkHttpClient().newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        iBaseCallBack.onErrer();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        iBaseCallBack.onSuccess(response.body().string());
                    }
                });

    }
}
