package com.example.student.hotplatetimer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Student on 02/12/2014.
 */
public class Food implements Parcelable{

    private String foodName;
    private Integer cookTime;
    private String cookMeth;

     Food(String name, Integer time, String method) {

        foodName = name;
        cookTime = time;
        cookMeth = method;
    }
    //Method to create string for file writing
    String getFoodForStorage(){

        String alldetails = foodName +','+ cookTime.toString()+','+ cookMeth;
        return alldetails;

    }

    String getFoodName(){
        return foodName;
    }
    Integer getCookTime() {return cookTime;}

    //Parcel Stuff
    private Food(Parcel in){
        foodName = in.readString();
        cookTime = in.readInt();
        cookMeth = in.readString();
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeString(foodName);
        out.writeInt(cookTime);
        out.writeString(cookMeth);
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>(){
        public Food createFromParcel(Parcel in){
            return new Food(in);
        }
        public Food[] newArray(int size){
            return new Food[size];
        }
    };

//
//
// String getFoodForUse(String foodArrayItem) {
//
//            String ret = "";
//
//            String attribs = foodArrayItem;
//            String[] splitattribs = attribs.split(",");
//            for (int i = 0; i < splitattribs.length; i++) {
//                Log.d("getFoodForUSe" + i, splitattribs[i]);
//                foodName = splitattribs[0];
//                cookTime = splitattribs[1];
//                cookMeth = splitattribs[2];
//                Food foodName = new Food(foodName, cookTime, cookMeth);
//            }
//            return ret;
//        }





}