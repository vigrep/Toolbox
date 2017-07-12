package com.wcb.toolbox;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * Created by RS on 2017/7/12.
 */

public class WcbApplication extends Application {

    private static Context appContext;       //全局context
    private static Activity topActivity;        //最上层的Activity

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }

    public Context getAppContext() {
        return appContext;
    }

    public Activity getTopActivity() {
        return  topActivity;
    }

    public void setTopActivity(Activity activity) {
        if (activity == null)
            return;
        topActivity = activity;
    }

}
