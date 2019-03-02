package com.ahmedadeltito.virtualdressingview.models;

import android.graphics.Bitmap;

public class UserInfoHolder {

    private Bitmap image;
    private Bitmap croppedImage;

    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class UserInfoHolderHelper {

        static UserInfoHolder INSTANCE = new UserInfoHolder();

    }


}
