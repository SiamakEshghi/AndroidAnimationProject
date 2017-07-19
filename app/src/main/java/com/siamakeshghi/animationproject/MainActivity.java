package com.siamakeshghi.animationproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.siamakeshghi.animationproject.Model.Animation;
import com.siamakeshghi.animationproject.Model.CustomView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linGame;
    Button btnStart,btnStop;
    CustomView customview;
    Animation animation;
    TextView catchView,noCatchView,score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        linGame = (LinearLayout) findViewById(R.id.linGame);

        btnStart = (Button) findViewById(R.id.startBtn);
        btnStop = (Button) findViewById(R.id.stopBtn);
        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        customview = new CustomView(this);
        linGame.addView(customview);

        animation = new Animation(customview);


        catchView = (TextView) findViewById(R.id.catchTextView);
        noCatchView = (TextView) findViewById(R.id.noCatchTextView);
        score = (TextView) findViewById(R.id.scoreTextView);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.startBtn :
                animation.start();
                break;
            case R.id.stopBtn :
                catchView.setText("CATCHED: "+String.valueOf(customview.rocketTouchCounter));
                noCatchView.setText("NO CATCHED: "+String.valueOf(customview.rocketPasscounter));
                score.setText(String.valueOf("SCORE: "+customview.score));

                animation.stop();
                break;
        }

    }
}
