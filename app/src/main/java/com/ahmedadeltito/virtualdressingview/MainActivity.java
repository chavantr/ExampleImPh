package com.ahmedadeltito.virtualdressingview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.opencv.android.AsyncServiceHelper;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import static org.opencv.android.AsyncServiceHelper.InstallService;

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


    @Override
    protected void onResume() {
        super.onResume();




        /*OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11, this,
                new BaseLoaderCallback(this) {

                    @Override
                    public void onManagerConnected(int status) {
                        switch (status) {
                            case LoaderCallbackInterface.SUCCESS:
                                Log.i(".onManagerConnected",
                                        "OpenCV loaded successfully");
                                break;

                            default:
                                Log.w(".onManagerConnected", "OpenCV loaded failed");
                                super.onManagerConnected(status);

                                break;
                        }
                    }
                });*/

        initOpenCV("2.4.11", this, new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                        Log.i(".onManagerConnected",
                                "OpenCV loaded successfully");
                        break;

                    default:
                        Log.w(".onManagerConnected", "OpenCV loaded failed");
                        super.onManagerConnected(status);

                        break;
                }
            }

            @Override
            public void onPackageInstall(int operation, InstallCallbackInterface callback) {
                super.onPackageInstall(operation, callback);
            }
        });

    }


    public static boolean initOpenCV(String Version, final Context AppContext,
                                     final LoaderCallbackInterface Callback) {
        AsyncServiceHelper helper = new AsyncServiceHelper(Version, AppContext,
                Callback);
        Intent intent = new Intent("org.opencv.engine.BIND");
        intent.setPackage("org.opencv.engine");
        if (AppContext.bindService(intent, helper.mServiceConnection,
                Context.BIND_AUTO_CREATE)) {
            return true;
        } else {
            AppContext.unbindService(helper.mServiceConnection);
            InstallService(AppContext, Callback);
            return false;
        }
    }
}
