package com.ahmedadeltito.virtualdressingview

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.ahmedadeltito.virtualdressingview.models.UserInfoHolder
import kotlinx.android.synthetic.main.activity_select_image.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class SelectImageActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_image)

        btnGallery.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE)
        }

        btnCamera.setOnClickListener {
            val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST)

        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
            ) {

            } else {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    // Marshmallow+
                    requestPermissions(
                            arrayOf(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ),
                            PERMISSION
                    )
                } else {
                    //below Marshmallow
                }

            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> {
                when (requestCode) {
                    CAMERA_PIC_REQUEST -> {

                        val image = data!!.extras.get("data") as Bitmap
                        saveFile(image)
                        UserInfoHolder.getInstance().image = image
                        val intent = Intent(this@SelectImageActivity, ImageViewActivity::class.java)
                        startActivity(intent)

                    }
                    SELECT_IMAGE -> {
                        try {
                            val image =
                                    MediaStore.Images.Media.getBitmap(contentResolver, data?.data)

                            UserInfoHolder.getInstance().image = image

                            val intent = Intent(this@SelectImageActivity, ImageViewActivity::class.java)
                            startActivity(intent)

                            saveFile(image)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    private fun saveFile(bitmap: Bitmap) {
        val path = "mnt/sdcard"
        //val extStorageDirectory = Environment.getExternalStorageDirectory().toString()
        val pathFile = File(path, "image.png")
        var outputStream: OutputStream
        if (!pathFile.exists()) {
            pathFile.createNewFile()
        }
        outputStream = FileOutputStream(pathFile)

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.flush();
        outputStream.close();
        //MediaStore.Images.Media.insertImage(contentResolver, pathFile.absolutePath, pathFile.name, pathFile.name);
    }

    companion object {
        const val PERMISSION = 1001
        const val CAMERA_PIC_REQUEST = 1002
        const val SELECT_IMAGE = 1003
    }


}
