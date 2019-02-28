package com.ahmedadeltito.virtualdressingview;

import android.content.Context;
import android.content.Intent;

import org.opencv.android.AsyncServiceHelper;
import org.opencv.android.LoaderCallbackInterface;

import static org.opencv.android.AsyncServiceHelper.InstallService;

public class InstallUtil {

    public static boolean initOpenCV(String Version, final Context AppContext,
                                     final LoaderCallbackInterface Callback) {
        AsyncServiceHelper helper = new AsyncServiceHelper(Version, AppContext, Callback);
        Intent intent = new Intent("org.opencv.engine.BIND");
        intent.setPackage("org.opencv.engine");
        if (AppContext.bindService(intent,
                helper.mServiceConnection, Context.BIND_AUTO_CREATE)) {
            return true;
        } else {
            AppContext.unbindService(helper.mServiceConnection);
            InstallService(AppContext, Callback);
            return false;
        }
    }
}
