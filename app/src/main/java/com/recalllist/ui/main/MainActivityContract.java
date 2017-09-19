package com.recalllist.ui.main;


import android.content.Context;

import com.recalllist.domain.entity.Recall;

import java.util.List;

/**
 * Created by estevao on 09/08/17.
 */

public interface MainActivityContract {

    interface Model {
        void getMyRecallList(MainActivityPresenter mainActivityPresenter);

        void updateRecall(Recall recall);

        void deleteRecall(Recall recall);
    }

    interface View {
        void hideProgressDialog();

        void showProgressDialog();

        void onGetMyRecallListSuccess(List<Recall> recallList);

        void onGetMyRecallListError(String error);
    }

    interface Presenter {
        void onAttachView(View view);

        void getMyRecallList();

        void onGetMyRecallListSuccess(List<Recall> recallList);

        void onGetMyRecallListError(String error);

        void onRecallClick(Recall recall);

        void updateRecall(Recall recall);

        void deleteRecall(Recall recall);

        Context getContext();
    }
}
