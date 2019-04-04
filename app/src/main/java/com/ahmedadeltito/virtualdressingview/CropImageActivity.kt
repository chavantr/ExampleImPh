package com.ahmedadeltito.virtualdressingview

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap


import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ahmedadeltito.virtualdressingview.models.UserInfoHolder
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.face.FaceDetector
//import com.google.android.gms.vision.Frame
import com.isseiaoki.simplecropview.callback.CropCallback
import com.isseiaoki.simplecropview.callback.SaveCallback
import kotlinx.android.synthetic.main.activity_crop_image.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

//import com.google.android.gms.vision.face.FaceDetector;


class CropImageActivity : AppCompatActivity() {

    private val mCompressFormat = Bitmap.CompressFormat.JPEG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_image)
        cropImageView.imageBitmap = UserInfoHolder.getInstance().image


        val faceDetector = FaceDetector.Builder(applicationContext).setTrackingEnabled(false)
                .build()


        btnCrop.setOnClickListener {
            UserInfoHolder.getInstance().croppedImage = cropImageView.croppedBitmap
            val detector = FaceDetector.Builder(this)
                    .setProminentFaceOnly(true)
                    .build()

            if (!detector.isOperational) {
                AlertDialog.Builder(this).setMessage("Could not set up the face detector!").show()
                return@setOnClickListener
            }

            val frame = Frame.Builder().setBitmap(UserInfoHolder.getInstance().croppedImage).build()
            val faces = detector.detect(frame)

            if (faces.size() == 1) {
                val intent = Intent(this@CropImageActivity, PhotoEditorActivity::class.java)
                startActivity(intent)
            } else {
                AlertDialog.Builder(this).setMessage("Input image is not clear").show()
            }
        }

    }

    private val mCropCallback = object : CropCallback {
        override fun onSuccess(cropped: Bitmap) {
            cropImageView.save(cropped)
                    .compressFormat(mCompressFormat)
                    .execute(createSaveUri(), mSaveCallback)
        }

        override fun onError(e: Throwable) {}
    }

    fun createSaveUri(): Uri? {
        return createNewUri(this@CropImageActivity, mCompressFormat)
    }

    private fun createNewUri(context: Context, format: Bitmap.CompressFormat): Uri? {
        val currentTimeMillis = System.currentTimeMillis()
        val today = Date(currentTimeMillis)
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss")
        val title = dateFormat.format(today)
        val dirPath = getDirPath()
        val fileName = "scv" + title + "." + getMimeType(format)
        val path = "$dirPath/$fileName"
        val file = File(path)
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, title)
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/" + getMimeType(format))
        values.put(MediaStore.Images.Media.DATA, path)
        val time = currentTimeMillis / 1000
        values.put(MediaStore.MediaColumns.DATE_ADDED, time)
        values.put(MediaStore.MediaColumns.DATE_MODIFIED, time)
        if (file.exists()) {
            values.put(MediaStore.Images.Media.SIZE, file.length())
        }

        val resolver = context.getContentResolver()
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        return uri
    }

    fun getDirPath(): String {
        var dirPath = ""
        var imageDir: File? = null
        val extStorageDir = Environment.getExternalStorageDirectory()
        if (extStorageDir.canWrite()) {
            imageDir = File(extStorageDir.getPath() + "/simplecropview")
        }
        if (imageDir != null) {
            if (!imageDir.exists()) {
                imageDir.mkdirs()
            }
            if (imageDir.canWrite()) {
                dirPath = imageDir.path
            }
        }
        return dirPath
    }

    private fun getMimeType(format: Bitmap.CompressFormat): String {

        when (format) {
            Bitmap.CompressFormat.JPEG -> return "jpeg"
            Bitmap.CompressFormat.PNG -> return "png"

        }
        return "png"
    }

    private val mSaveCallback = object : SaveCallback {
        override fun onSuccess(outputUri: Uri) {
            Toast.makeText(this@CropImageActivity, "Hey Success", Toast.LENGTH_LONG).show()
        }

        override fun onError(e: Throwable) {
            //dismissProgress()
            Toast.makeText(this@CropImageActivity, "Hey Failure", Toast.LENGTH_LONG).show()
        }
    }
}
