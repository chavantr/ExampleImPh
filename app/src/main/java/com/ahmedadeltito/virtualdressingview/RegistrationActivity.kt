package com.ahmedadeltito.virtualdressingview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONObject

class RegistrationActivity : AppCompatActivity(), OnResultListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        progressDialogUtil = ProgressDialogUtil(this)

        btnSignUp.setOnClickListener {
            init()
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun init() {
        progressDialogUtil.show()
        val registrationAsync = RegistrationAsync()
        var request = JSONObject()
        var param = JSONObject()
        param.put("Name", txtName.text)
        param.put("Username", txtUserName.text)
        param.put("Password", txtPassword.text)
        param.put("Phone", txtNumber.text)
        param.put("Type", "")
        request.put("request", param)
        registrationAsync.setOnResultListener(this, request)
    }


    override fun onSuccess(result: String?) {
        progressDialogUtil.hide()
        if (!result.isNullOrBlank()) {
            val snackbar = Snackbar.make(btnSignUp, "Registration completed.", Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction("Ok") {
                finish()
            }
            snackbar.show()
        } else {
            val snackbar = Snackbar.make(btnSignUp, "An error has occurred.try later", Snackbar.LENGTH_LONG)
            snackbar.setAction("Ok") {

            }
            snackbar.show()
        }
    }
}
