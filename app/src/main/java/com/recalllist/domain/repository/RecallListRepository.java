package com.recalllist.domain.repository;

import android.content.SharedPreferences;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.recalllist.domain.entity.Recall;
import com.recalllist.ui.main.MainActivityContract;
import com.recalllist.util.Constants;
import com.recalllist.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by estevao on 18/09/17.
 */

public class RecallListRepository {


    private final DatabaseReference mReference;

    public RecallListRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getSharedPreferences();
        String string = sharedPreferences.getString(Constants.HASH_SHARED_PREFERENCES, "");
        mReference = database.getReference(Constants.RECALL_LIST + string);
    }

    public void getRecallList(final MainActivityContract.Presenter presenter) {
        mReference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    GenericTypeIndicator<HashMap<String, Recall>> typeIndicator = new GenericTypeIndicator<HashMap<String, Recall>>() {
                    };
                    HashMap<String, Recall> objectHashMap = dataSnapshot.getValue(typeIndicator);
                    if (objectHashMap != null) {
                        ArrayList<Recall> objectArrayList = new ArrayList<>(objectHashMap.values());
                        presenter.onGetMyRecallListSuccess(objectArrayList);
                    } else
                        presenter.onGetMyRecallListSuccess(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                presenter.onGetMyRecallListError(databaseError.getMessage());
            }
        });
    }

    public void updateRecall(Recall recall, final MainActivityContract.Presenter presenter) {
        if (recall.getId() == null) {
            DatabaseReference push = mReference.push();
            recall.setId(push.getKey());
            push.setValue(recall);
        }
    }

    public void deleteRecall(Recall recall, MainActivityContract.Presenter presenter) {
        mReference.child(recall.getId()).setValue(null);
    }
}
