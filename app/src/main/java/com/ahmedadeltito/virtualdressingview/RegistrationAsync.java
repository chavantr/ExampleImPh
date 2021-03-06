package com.ahmedadeltito.virtualdressingview;

import android.os.AsyncTask;

import org.json.JSONObject;

public class RegistrationAsync extends AsyncTask<JSONObject, Void, String> {

    private OnResultListener onResultListener;
    private HttpConnectionUtil httpConnectionUtil = new HttpConnectionUtil();


    public void setOnResultListener(OnResultListener onResultListener, JSONObject request) {
        this.onResultListener = onResultListener;
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }

    @Override
    protected String doInBackground(JSONObject... params) {
        return httpConnectionUtil.requestPost(HairRecommendationConstants.URL + HairRecommendationConstants.REGISTRATION, params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onResultListener.onSuccess(s);
    }
}
