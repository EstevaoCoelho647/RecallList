package com.recalllist.ui.find_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.recalllist.R;
import com.recalllist.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.recalllist.R.id.textViewInvalidCode;

/**
 * Created by estevao on 18/09/17.
 */

public class FindListActivity extends AppCompatActivity implements FindListActivityContract.View {
    @BindView(R.id.editHash)
    EditText mEdtTextHash;
    @BindView(textViewInvalidCode)
    TextView textViewError;
    @BindView(R.id.buttonContinue)
    Button buttonContinue;
    String insertedHash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ButterKnife.bind(this);

        final FindListActivityPresenter findListActivityPresenter = new FindListActivityPresenter();
        findListActivityPresenter.onAttachView(this);

        mEdtTextHash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textViewError.setVisibility(View.GONE);

                if (charSequence.length() == 6) {
                    findListActivityPresenter.validateCode(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void insertedValidHash() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void insertedInvalidHash() {
        mEdtTextHash.setText("");
        textViewError.setVisibility(View.VISIBLE);
    }
}