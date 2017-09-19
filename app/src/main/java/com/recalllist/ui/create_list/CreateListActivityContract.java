package com.recalllist.ui.create_list;


/**
 * Created by estevao on 09/08/17.
 */

public interface CreateListActivityContract {

    interface Model {
        void validateHash(String hash);


        void createNewHash(String hash);
    }

    interface View {
        void hideProgressDialog();

        void showProgressDialog();

        void onHashCreated(String hash);
    }

    interface Presenter {
        void onAttachView(View view);

        void generateNewCode();

        void hashAlreadyExists();

        void hashValidated(String hash);

        void onHashCreated(String hash);

        void onHashCreateError(String message);
    }
}
