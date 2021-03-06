package com.recalllist.ui.main;

import com.recalllist.domain.entity.Recall;
import com.recalllist.domain.repository.RecallListRepository;

/**
 * Created by estevao on 09/08/17.
 */

public class MainActivityModel implements MainActivityContract.Model {

    private MainActivityContract.Presenter presenter;

    public MainActivityModel(MainActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getMyRecallList(MainActivityPresenter mainActivityPresenter) {
        RecallListRepository recallListRepository = new RecallListRepository();
        recallListRepository.getRecallList(presenter);
    }

    @Override
    public void updateRecall(Recall recall) {
        RecallListRepository recallListRepository = new RecallListRepository();
        recallListRepository.updateRecall(recall, presenter);
    }

    @Override
    public void deleteRecall(Recall recall) {
        RecallListRepository recallListRepository = new RecallListRepository();
        recallListRepository.deleteRecall(recall, presenter);
    }
}
