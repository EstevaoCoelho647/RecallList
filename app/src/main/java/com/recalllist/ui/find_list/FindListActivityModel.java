package com.recalllist.ui.find_list;

import com.recalllist.domain.repository.HashIdsRepository;

/**
 * Created by estevao on 09/08/17.
 */

public class FindListActivityModel implements FindListActivityContract.Model {

    private FindListActivityContract.Presenter presenter;

    public FindListActivityModel(FindListActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void validateHash(String insertedHash) {
        HashIdsRepository hashIdsRepository = new HashIdsRepository();
        hashIdsRepository.existsHash(insertedHash, presenter);
    }
}
