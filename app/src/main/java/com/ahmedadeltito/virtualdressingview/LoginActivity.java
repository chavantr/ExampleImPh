package com.ahmedadeltito.virtualdressingview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements OnResultListener {


    private EditText txtUserName;
    private EditText txtPassword;
    private Button btnSignIn;
    private Button btnSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        txtUserName.setText("ankita@gmail.com");
        txtPassword.setText("uSer_123");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(txtUserName.getText()) || TextUtils.isEmpty(txtPassword.getText())) {
                    Toast.makeText(LoginActivity.this, "Enter username & password", Toast.LENGTH_LONG).show();
                } else {
                    LoginAsync loginAsync = new LoginAsync();
                    JSONObject request = new JSONObject();
                    try {
                        request.put("email", txtUserName.getText().toString());
                        request.put("password", txtPassword.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    loginAsync.setOnResultListener(LoginActivity.this, request);

                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSuccess(String result) {

        if (null != result) {

            if(result.length()==2){
                Toast.makeText(this,"Enter correct username or password",Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent(LoginActivity.this, MainActivity_0.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"Not able to login",Toast.LENGTH_LONG).show();
        }

    }
}
