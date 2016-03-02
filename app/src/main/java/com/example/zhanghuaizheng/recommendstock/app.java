package com.example.zhanghuaizheng.recommendstock;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zhanghuaizheng on 15/12/30.
 */
public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
