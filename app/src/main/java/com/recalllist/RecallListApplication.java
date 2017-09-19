package com.recalllist;

import android.app.Application;

import com.recalllist.util.ApplicationUtil;

/**
 * Created by estevao on 18/09/17.
 */

public class RecallListApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
