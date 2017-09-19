package com.recalllist.util;

import android.content.Context;

/**
 * Created by estevao on 18/09/17.
 */

public class ApplicationUtil {
    private static Context context;

    public static void setContext(Context context) {
        ApplicationUtil.context = context;
    }

    public static Context getContext() {
        return context;
    }


}
