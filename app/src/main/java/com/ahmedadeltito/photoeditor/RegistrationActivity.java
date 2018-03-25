package com.ahmedadeltito.photoeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtMobileNumber;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}
