package com.ahmedadeltito.virtualdressingview.models;

import android.graphics.Bitmap;

public class UserInfoHolder {

    private Bitmap image;
    private Bitmap croppedImage;

    public static UserInfoHolder getInstance() {
        return UserInfoHolderHelper.INSTANCE;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(Bitmap croppedImage) {
        this.croppedImage = croppedImage;
    }

    public static class UserInfoHolderHelper {

        static UserInfoHolder INSTANCE = new UserInfoHolder();

    }


}
