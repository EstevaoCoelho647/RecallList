package com.recalllist.ui.create_list;

import com.recalllist.domain.repository.HashIdsRepository;

/**
 * Created by estevao on 09/08/17.
 */

public class CreateListActivityModel implements CreateListActivityContract.Model {

    private CreateListActivityContract.Presenter presenter;

    public CreateListActivityModel(CreateListActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void validateHash(String hash) {
        HashIdsRepository hashIdsRepository = new HashIdsRepository();
        hashIdsRepository.validateHash(hash, presenter);
    }

    @Override
    public void createNewHash(String hash) {
        HashIdsRepository hashIdsRepository = new HashIdsRepository();
        hashIdsRepository.createHash(hash, presenter);
    }
}
