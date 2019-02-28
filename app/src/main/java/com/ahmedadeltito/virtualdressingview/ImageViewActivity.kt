package com.ahmedadeltito.virtualdressingview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ahmedadeltito.virtualdressingview.models.UserInfoHolder

import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)
        imgImage.setImageBitmap(UserInfoHolder.getInstance().image)

        imgImage.setOnClickListener {
            val intent = Intent(this@ImageViewActivity, CropImageActivity::class.java)
            startActivity(intent)
        }
    }
}
