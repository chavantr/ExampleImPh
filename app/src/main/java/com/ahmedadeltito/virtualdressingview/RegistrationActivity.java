package com.ahmedadeltito.virtualdressingview;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity implements OnResultListener {

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

        lblDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {


                } else {

                }
            }
        });


    }

    private boolean isValidEmaill(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    @Override
    public void onSuccess(String result) {

    }

    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            lblDateOfBirth.setText("" + day + "/" + month + "/" + year);
        }
    }

    private boolean validate() {

        if (TextUtils.isEmpty(txtName.getText().toString())) {
            txtName.setError("Enter Name");
            return false;
        }

        if (TextUtils.isEmpty(txtMobileNumber.getText())) {
            txtMobileNumber.setError("Enter mobile number");
            return false;
        }

        if (TextUtils.isEmpty(txtEmail.getText())) {
            txtEmail.setError("Enter email");
            return false;
        } else {
            if (!isValidEmaill(txtEmail.getText().toString())) {
                txtEmail.setError("Enter valid email");
                return false;
            }
        }

        if (TextUtils.isEmpty(txtPassword.getText())) {
            txtPassword.setError("Enter password");
            return false;
        }

        if (TextUtils.isEmpty(txtAddress.getText())) {
            txtAddress.setError("Enter address");
            return false;
        }

        if (spnGender.getSelectedItem().toString().equalsIgnoreCase("Select")) {
            show("Select gender");
            return false;
        }

        if (lblDateOfBirth.getText().toString().equalsIgnoreCase("Select date of birth")) {
            show("Select date of birth");
            return false;
        }

        return true;
    }

    private void show(String message) {
        Snackbar.make(btnRegister, message, Snackbar.LENGTH_LONG).show();
    }


}
