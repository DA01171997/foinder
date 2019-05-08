package com.example.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class Profile extends AppCompatActivity {
    private Button reg;
    EditText Username;
    TextView Email;
    EditText Date;
    EditText Password;
    EditText CPassword;

    private String name;
    private int userID;
    private String userEMAIl;


    private static final String TAG = "Profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        Intent intent = getIntent();
        name = intent.getExtras().getString("name").toUpperCase();
        userID = intent.getExtras().getInt("userID");
        userEMAIl = intent.getExtras().getString("email");
        Log.i(TAG,"STUFFF "+ name + " "+ userID );


        Username=(EditText)findViewById(R.id.UserName);
        Email=(TextView)findViewById(R.id.UserEmail);
        Date=(EditText)findViewById(R.id.Birthday);
        Password=(EditText)findViewById(R.id.regPassword);
        CPassword=(EditText)findViewById(R.id.CPassword);

        Email.setText(userEMAIl);
        String type = "getInfo";
        String userIDSTRING= Integer.toString(userID);
        BackgroundWorker backgroundWorker3 = new BackgroundWorker((this));
        try {
            String whichInfo= "birthday";
            Date.setText(backgroundWorker3.execute(type,userIDSTRING,whichInfo).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Username.setText(name);
        reg=(Button)findViewById(R.id.historyButton);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSaveAndContinue();
            }
        });
    }

    private void attemptSaveAndContinue() {
        // Reset errors.
        Username.setError(null);
        Email.setError(null);
        Date.setError(null);
        Password.setError(null);
        CPassword.setError(null);

        // Store values at the time of the login attempt.
        String name = Username.getText().toString();
        String bdate = Date.getText().toString();
        //String email = Email.getText().toString();
        String pw = Password.getText().toString();
        String cpw = CPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid name.
        if (TextUtils.isEmpty(name)){
            Username.setError(getString(R.string.error_field_required));
            focusView = Username;
            cancel = true;
        } else if (!isNameValid(name)){
            Username.setError(getString(R.string.error_invalid_name));
            focusView = Username;
            cancel = true;
        }

        // Check for a valid email address
        /*
        if (TextUtils.isEmpty(email)) {
            Email.setError(getString(R.string.error_field_required));
            focusView = Email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            Email.setError(getString(R.string.error_invalid_email));
            focusView = Email;
            cancel = true;
        }

        */

        // Check for a valid date.
        if (TextUtils.isEmpty(bdate)) {
            Date.setError(getString(R.string.error_field_required));
            focusView = Date;
            cancel = true;
        }
        else if (!isBirthDateValid(bdate)) {
            Date.setError(getString(R.string.error_invalid_bdate));
            focusView = Date;
            cancel = true;
        }

        //Check for a valid password.
        if (TextUtils.isEmpty(pw)){
            Password.setError(getString(R.string.error_field_required));
            focusView = Password;
            cancel = true;
        } else if (!isPasswordValid(pw)){
            Password.setError(getString(R.string.error_invalid_password));
            focusView = Password;
            cancel = true;
        }

        if (TextUtils.isEmpty(cpw)){
            CPassword.setError(getString(R.string.error_field_required));
            focusView = CPassword;
            cancel = true;
        } else if (!isCPasswordValid(pw,cpw)){
            CPassword.setError(getString(R.string.error_incorrect_Cpassword));
            focusView = CPassword;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't accept button press and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            String type = "update";

            BackgroundWorker backgroundWorker4 = new BackgroundWorker((this));
            try {
                String what = backgroundWorker4.execute(type,Integer.toString(userID),name,bdate,pw).get();
                Log.i(TAG,"what: "+ what+":");
                if (what.equals("Update Successed")){
                    Integer userIDInt = Integer.valueOf(userID);
                    Intent newActivity1 = new Intent(this, MainOption.class);
                    newActivity1.putExtra("email",userEMAIl);
                    newActivity1.putExtra("name",name);
                    newActivity1.putExtra("userID",userIDInt);
                    startActivity(newActivity1);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isNameValid(String name) {
        return name.length() > 4;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isBirthDateValid(String Bdate) {
        Bdate.replaceAll("\\s+","");
        String [] arrDate = Bdate.split("-");
        if (arrDate.length != 3)
            return false;

        int month, day, year;
        month = Integer.parseInt(arrDate[1]);
        day = Integer.parseInt(arrDate[2]);
        year = Integer.parseInt(arrDate[0]);

        return (month > 0 && month < 13 && day > 0 && day <= 31 && year > 1900 && year < 2020);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isCPasswordValid(String password, String cpassword) {
        return password.equals(cpassword);
    }
    private boolean isEmailAvailable(String email){
        String type = "checkEmail";
        String valid= "Existed";
        BackgroundWorker backgroundWorker = new BackgroundWorker((this));
        try {
            valid = backgroundWorker.execute(type,email).get();
            Log.i(TAG, "valid "+ valid);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (valid.equals("Empty")){
            return true;
        }
        else {
            return false;
        }
    }
}
