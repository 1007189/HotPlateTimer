package com.example.student.hotplatetimer;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {


    private String filein;
    public static ArrayList<Food> foodArr;
    private Boolean firstTime;
    //ArrayList<Food> foodArr;
   // public boolean startflag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // foodArr = new ArrayList<>();

        //Experiments with the food class and write/read file
        //Food chicken = new Food("Chicken", 20, "oven");

        //writeToFile(chicken.getFoodForStorage());
        // writeToFile(chips.getFoodForStorage());
        //writeToFile(peas.getFoodForStorage());

        // foodArr.get(0).getFoodName();

        //tv test output for fileread/write
        //TextView tv = (TextView) findViewById(R.id.tv_test);

        //Reads saved data file and populates food arrary for use

        if (isFirstTime() == false) {
            filein = readFromFile();
            FileOps(filein);
            isNotFirstTime();
            Log.d("ISFIRSTTIME", "false");
        }
        else {

            //if not first execution read in array from intent pass
            Log.d("ELSEHIT", "yup");
            Intent get = getIntent();
            foodArr = get.getParcelableArrayListExtra("foodArr");
            //isNotFirstTime();
            //Log.d("ISFIRSTTIME", "true");
        }


        //Update Favourite Buttons to Reflect File Read
        //If less than five foods
        if (foodArr.size() < 5){
            Button[] btn_fav = new Button[5];
            for (int s = 0; s < foodArr.size(); s++) {

                //btn_fav[s] = new Button(this);
                //find view by variant string using getIdentifier
                int resId = getResources().getIdentifier("btn_fav" + s, "id", MainActivity.this.getPackageName());
                Log.d("BUTTON", "" + resId);
                //this line...
                btn_fav[s] = ((Button) findViewById(resId));
            /*btn_fav[s].setText*/
                Log.d("arrayget", foodArr.get(s).getFoodName());
                btn_fav[s].setText(foodArr.get(s).getFoodName());
            }
        }
        //else five or more
        else {
            Button[] btn_fav = new Button[5];
            for (int s = 0; s < 5 /*foodArr.size()*/; s++) {

                //btn_fav[s] = new Button(this);
                //find view by variant string using getIdentifier
                int resId = getResources().getIdentifier("btn_fav" + s, "id", MainActivity.this.getPackageName());
                Log.d("BUTTON", "" + resId);
                //this line...
                btn_fav[s] = ((Button) findViewById(resId));
            /*btn_fav[s].setText*/
                Log.d("arrayget", foodArr.get(s).getFoodName());
                btn_fav[s].setText(foodArr.get(s).getFoodName());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    //Method switches to Foods activity onClick -see XML for call
    public void switchFoods (View view) {
        Intent showFoods = new Intent(this, FoodsActivity.class);
        //pass main data array with intents parcelable
        showFoods.putParcelableArrayListExtra("foodArr",foodArr);
        startActivity(showFoods);
    }

    //Method switches to Meals activity onClick -see XML for call
    public void switchMeals (View view) {
        Intent showMeals = new Intent(this, MealsActivity.class);
        showMeals.putParcelableArrayListExtra("foodArr",foodArr);
        startActivity(showMeals);
    }

    //Method switches to Timer activity onClick -see XML for call
    public void switchTimer (View view) {
        Intent showTImer = new Intent(this, TimerActivity.class);
        //showTImer.getExtras()
        startActivity(showTImer);
    }

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        }
        return firstTime;
    }

    private boolean isNotFirstTime() {
        if (firstTime == false) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();

        }
        return firstTime;
    }


    //method for reading in from file
    private String readFromFile() {
        //return read in data as string
        String ret = "";

        try {

            //create input stream object (byte stream), check that file exists and open it
            InputStream inputStream = openFileInput("new10.txt");

            //if file exists and inputstream created
            if ( inputStream != null ) {
                //create inputsteam reader with inputstream (bytes to unicode)
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //create bufferedreader (reads unicode stream)
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                //create string builder, (many unicode char to string)
                StringBuilder stringBuilder = new StringBuilder();

                //While there is still a data stream
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    //add that data to recieve string
                    stringBuilder.append(receiveString);
                }

                //no more data stream, close object
                inputStream.close();

                //set read in data to return string
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Exception from readFromFile", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Exception from readFromFile", "Can not read file: " + e.toString());
        }

        return ret;
    }

    //method for writing to file
    private void writeToFile(String data) {
        try {
            //create output stream writer object, define filename and mode
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("new10.txt", Context.MODE_APPEND));
            //write string to file -see Food.java for string building
            outputStreamWriter.write(data);
            //Using '&' to separate objects in file.
            outputStreamWriter.write("&");
            //close osw object, may need to add flush?
            outputStreamWriter.close();

        }
        catch (IOException e) {
            //Exception for when can't get lock to write.
            Log.e("Exception from writeToFile", "File write failed: " + e.toString());
        }
    }

        // Checks if external storage is available for read and write /UNUSED/
        public boolean isExternalStorageWritable() {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                return true;
            }
            return false;
        }

        // Checks if external storage is available to at least read /UNUSED/
        public boolean isExternalStorageReadable() {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state) ||
                    Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                return true;
            }
            return false;
        }


    //method for splitting save file into food items
    public String FileOps(String data) {

      // ArrayList<Food> foodArr = new ArrayList<>();

        //Dirty return value, fix this later
        String foodOne = "";

        // create string array for storing individual object attributes
        String[] splitattribs = new String[]{};


        //splits string (read in from file by '&' symbol to separate food objects) adds
        //each object to string array foods
        String[] foods = data.split("&");

        //create arraylist
        foodArr = new ArrayList<>();
        //for loop cycles through all object within food array
        for (int i = 0; i < foods.length; i++) {

            //Log.d("FILEOPS" + i, foods[i]); //Output to Log file for testing

            //split each array object by ',' to give individual object attributes store in array
            splitattribs = foods[i].split(",");

            //set array items to type correct variables for ease
            String name = splitattribs[0];
            Integer time = Integer.parseInt(splitattribs[1]);
            String meth = splitattribs[2];

            //Create the actual food object from Food class
            foodArr.add(new Food(name,time,meth));


            }

        return foodOne;
        }


    }












