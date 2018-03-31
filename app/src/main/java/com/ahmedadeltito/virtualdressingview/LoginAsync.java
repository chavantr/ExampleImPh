package com.ahmedadeltito.virtualdressingview;

import android.os.AsyncTask;

import org.json.JSONObject;

public class LoginAsync extends AsyncTask<JSONObject, Void, String> {


    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();


    public void setOnResultListener(OnResultListener onResultListener, JSONObject request) {
        this.onResultListener = onResultListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }

    private OnResultListener onResultListener;

    @Override
    protected String doInBackground(JSONObject... jsonObjects) {
        return httpConnectionUtil.requestPost(VirtualDressingRoomConstant.URL + VirtualDressingRoomConstant.LOGIN, jsonObjects[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onResultListener.onSuccess(s);
    }


}
