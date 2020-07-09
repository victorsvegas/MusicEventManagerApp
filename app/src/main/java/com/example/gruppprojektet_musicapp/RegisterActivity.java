package com.example.gruppprojektet_musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.example.gruppprojektet_musicapp.Database.InputValid;
import com.example.gruppprojektet_musicapp.Database.Database;
import com.example.gruppprojektet_musicapp.Database.User;


//Thanks to lalit for some of the code

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutAdress;
    private TextInputLayout textInputLayoutCity;
    private TextInputLayout textInputLayoutPostalcode;
    private TextInputLayout textInputLayoutAge;
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextAdress;
    private TextInputEditText textInputEditTextCity;
    private TextInputEditText textInputEditTextPostalcode;
    private TextInputEditText textInputEditTextAge;
    AppCompatSpinner dropdownGender;
    private InputValid inputValidation;
    private Database databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        init();
    }

    private void init() {
        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail =  findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutAdress = findViewById(R.id.textInputLayoutAdress);
        textInputLayoutCity = findViewById(R.id.textInputLayoutCity);
        textInputLayoutAge = findViewById(R.id.textInputLayoutAge);
        textInputLayoutPostalcode = findViewById(R.id.textInputLayoutPostalcode);
        textInputEditTextName =  findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail =  findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword =  findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword =  findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextAdress = findViewById(R.id.textInputEditTextAdress);
        textInputEditTextAge = findViewById(R.id.textInputEditTextAge);
        textInputEditTextCity = findViewById(R.id.textInputEditTextCity);
        textInputEditTextPostalcode = findViewById(R.id.textInputEditTextPostalcode);

        dropdownGender = findViewById(R.id.InputGender);
        String[] items = new String[]{"male", "female", "none"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdownGender.setAdapter(adapter);


        AppCompatButton appCompatButtonRegister =  findViewById(R.id.appCompatButtonRegister);

        AppCompatButton backToLgoinButton = findViewById(R.id.appCompatTextViewLoginLink);

        appCompatButtonRegister.setOnClickListener(this);
        backToLgoinButton.setOnClickListener(this);

        inputValidation = new InputValid(activity);
        databaseHelper = new Database(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                    textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
                return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setAdress(textInputEditTextAdress.getText().toString().trim());
            user.setCity(textInputEditTextCity.getText().toString().trim());
            user.setDateofbirth(textInputEditTextAge.getText().toString().trim());
            user.setGender(dropdownGender.getSelectedItem().toString().trim());
            user.setPostalcode(textInputEditTextPostalcode.getText().toString().trim());
            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();

            Intent registerdoneintent = new Intent(activity, LoginActivity.class);
            registerdoneintent.putExtra("Register", "Register Complete. Login Above");
            startActivity(registerdoneintent);

        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

}
