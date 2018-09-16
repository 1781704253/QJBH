package com.bwie.zhanglu.presenter;
import android.text.TextUtils;
import com.bwie.zhanglu.base.IBaseCallBack;
import com.bwie.zhanglu.model.IModelImpl;
import com.bwie.zhanglu.model.bean.UserBean;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPresenterImpl extends IPresenter {
//        正则
    private String name_rex ="^[1][3,4,5,7,8][0-9]{9}$";
    private String password_rex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,8}$";

//    调用Model
    private IModelImpl iModel;

    private String regirectApi = "https://www.zhaoapi.cn/quarter/register";
    private String loginApi = "https://www.zhaoapi.cn/user/login";

    public IPresenterImpl() {
        iModel = new IModelImpl();
    }

    /***
     * 注册
     * @param my_name
     * @param my_password
     */
    @Override
    public void regirectGetData(String my_name, String my_password) {

        boolean name_flag = yan(name_rex, my_name);
        boolean password_flag = yan(password_rex, my_password);

        if (name_flag){
            if (password_flag){
                iModel.HttpGetData(my_name, my_password, regirectApi, new IBaseCallBack() {
                    @Override
                    public void onSuccess(String result) {

                        Gson gson = new Gson();

                        UserBean userBean = gson.fromJson(result, UserBean.class);

                        if(TextUtils.equals(userBean.getMsg(),"注册成功")){
                            getView().regirectSuccess(result);
                        }else{
                            getView().errer("用户已经注册");
                        }

                    }

                    @Override
                    public void onErrer() {

                    }
                });
            }else{
                getView().errer("密码错误");
            }
        }else{
            getView().errer("用户名错误");
        }


    }

    /***
     * 登录
     * @param my_name
     * @param my_password
     */
    @Override
    public void loginGetData(String my_name, String my_password) {
        boolean name_flag = yan(name_rex, my_name);
        boolean password_flag = yan(password_rex, my_password);

        if (name_flag){
            if (password_flag){
                iModel.HttpGetData(my_name, my_password, loginApi, new IBaseCallBack() {
                    @Override
                    public void onSuccess(String result) {

                        Gson gson = new Gson();

                        UserBean userBean = gson.fromJson(result, UserBean.class);

                        if(TextUtils.equals(userBean.getMsg(),"登录成功")){
                           getView().loginSuccess(result);
                        }else{
                            getView().errer("用户名或密码错误");
                        }

                    }

                    @Override
                    public void onErrer() {
                        getView().errer("网络异常");
                    }
                });
            }else{
                getView().errer("密码错误");
            }
        }else{
            getView().errer("用户名错误");
        }
    }

    /***
     * 正则验证
     * @param rex
     * @param str
     * @return
     */
    private boolean yan(String rex,String str){
        Pattern compile = Pattern.compile(rex);

        Matcher matcher = compile.matcher(str);

        return matcher.matches();


    }
}
