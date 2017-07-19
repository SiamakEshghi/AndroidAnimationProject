package com.siamakeshghi.animationproject.Model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.siamakeshghi.animationproject.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by siamakeshghi on 2017-05-27.
 */

public class CustomView extends View implements View.OnTouchListener {


    private ArrayList<PackMan> listOfPacs ;
    private Rocket rocket;
    private TextView scoreView;
    private TextView touchCounterView;
    private TextView unTouchCounterView;

    private LinearLayout linMid;


    public int rocketTouchCounter = 0;
    public int rocketPasscounter = 0;
    public int score =0;

    //index for choosing apropriate image
    public int i = 1;

    Bitmap pac1BitmapImage;
    Bitmap pac2BitmapImage;



    public CustomView(Context context) {
        super(context);

        listOfPacs = FilePackManManagement.readFile(context,"pacman.txt");

        scoreView = (TextView)  ((Activity)context).findViewById(R.id.scoreTextView);
        touchCounterView = (TextView)  ((Activity)context).findViewById(R.id.catchTextView);
        unTouchCounterView = (TextView)  ((Activity)context).findViewById(R.id.noCatchTextView);

        linMid = (LinearLayout)  ((Activity)context).findViewById(R.id.linGame);



        int imagePac1ResourceId = context.getResources().getIdentifier("pac1","drawable"
                ,context.getPackageName());
        int imagePac2ResourceId = context.getResources().getIdentifier("pac2","drawable"
                ,context.getPackageName());

         pac1BitmapImage = BitmapFactory.decodeResource(getResources(), imagePac1ResourceId);
         pac2BitmapImage = BitmapFactory.decodeResource(getResources(), imagePac2ResourceId);

       setOnTouchListener(this);



    }

    //Mark: - Canvas Draw function


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(rocket == null){
            Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.rocket);
            float xPos = 0f;
            float yPos = canvas.getHeight()-bitmapImage.getHeight();
            rocket = new Rocket(bitmapImage,xPos,yPos);
        }else {
            setBitmaps();
            pacMansDraw(canvas);
            rocketDraw(canvas,rocket);
            move(canvas);
        }
    }


    //Mark: - Pacmans Movement
    public void move(Canvas canvas){

        for(PackMan pacman: listOfPacs){

             checkTouchedRocket(canvas,pacman);
             checkPassed(canvas,pacman);
             checkEdges(canvas,pacman);


            float nextXPos = pacman.getxPos() + pacman.getSpeed()*pacman.getxDir();
            float nextYPos = pacman.getyPos() + pacman.getSpeed()*pacman.getyDir();

            pacman.setxPos(nextXPos);
            pacman.setyPos(nextYPos);



        }
    }


    //Mark : - check rocket is touched
    private void checkTouchedRocket(Canvas canvas, PackMan pacman) {
        if (
            //check touching rocket
                pacman.getBitmapImage().getHeight()+pacman.getyPos() >= rocket.getyPos() &&
                        pacman.getxPos()>=rocket.getxPos() &&
                        pacman.getxPos() <= rocket.getxPos()+rocket.getBitmap().getWidth())
        {
            rocketTouchCounter += 1;
            score += (pacman.getSpeed() * 10);

            scoreView.setText(String.valueOf(score));
            touchCounterView.setText(String.valueOf(rocketTouchCounter));



            float newYpos = (canvas.getHeight() -
                    (rocket.getBitmap().getHeight()+pacman.getBitmapImage().getHeight())- 200 ) ;
            pacman.setyPos(newYpos);

            setnewXYDir(pacman);

        }
    }


    //Mark: - check pacman is passed
    private void checkPassed(Canvas canvas, PackMan pacman) {

        if (pacman.getyPos() >= rocket.getyPos()){

            rocketPasscounter += 1;
            unTouchCounterView.setText(String.valueOf(rocketPasscounter));

           changeBackGroundColor();

            float min = 00.0f;
            float maxX = canvas.getWidth() - rocket.getBitmap().getWidth();
            float maxY = canvas.getHeight();

            Random rand = new Random();

            float newX = rand.nextFloat() * (maxX - min) + min;


            pacman.setxPos(newX);
            pacman.setyPos(0);
        }
    }

    private void changeBackGroundColor() {
        linMid.setBackgroundColor(Color.RED);
        new CountDownTimer(200,1) {

            @Override
            public void onTick(long arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                linMid.setBackgroundColor(Color.BLUE);
            }
        }.start();
    }


    //Mark: - check pacmans are arrived the edge of canvas
    private void checkEdges(Canvas canvas,PackMan pacman) {

                 chekEdgeLeft(pacman);
                 checkEdgeRight(canvas,pacman);
                 checkTop(pacman);
    }


    //Mark: - check packman touched right edge
    private void checkEdgeRight(Canvas canvas,PackMan pacman) {
        if(pacman.getBitmapImage().getWidth() + pacman.getxPos() >= canvas.getWidth()){
            float newXPos = canvas.getWidth() - pacman.getBitmapImage().getWidth();
            pacman.setxPos(newXPos);
            setnewXYDir(pacman);
        }

    }

    //Mark: - check packman touched top edge
    private void checkTop(PackMan pacman) {
        if(pacman.getyPos() <= 0){
            pacman.setyPos(0);
            setnewXYDir(pacman);
        }
    }
    //Mark: - check packman touched left edge
    private void chekEdgeLeft(PackMan pacman) {
        if(pacman.getxPos() <= 0){
            pacman.setxPos(0);
            setnewXYDir(pacman);
        }
    }

    private void setnewXYDir(PackMan pacman) {
        Random random = new Random();
        int randomXDir = random.nextInt(2);
        if (randomXDir == 0)
        {
            randomXDir = -1;
        }
        int randomYDir = random.nextInt(2);
        if (randomYDir == 0)
        {
            randomYDir = -1;
        }

        pacman.setxDir(randomXDir);
        pacman.setyDir(randomYDir);
    }


    //Mark: - rocket drawing
    private void rocketDraw(Canvas canvas,Rocket rocket) {
        Bitmap bitmapImage = rocket.getBitmap();
        float xPos = rocket.getxPos();
        float yPos = rocket.getyPos();

        canvas.drawBitmap(bitmapImage,xPos,yPos,null);
    }//end rocketDraw


    //Mark: - Pacmans drawing
    private void pacMansDraw(Canvas canvas)
    {
        for(PackMan pacman:listOfPacs){

            float xPos = pacman.getxPos();
            float yPos = pacman.getyPos();


            Bitmap bitmap = pacman.getBitmapImage();
            canvas.drawBitmap(bitmap,xPos,yPos,null);
        }
    }//end pacMansDraw


    //Mark: - change image resource id to bitmap
    private void setBitmaps(){
        Random r = new Random();
        for (PackMan packMan : listOfPacs) {
            i = r.nextInt(2)+1;
            if(i == 1){
            packMan.setBitmapImage(pac1BitmapImage);

            }else {
                packMan.setBitmapImage(pac2BitmapImage);

            }

        }

    }//end setBitmaps



    //Mark: - moving the rocket
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        float touchedPosX = motionEvent.getX();
        float touchedPosY = motionEvent.getY();

        if(touchedPosY >= rocket.getyPos()) {
            float rocketNewXPos = touchedPosX - (rocket.getBitmap().getWidth() / 2);
            rocket.setxPos(rocketNewXPos);
        }

        return false;
    }


}
