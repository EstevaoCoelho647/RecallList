package com.recalllist.ui.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.recalllist.R;
import com.recalllist.domain.entity.Recall;
import com.recalllist.ui.main.adapter.RecallListAdapter;
import com.recalllist.ui.splash.SplashActivity;
import com.recalllist.util.Constants;
import com.recalllist.util.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.linearCode)
    LinearLayout mLinearCode;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    MainActivityPresenter mMainActivityPresenter;
    RecallListAdapter mRecallListAdapter;
    @BindView(R.id.textViewCode)
    TextView mTextViewCode;
    @BindView(R.id.buttonShare)
    ImageView mButtonShare;
    @BindView(R.id.buttonClose)
    ImageView mButtonClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMainActivityPresenter = new MainActivityPresenter();
        mMainActivityPresenter.onAttachView(this);

        mTextViewCode.setText(SharedPreferencesUtil.getSharedPreferences()
                .getString(Constants.HASH_SHARED_PREFERENCES, null));

        mMainActivityPresenter.getMyRecallList();
        mRecallListAdapter = new RecallListAdapter(mMainActivityPresenter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecallListAdapter.addNewItem();
            }
        });

        mLinearCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setCodeToClipboard();
                return true;
            }
        });

        mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCodeToClipboard();
            }
        });

        mButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesUtil.getSharedPreferencesEdit().putString(Constants.HASH_SHARED_PREFERENCES, null).apply();
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setCodeToClipboard() {
        String code = mTextViewCode.getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("to share code", code);
        clipboard.setPrimaryClip(clip);
        Snackbar.make(mLinearCode, "Code copied to clipboard", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressDialog() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetMyRecallListSuccess(List<Recall> recallList) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mRecallListAdapter);
            mRecallListAdapter.setRecallList(recallList);
    }

    @Override
    public void onGetMyRecallListError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecallDeleted() {
        Snackbar.make(mLinearCode, "Recall deleted", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRecallUpdated() {
        Snackbar.make(mLinearCode, "Recall updated", Snackbar.LENGTH_LONG).show();
    }
}
