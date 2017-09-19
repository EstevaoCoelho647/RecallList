package com.recalllist.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.recalllist.R;
import com.recalllist.ui.create_list.CreateListActivity;
import com.recalllist.ui.find_list.FindListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 18/09/17.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.button_new)
    Button mButtonNew;
    @BindView(R.id.button_find)
    Button mButtonFind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        ButterKnife.bind(this);

        mButtonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, FindListActivity.class);
                startActivity(intent);
            }
        });
        mButtonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateListActivity.class);
                startActivity(intent);
            }
        });
    }
}
