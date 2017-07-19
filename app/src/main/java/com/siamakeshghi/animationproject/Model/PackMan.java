package com.siamakeshghi.animationproject.Model;

import android.graphics.Bitmap;

/**
 * Created by siamakeshghi on 2017-05-27.
 */

public class PackMan {

    private String name;
    private int speed;
    private float xPos;
    private float yPos;
    private int xDir;
    private int yDir;
    private Bitmap bitmapImage;

    public PackMan(String name, int speed, float xPos, float yPos, int xDir, int yDir) {
        this.name = name;

        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xDir = xDir;
        this.yDir = yDir;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public int getxDir() {
        return xDir;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }



    @Override
    public String toString() {
        return "XPos: "+xPos+"\tYPos: "+yPos;
    }
}
