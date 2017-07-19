package com.siamakeshghi.animationproject.Model;

import android.util.Log;

/**
 * Created by siamakeshghi on 2017-05-27.
 */

public class Animation implements Runnable {

    CustomView customView;
    Thread thread;

    public Animation(CustomView customView) {
        this.customView = customView;
    }

    @Override
    public void run() {

        while (thread!=null){

            //reDraw the canvas or call the method onDraw
            customView.postInvalidate();


            //we always should add it in run method (we need sleep and process , sleep and process
            try {
                thread.sleep(100);
            } catch (InterruptedException e) {
                Log.d("Animation",e.getMessage());
            }
        }
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        thread = null;
    }
}
