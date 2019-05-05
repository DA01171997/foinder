package com.example.swipedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileDescription extends AppCompatActivity {
    private EditText mNameSubmissionView;
    private EditText mBirthDateSubmissionView;
    private View mDescriptionFormView;
    private RadioGroup mGenderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_description);
        mNameSubmissionView = (EditText) findViewById(R.id.name_submission);
        mBirthDateSubmissionView = (EditText) findViewById(R.id.birth_date_submission);

        mGenderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        mGenderRadioGroup.clearCheck();

        Button mProfileSubmissionButton = (Button) findViewById(R.id.profile_description_button);
        mProfileSubmissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSaveAndContinue();
            }
        });
        mDescriptionFormView = findViewById(R.id.description_form);
    }

    /**
     * Attempts to move to next stage of creating profile specified by the info in form.
     * If there are form errors (invalid form, missing fields, etc.), the
     * errors are presented and button does not do anything.
     */
    private void attemptSaveAndContinue() {
        // Reset errors.
        mNameSubmissionView.setError(null);
        mBirthDateSubmissionView.setError(null);

        // Store values at the time of the login attempt.
        String name = mNameSubmissionView.getText().toString();
        String bdate = mBirthDateSubmissionView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid name.
        if (TextUtils.isEmpty(name)){
            mNameSubmissionView.setError(getString(R.string.error_field_required));
            focusView = mNameSubmissionView;
            cancel = true;
        } else if (!isNameValid(name)){
            mNameSubmissionView.setError(getString(R.string.error_invalid_name));
            focusView = mNameSubmissionView;
            cancel = true;
        }

        // Check for a valid date.
        if (TextUtils.isEmpty(bdate)) {
            mBirthDateSubmissionView.setError(getString(R.string.error_field_required));
            focusView = mBirthDateSubmissionView;
            cancel = true;
        } else if (!isBirthDateValid(bdate)) {
            mBirthDateSubmissionView.setError(getString(R.string.error_invalid_bdate));
            focusView = mBirthDateSubmissionView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't accept button press and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Move to next Activity
            Intent intent = new Intent(this, SearchSettings.class);
            startActivity(intent);
        }
    }

    private boolean isNameValid(String name) {
        return name.length() > 4;
    }

    private boolean isBirthDateValid(String Bdate) {
        Bdate.replaceAll("\\s+","");
        String [] arrDate = Bdate.split("/");
        if (arrDate.length != 3)
            return false;

        int month, day, year;
        month = Integer.parseInt(arrDate[0]);
        day = Integer.parseInt(arrDate[1]);
        year = Integer.parseInt(arrDate[2]);

        return (month > 0 && month < 13 && day > 0 && day <= 31 && year > 1900 && year < 2020);
    }

}