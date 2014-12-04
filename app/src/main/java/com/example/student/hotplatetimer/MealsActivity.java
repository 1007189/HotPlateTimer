package com.example.student.hotplatetimer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class MealsActivity extends ActionBarActivity {

    ArrayList<Food> foodArr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        //get passed data form intent
        foodArr = getIntent().getParcelableArrayListExtra("foodArr");

        //create toggle buttons
        ToggleButton[] myButton = new ToggleButton[50];
        LinearLayout scrViewButLay = (LinearLayout) findViewById(R.id.scrollViewLinLaymeals);
        for (int i = 0; i < foodArr.size(); i++) {

            myButton[i] = new ToggleButton(this);
            myButton[i].setText(foodArr.get(i).getFoodName());
            myButton[i].setTextOff(foodArr.get(i).getFoodName());
            myButton[i].setTextOn(foodArr.get(i).getFoodName());

            myButton[i].setOnClickListener(new MyOnClickListerner(i) {
                public void onClick(View v) {
                    Intent showTimer = new Intent(MealsActivity.this, TimerActivity.class);
                    showTimer.putExtra("foodname",foodArr.get(i).getFoodName());
                    showTimer.putExtra("cooktime",foodArr.get(i).getCookTime());
                    //startActivity(showTimer);
                }
            });

            scrViewButLay.addView(myButton[i]);
            Log.d("CRTBUTTON", "" + i);


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meals, menu);
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

    public class MyOnCheckedChangeListerner implements CompoundButton.OnCheckedChangeListener(){

        int i;
        public MyOnCheckedChangeListerner(int i) {
            this.i = i;
        }

        @Override
        public void onCheckedChange(View v) {

        }

    };

    //Method switches to Timer activity onClick -see XML for call
    public void switchTimer (View view) {
        Intent showTImer = new Intent(this, TimerActivity.class);
        //showTImer.getExtras()
        startActivity(showTImer);
    }


}
