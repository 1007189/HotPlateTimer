package com.example.student.hotplatetimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class TimerActivity extends ActionBarActivity {


    //start pause buttons
    private Button startButton;
    private Button pauseButton;
    //TextView for timer field
    private TextView timerValue;
    private long downtime = 30000;
    private long updateinterval = 100;
    ArrayList<Food> foodArr;
    String foodname;
    Integer cookTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
       //get passed data from intent
        foodArr = getIntent().getParcelableArrayListExtra("foodArr");

     //   Bundle extras = getIntent().getExtras();
     //   if (extras != null) {
     //       String value = extras.getString("new_variable_name");
      //  }

        foodname = getIntent().getExtras().getString("foodname");
        cookTime = getIntent().getExtras().getInt("cooktime");
        //hook id of timer textview to var timerValue
        timerValue = (TextView) findViewById(R.id.tv_timerValue);

      // CountDownTimer myCountDownTimer = (CountDownTimer)

        Long longCookTime = Long.valueOf(cookTime * 1000);
       //CountdownTimer counts down time, updates textview every second, displays message on timeup

        new CountDownTimer(longCookTime, updateinterval){

            public void onTick(long millsUntilFinished) {
                timerValue.setText("" + millsUntilFinished / 1000);

            }

            public void onFinish() {
                timerValue.setText("Time Up!");
                playSound();

            }

        }.start();

    }
    //plays some sound from R.raw
    public void playSound() {

        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.alarm); //odd public domain alarm.mp3
        mp.start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
