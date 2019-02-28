package com.ahmedadeltito.virtualdressingview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.opencv.android.BaseLoaderCallback
import org.opencv.android.InstallCallbackInterface
import org.opencv.android.LoaderCallbackInterface

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this@MainActivity, SelectImageActivity::class.java)
            startActivity(intent)
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

        /*initOpenCV("2.4.11", this, object : BaseLoaderCallback(this) {
            override fun onManagerConnected(status: Int) {
                when (status) {
                    LoaderCallbackInterface.SUCCESS -> Log.i(".onManagerConnected",
                            "OpenCV loaded successfully")

                    else -> {
                        Log.w(".onManagerConnected", "OpenCV loaded failed")
                        super.onManagerConnected(status)
                    }
                }
            }

            override fun onPackageInstall(operation: Int, callback: InstallCallbackInterface) {
                super.onPackageInstall(operation, callback)
            }
        })*/

    }
}
