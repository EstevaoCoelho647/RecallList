package com.recalllist.ui.main;


import android.content.Context;

import com.recalllist.domain.entity.Recall;

import java.util.List;

/**
 * Created by estevao on 09/08/17.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private MainActivityContract.Model model;

    public MainActivityPresenter() {
        this.model = new MainActivityModel(this);
    }


    @Override
    public void onAttachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void getMyRecallList() {
        view.showProgressDialog();
        model.getMyRecallList(this);
    }

    @Override
    public void onGetMyRecallListSuccess(List<Recall> recallList) {
        view.hideProgressDialog();
        view.onGetMyRecallListSuccess(recallList);
    }

    @Override
    public void onGetMyRecallListError(String error) {
        view.hideProgressDialog();
        view.onGetMyRecallListError(error);
    }

    @Override
    public void onRecallClick(Recall recall) {

    }

    @Override
    public void updateRecall(Recall recall) {
        model.updateRecall(recall);
    }

    @Override
    public void deleteRecall(Recall recall) {
        if (recall.getId() != null)
            model.deleteRecall(recall);
    }

    @Override
    public Context getContext() {
        return (MainActivity) view;
    }

    @Override
    public void onRecallDeleted() {
        view.onRecallDeleted();
    }

    @Override
    public void onRecallUpdated() {
        view.onRecallUpdated();
    }
}
