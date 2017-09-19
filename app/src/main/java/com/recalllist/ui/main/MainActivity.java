package com.recalllist.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.recalllist.R;
import com.recalllist.domain.entity.Recall;
import com.recalllist.ui.main.adapter.RecallListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    MainActivityPresenter mMainActivityPresenter;
    RecallListAdapter mRecallListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMainActivityPresenter = new MainActivityPresenter();
        mMainActivityPresenter.onAttachView(this);

        mMainActivityPresenter.getMyRecallList();
        mRecallListAdapter = new RecallListAdapter(mMainActivityPresenter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecallListAdapter.addNewItem();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}
