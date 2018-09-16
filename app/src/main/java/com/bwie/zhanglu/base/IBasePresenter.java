package com.bwie.zhanglu.base;

import com.bwie.zhanglu.view.ui.IBaseView;

import java.lang.ref.WeakReference;

public abstract class IBasePresenter<V extends IBaseView> {

    private WeakReference<V> weakReference;

//    绑定视图
    public void  onAttachView(V view){
        weakReference = new WeakReference(view);
    }

//    夺取
    public V getView(){
        return weakReference.get();
    }

//    判断是否绑定
    public boolean isView(){
        return weakReference != null && weakReference.get() != null;
    }

//    销毁
    public void destroy(){
        if (weakReference != null){
            weakReference.clear();
            weakReference = null;

        }    }
}
