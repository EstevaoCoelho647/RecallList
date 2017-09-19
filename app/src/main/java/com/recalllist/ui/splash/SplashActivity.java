package com.recalllist.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.recalllist.R;
import com.recalllist.ui.login.LoginActivity;
import com.recalllist.ui.main.MainActivity;
import com.recalllist.util.Constants;
import com.recalllist.util.SharedPreferencesUtil;

import butterknife.ButterKnife;

/**
 * Created by estevao on 18/09/17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        if (SharedPreferencesUtil.getSharedPreferences().getString(Constants.HASH_SHARED_PREFERENCES, null) != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
