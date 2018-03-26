package com.ahmedadeltito.photoeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtMobileNumber;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtAddress;
    private Spinner spnGender;
    private TextView lblDateOfBirth;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        txtName = (EditText) findViewById(R.id.txtName);
        txtMobileNumber = (EditText) findViewById(R.id.txtPhonerNumber);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        spnGender = (Spinner) findViewById(R.id.spnGender);
        lblDateOfBirth = (TextView) findViewById(R.id.lblDateOfBirth);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                }else {

                }
            }
        });


    }

    private boolean validate() {



        return false;
    }
}
