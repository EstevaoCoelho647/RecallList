package com.recalllist.util;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.recalllist.util.Constants.SHARED_PREFERENCES;

/**
 * Created by estevao on 11/09/17.
 */

public class SharedPreferencesUtil {

    public static SharedPreferences getSharedPreferences() {
        return ApplicationUtil.getContext().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getSharedPreferencesEdit() {
        return ApplicationUtil.getContext().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE).edit();
    }
}
