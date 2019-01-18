package com.zt.vpn.assistant;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.zt.vpn.assistant.network.RequestManager;


public class ZtApplication extends Application {

    private static ZtApplication mInstance;

    /**
     * 获取context
     *
     * @return
     */
    public static Context getInstance() {
        if (mInstance == null) {
            mInstance = new ZtApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RequestManager.init(getApplicationContext());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
