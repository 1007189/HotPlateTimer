package com.example.student.hotplatetimer;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Timer;


public class FoodsActivity extends ActionBarActivity {

    ArrayList<Food> foodArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);

        //get passed data form intent
        foodArr = getIntent().getParcelableArrayListExtra("foodArr");

        //create buttons for scrollview based on foodArr entries
       Button[] myButton = new Button[50];
        LinearLayout scrViewButLay = (LinearLayout) findViewById(R.id.scrollViewLinLay);
        for (int i = 0; i < foodArr.size(); i++) {

            myButton[i] = new Button(this);
            myButton[i].setText(foodArr.get(i).getFoodName());

            myButton[i].setOnClickListener(new MyOnClickListerner(i) {
                public void onClick(View v) {
                    Intent showTimer = new Intent(FoodsActivity.this, TimerActivity.class);
                    showTimer.putExtra("foodname",foodArr.get(i).getFoodName());
                    showTimer.putExtra("cooktime",foodArr.get(i).getCookTime());
                    startActivity(showTimer);
                }
            });

            scrViewButLay.addView(myButton[i]);
            Log.d("CRTBUTTON", "" + i);


        }
    }

    //custom onclicklister calss to pass in count i
    public class MyOnClickListerner implements View.OnClickListener
    {
        int i;
        public MyOnClickListerner(int i) {
            this.i = i;
        }
    @Override
        public void onClick(View v) {

    }

    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_foods, menu);
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


    @Override
    //Override back button to stop auto-back into AddActivity
    public void onBackPressed(){
        Intent showMain = new Intent(this, MainActivity.class);
        //Pass array back via intent
        showMain.putParcelableArrayListExtra("foodArr",foodArr);
        startActivity(showMain);

        super.onBackPressed();
    }

    public void switchFoodAdd(View view) {
        Intent showFoodAdd = new Intent(this, AddFoodActivity.class);
        //pass main data array with intents parcelable
        showFoodAdd.putParcelableArrayListExtra("foodArr",foodArr);
        startActivity(showFoodAdd);
    }
}
