package com.ahmedadeltito.virtualdressingview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends MediaActivity implements OnResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openUserGallery(View view) {
        openGallery();
    }

    public void openUserCamera(View view) {
        startCameraActivity();
    }

    @Override
    protected void onPhotoTaken() {
        Intent intent = new Intent(MainActivity.this, PhotoEditorActivity.class);
        intent.putExtra("selectedImagePath", selectedImagePath);
        startActivity(intent);
    }

    @Override
    public void onSuccess(String result) {

    }
}
