package com.recalllist.ui.main;


import com.recalllist.domain.entity.Recall;

import java.util.List;

/**
 * Created by estevao on 09/08/17.
 */

public interface MainActivityContract {

    interface Model {
        void getMyRecallList(MainActivityPresenter mainActivityPresenter);
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
    }
}
