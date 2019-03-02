package com.ahmedadeltito.virtualdressingview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ahmedadeltito.virtualdressingview.models.User
import com.ahmedadeltito.virtualdressingview.models.UserInfoHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.InstallCallbackInterface
import org.opencv.android.LoaderCallbackInterface

class MainActivity : AppCompatActivity(), OnLoginListener, OnResultListener {


    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialogUtil = ProgressDialogUtil(this)

        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            init()
        }
    }

    override fun onResume() {
        super.onResume()

        InstallUtil.initOpenCV("2.4.11", this@MainActivity, object : BaseLoaderCallback(this) {
            override fun onManagerConnected(status: Int) {
                super.onManagerConnected(status)

                when (status) {
                    LoaderCallbackInterface.SUCCESS -> Log.i(".onManagerConnected",
                            "OpenCV loaded successfully")

                    else -> {
                        Log.w(".onManagerConnected", "OpenCV loaded failed")
                        super.onManagerConnected(status)
                    }
                }

            }

            override fun onPackageInstall(operation: Int, callback: InstallCallbackInterface?) {
                super.onPackageInstall(operation, callback)


            }
        })

    }


    private fun init() {
        progressDialogUtil.show()
        val loginAsync = LoginAsync()
        var request = JSONObject()
        var param = JSONObject()
        param.put("Username", txtUserName.text)
        param.put("Password", txtPassword.text)
        request.put("login", param)
        loginAsync.setOnResultListener(this, request)
    }

    override fun onLoginSuccess(result: String?) {


        /* if (!result.isNullOrBlank()) {

             val jUser = JSONObject(result)

             var user = User()

             user.id = jUser.getInt("Id")

             user.name = jUser.getString("Name")

             user.username = jUser.getString("Username")

             user.password = jUser.getString("Password")

             user.phone = jUser.getString("Phone")

             user.type = jUser.getString("Type")

             UserInfoHolder.getInstance().user = user

             val intent = Intent(this@MainActivity, SelectImageActivity::class.java)
             startActivity(intent)


         }*/


    }


    override fun onSuccess(result: String?) {


        progressDialogUtil.hide()

        if (!result.isNullOrBlank()) {

            val jUser = JSONObject(result)

            var user = User()

            user.id = jUser.getInt("Id")

            user.name = jUser.getString("Name")

            user.username = jUser.getString("Username")

            user.password = jUser.getString("Password")

            user.phone = jUser.getString("Phone")

            user.type = jUser.getString("Type")

            UserInfoHolder.getInstance().user = user

            val intent = Intent(this@MainActivity, SelectImageActivity::class.java)
            startActivity(intent)


        }

    }
}
