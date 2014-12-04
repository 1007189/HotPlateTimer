package com.example.student.hotplatetimer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class AddFoodActivity extends ActionBarActivity {


    ArrayList<Food> foodArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);


        //get passed data form intent
        foodArr = getIntent().getParcelableArrayListExtra("foodArr");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_food, menu);
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

    //Method adds food onClick to foodArr -see XML for call
    public void addFood (View view) {

        EditText enterFood = (EditText) findViewById(R.id.et_addfood);
        EditText enterCookTime = (EditText) findViewById(R.id.et_addtime);

        //get text input and set to string
        String name = enterFood.getText().toString();
        String strtime = enterCookTime.getText().toString();
        Integer time = Integer.parseInt(strtime);

        //Add to array of foods
        foodArr.add(new Food(name, time, "oven"));

        //Return to Foods and pass back data
        Intent showFoods = new Intent(this, FoodsActivity.class);
        showFoods.putParcelableArrayListExtra("foodArr",foodArr);
        startActivity(showFoods);

    }

}
