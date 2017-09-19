package com.recalllist.ui.find_list;


import android.content.SharedPreferences;

import com.recalllist.util.Constants;
import com.recalllist.util.SharedPreferencesUtil;

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
        SharedPreferences.Editor edit = SharedPreferencesUtil.getSharedPreferencesEdit();
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
