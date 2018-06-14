package com.example.android.healthcalc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fotev on 10/12/2016.
 */
public class Food implements Parcelable {

    //Class maping the columns in Foods database

    private int mIntId;
    private String mStrName;
    private int mIntCalories;
    private int mIntProtein;
    private int mIntCarbs;
    private int mIntFats;
    private int mIntVitA;
    private int mIntVitB6;
    private int mIntVitC;
    private int mIntVitD;
    private int mIntZinc;
    private int mIntMagnesium;
    private int mIntIron;
    private int mIntQuantity;

    public Food(int mIntId, String mStrName, int mIntCalories, int mIntProtein,
                 int mIntCarbs, int mIntFats, int mIntVitA, int mIntVitB6, int mIntVitC,
                 int mIntVitD, int mIntZinc, int mIntMagnesium, int mIntIron) {
        this.mIntId = mIntId;
        this.mStrName = mStrName;
        this.mIntCalories = mIntCalories;
        this.mIntProtein = mIntProtein;
        this.mIntCarbs = mIntCarbs;
        this.mIntFats = mIntFats;
        this.mIntVitA = mIntVitA;
        this.mIntVitB6 = mIntVitB6;
        this.mIntVitC = mIntVitC;
        this.mIntVitD = mIntVitD;
        this.mIntZinc = mIntZinc;
        this.mIntMagnesium = mIntMagnesium;
        this.mIntIron = mIntIron;
    }

    public Food(String mStrName, int mIntCalories, int mIntProtein,
                int mIntCarbs, int mIntFats, int mIntVitA, int mIntVitB6, int mIntVitC,
                int mIntVitD, int mIntZinc, int mIntMagnesium, int mIntIron) {
        this.mStrName = mStrName;
        this.mIntCalories = mIntCalories;
        this.mIntProtein = mIntProtein;
        this.mIntCarbs = mIntCarbs;
        this.mIntFats = mIntFats;
        this.mIntVitA = mIntVitA;
        this.mIntVitB6 = mIntVitB6;
        this.mIntVitC = mIntVitC;
        this.mIntVitD = mIntVitD;
        this.mIntZinc = mIntZinc;
        this.mIntMagnesium = mIntMagnesium;
        this.mIntIron = mIntIron;
    }

    public Food(String mStrName, int mIntCalories, int mIntProtein,
                int mIntCarbs, int mIntFats) {
        this.mStrName = mStrName;
        this.mIntCalories = mIntCalories;
        this.mIntProtein = mIntProtein;
        this.mIntCarbs = mIntCarbs;
        this.mIntFats = mIntFats;
    }

    public Food(){}

    public Food(Parcel in){

        this.mIntId = in.readInt();
        this.mStrName = in.readString();
        this.mIntCalories = in.readInt();
        this.mIntProtein = in.readInt();
        this.mIntCarbs = in.readInt();
        this.mIntFats = in.readInt();
        this.mIntVitA = in.readInt();
        this.mIntVitB6 = in.readInt();
        this.mIntVitC = in.readInt();
        this.mIntVitD = in.readInt();
        this.mIntZinc = in.readInt();
        this.mIntMagnesium = in.readInt();
        this.mIntIron = in.readInt();
        this.mIntQuantity=in.readInt();
    }

    public int getmIntId() {
        return mIntId;
    }

    public void setmIntId(int mIntId) {
        this.mIntId = mIntId;
    }

    public String getmStrName() {
        return mStrName;
    }

    public void setmStrName(String mStrName) {
        this.mStrName = mStrName;
    }

    public int getmIntCalories() {
        return mIntCalories;
    }

    public void setmIntCalories(int mIntCalories) {
        this.mIntCalories = mIntCalories;
    }

    public int getmIntProtein() {
        return mIntProtein;
    }

    public void setmIntProtein(int mIntProtein) {
        this.mIntProtein = mIntProtein;
    }

    public int getmIntCarbs() {
        return mIntCarbs;
    }

    public void setmIntCarbs(int mIntCarbs) {
        this.mIntCarbs = mIntCarbs;
    }

    public int getmIntFats() {
        return mIntFats;
    }

    public void setmIntFats(int mIntFats) {
        this.mIntFats = mIntFats;
    }

    public int getmIntVitA() {
        return mIntVitA;
    }

    public void setmIntVitA(int mIntVitA) {
        this.mIntVitA = mIntVitA;
    }

    public int getmIntVitB6() {
        return mIntVitB6;
    }

    public void setmIntVitB6(int mIntVitB6) {
        this.mIntVitB6 = mIntVitB6;
    }

    public int getmIntVitC() {
        return mIntVitC;
    }

    public void setmIntVitC(int mIntVitC) {
        this.mIntVitC = mIntVitC;
    }

    public int getmIntVitD() {
        return mIntVitD;
    }

    public void setmIntVitD(int mIntVitD) {
        this.mIntVitD = mIntVitD;
    }

    public int getmIntZinc() {
        return mIntZinc;
    }

    public void setmIntZinc(int mIntZinc) {
        this.mIntZinc = mIntZinc;
    }

    public int getmIntMagnesium() {
        return mIntMagnesium;
    }

    public void setmIntMagnesium(int mIntMagnesium) {
        this.mIntMagnesium = mIntMagnesium;
    }

    public int getmIntIron() {
        return mIntIron;
    }

    public void setmIntIron(int mIntIron) {
        this.mIntIron = mIntIron;
    }

    public void setmIntQuantity(int mIntQuantity) {
        this.mIntQuantity = mIntQuantity;
    }

    public int getmIntQuantity() {
        return mIntQuantity;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mIntId);
        parcel.writeString(mStrName);
        parcel.writeInt(mIntCalories);
        parcel.writeInt(mIntCarbs);
        parcel.writeInt(mIntFats);
        parcel.writeInt(mIntProtein);
        parcel.writeInt(mIntVitA);
        parcel.writeInt(mIntVitB6);
        parcel.writeInt(mIntVitC);
        parcel.writeInt(mIntVitD);
        parcel.writeInt(mIntZinc);
        parcel.writeInt(mIntMagnesium);
        parcel.writeInt(mIntIron);
        parcel.writeInt(mIntQuantity);
    }

    public static final Parcelable.Creator<Food> CREATOR
            = new Parcelable.Creator<Food>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Food createFromParcel(Parcel in) {
            return new  Food(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
