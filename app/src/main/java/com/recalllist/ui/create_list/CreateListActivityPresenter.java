package com.recalllist.ui.create_list;


import android.content.SharedPreferences;

import com.recalllist.util.Constants;
import com.recalllist.util.SharedPreferencesUtil;

import org.hashids.Hashids;

import java.util.Calendar;

/**
 * Created by estevao on 09/08/17.
 */

public class CreateListActivityPresenter implements CreateListActivityContract.Presenter {

    private CreateListActivityContract.View view;
    private CreateListActivityContract.Model model;

    public CreateListActivityPresenter() {
        this.model = new CreateListActivityModel(this);
    }


    @Override
    public void onAttachView(CreateListActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void generateNewCode() {
        view.showProgressDialog();

        Hashids hashids = new Hashids();
        String hash = hashids.encode(Calendar.getInstance().getTimeInMillis()).substring(3);
        model.validateHash(hash);
    }

    @Override
    public void hashAlreadyExists() {
        Hashids hashids = new Hashids();
        String hash = hashids.encode(5L, 5L, 5L, 5L);
        model.validateHash(hash);
    }

    @Override
    public void hashValidated(String hash) {
        model.createNewHash(hash);
    }

    @Override
    public void onHashCreated(String hash) {
        SharedPreferences.Editor edit = SharedPreferencesUtil.getSharedPreferencesEdit();
        edit.putString(Constants.HASH_SHARED_PREFERENCES, hash);
        edit.apply();

        view.hideProgressDialog();

        view.onHashCreated(hash);
    }

    @Override
    public void onHashCreateError(String message) {
        view.hideProgressDialog();
    }

}
