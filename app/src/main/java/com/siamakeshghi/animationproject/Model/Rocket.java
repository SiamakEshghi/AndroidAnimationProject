package com.siamakeshghi.animationproject.Model;

import android.graphics.Bitmap;

/**
 * Created by siamakeshghi on 2017-05-27.
 */

public class Rocket {

    private Bitmap bitmap;
    private float xPos;
    private float yPos;

    public Rocket(Bitmap bitmap, float xPos, float yPos) {
        this.bitmap = bitmap;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

}
