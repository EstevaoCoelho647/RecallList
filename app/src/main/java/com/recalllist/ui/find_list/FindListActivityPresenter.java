package com.recalllist.ui.find_list;


import android.content.Context;
import android.content.SharedPreferences;

import com.recalllist.domain.entity.Recall;
import com.recalllist.util.ApplicationUtil;
import com.recalllist.util.Constants;

import java.util.List;

/**
 * Created by estevao on 09/08/17.
 */

public class FindListActivityPresenter implements FindListActivityContract.Presenter {

    private FindListActivityContract.View view;
    private FindListActivityContract.Model model;

    public FindListActivityPresenter() {
        this.model = new FindListActivityModel(this);
    }


    @Override
    public void onAttachView(FindListActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void validateCode(String insertedHash) {
        view.showProgress();
        model.validateHash(insertedHash);
    }

    @Override
    public void hashExists(String insertedHash) {
        SharedPreferences sharedPreferences = ApplicationUtil.getContext().getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(Constants.HASH_SHARED_PREFERENCES, insertedHash);
        edit.apply();

        view.insertedValidHash();

    }

    @Override
    public void hashInvalid(String insertedHash) {
        view.hideProgress();
        view.insertedInvalidHash();
    }


}
