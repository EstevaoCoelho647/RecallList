package com.recalllist.domain.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recalllist.ui.create_list.CreateListActivityContract;
import com.recalllist.ui.find_list.FindListActivityContract;
import com.recalllist.util.Constants;

/**
 * Created by estevao on 18/09/17.
 */

public class HashIdsRepository {

    private final DatabaseReference mReference;

    public HashIdsRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mReference = database.getReference(Constants.HASH_ID);
    }


    public void validateHash(final String hash, final CreateListActivityContract.Presenter presenter) {
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(hash)) {
                    presenter.hashAlreadyExists();
                } else
                    presenter.hashValidated(hash);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("validateHash", databaseError.getMessage());
            }
        });
    }

    public void createHash(final String hash, final CreateListActivityContract.Presenter presenter) {
        mReference.child(hash).setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    presenter.onHashCreated(hash);
                }else
                    presenter.onHashCreateError(task.getException().getMessage());
            }
        });
    }

    public void existsHash(final String insertedHash, final FindListActivityContract.Presenter presenter) {
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(insertedHash)) {
                    presenter.hashExists(insertedHash);
                } else
                    presenter.hashInvalid(insertedHash);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("validateHash", databaseError.getMessage());
            }
        });
    }
}
