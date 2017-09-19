package com.recalllist.ui.find_list;


/**
 * Created by estevao on 09/08/17.
 */

public interface FindListActivityContract {

    interface Model {
        void validateHash(String insertedHash);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void insertedValidHash();

        void insertedInvalidHash();
    }

    interface Presenter {
        void onAttachView(View view);

        void validateCode(String insertedHash);

        void hashExists(String insertedHash);

        void hashInvalid(String insertedHash);
    }
}
