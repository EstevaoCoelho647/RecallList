package com.recalllist.ui.find_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.recalllist.R;
import com.recalllist.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.recalllist.R.id.textViewInvalidCode;

/**
 * Created by estevao on 18/09/17.
 */

public class FindListActivity extends AppCompatActivity implements FindListActivityContract.View, TextWatcher {
    @BindView(R.id.edt1)
    EditText mEdtText1;
    @BindView(R.id.edt2)
    EditText mEdtText2;
    @BindView(R.id.edt3)
    EditText mEdtText3;
    @BindView(R.id.edt4)
    EditText mEdtText4;
    @BindView(R.id.edt5)
    EditText mEdtText5;
    @BindView(R.id.edt6)
    EditText mEdtText6;
    @BindView(textViewInvalidCode)
    TextView textViewError;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    String insertedHash;
    FindListActivityPresenter findListActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ButterKnife.bind(this);

        findListActivityPresenter = new FindListActivityPresenter();
        findListActivityPresenter.onAttachView(this);

        mEdtText1.addTextChangedListener(this);
        mEdtText2.addTextChangedListener(this);
        mEdtText3.addTextChangedListener(this);
        mEdtText4.addTextChangedListener(this);
        mEdtText5.addTextChangedListener(this);
        mEdtText6.addTextChangedListener(this);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void insertedValidHash() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void insertedInvalidHash() {
        mEdtText1.setText("");
        mEdtText2.setText("");
        mEdtText3.setText("");
        mEdtText4.setText("");
        mEdtText5.setText("");
        mEdtText6.setText("");
        textViewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        View view = this.getCurrentFocus();
        int id = view.getId();

        switch (id) {
            case R.id.edt1:
                if (!s.toString().equals(""))
                    mEdtText2.requestFocus();
                break;
            case R.id.edt2:
                if (!s.toString().equals(""))
                    mEdtText3.requestFocus();
                break;
            case R.id.edt3:
                if (!s.toString().equals(""))
                    mEdtText4.requestFocus();
                break;
            case R.id.edt4:
                if (!s.toString().equals(""))
                    mEdtText5.requestFocus();
                break;
            case R.id.edt5:
                if (!s.toString().equals(""))
                    mEdtText6.requestFocus();
                break;
            case R.id.edt6:
                if (!s.toString().equals("")) {
                    insertedHash = String.format("%s%s%s%s%s%s", mEdtText1.getText().toString(),
                            mEdtText2.getText().toString(), mEdtText3.getText().toString(),
                            mEdtText4.getText().toString(), mEdtText5.getText().toString(),
                            mEdtText6.getText().toString());
                    mEdtText6.clearFocus();
                    InputMethodManager imm = (InputMethodManager) mEdtText6.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    findListActivityPresenter.validateCode(insertedHash);
                }
                break;

        }
    }
}