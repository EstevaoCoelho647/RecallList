package com.recalllist.domain.repository;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recalllist.domain.entity.Recall;
import com.recalllist.ui.main.MainActivityContract;
import com.recalllist.util.Constants;
import com.recalllist.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by estevao on 18/09/17.
 */

public class RecallListRepository {


    private final DatabaseReference mReference;

    public RecallListRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getSharedPreferences();
        String string = sharedPreferences.getString(Constants.HASH_SHARED_PREFERENCES, "");
        mReference = database.getReference(Constants.RECALL_LIST + string);
    }

    public void getRecallList(final MainActivityContract.Presenter presenter) {
        mReference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Recall> recallList = new ArrayList<>();
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    recallList.add(dt.getValue(Recall.class));
                }
                Collections.reverse(recallList);
                presenter.onGetMyRecallListSuccess(recallList);
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
            push.setValue(recall).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    presenter.onRecallUpdated();
                }
            });
        }
    }

    public void deleteRecall(Recall recall, final MainActivityContract.Presenter presenter) {
        mReference.child(recall.getId()).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    presenter.onRecallDeleted();
            }
        });
    }
}
