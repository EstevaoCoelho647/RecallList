package com.recalllist.ui.create_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.recalllist.R;
import com.recalllist.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by estevao on 18/09/17.
 */

public class CreateListActivity extends AppCompatActivity implements CreateListActivityContract.View {
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.linearCode)
    LinearLayout mLinearCode;
    @BindView(R.id.textViewCode)
    TextView mTextViewCode;
    @BindView(R.id.buttonContinue)
    Button mButtonContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        ButterKnife.bind(this);

        CreateListActivityPresenter createListActivityPresenter = new CreateListActivityPresenter();
        createListActivityPresenter.onAttachView(this);

        createListActivityPresenter.generateNewCode();

        mButtonContinue.setVisibility(View.GONE);
        mButtonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void hideProgressDialog() {
        mLinearCode.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressDialog() {
        mLinearCode.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHashCreated(String hash) {
        mLinearCode.setVisibility(View.VISIBLE);
        mTextViewCode.setText(hash);
        mButtonContinue.setVisibility(View.VISIBLE);
    }
}
