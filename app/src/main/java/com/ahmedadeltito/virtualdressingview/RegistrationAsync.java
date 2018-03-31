package com.ahmedadeltito.virtualdressingview;

import android.os.AsyncTask;

import org.json.JSONObject;

public class RegistrationAsync extends AsyncTask<JSONObject, Void, String> {


    private OnResultListener onResultListener;
    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();


    public void setOnResultListener(OnResultListener onResultListener) {
        this.onResultListener = onResultListener;
    }

    @Override
    protected String doInBackground(JSONObject... jsonObjects) {
        return httpConnectionUtil.requestPost(VirtualDressingRoomConstant.URL + VirtualDressingRoomConstant.REGISTRATION, jsonObjects[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onResultListener.onSuccess(s);
    }
}
