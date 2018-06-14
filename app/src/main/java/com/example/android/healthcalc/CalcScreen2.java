package com.example.android.healthcalc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class CalcScreen2 extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private Context ctx = this;
    private Intent mIntentFromCalcScreen1, mIntentToOpenActivity;
    private int mIntAge, mIntDailyCalories, mIntProtein, mIntCarbs, mIntFats;
    private boolean mBoolIsMale;
    private float mFloatHeight, mFloatWeight, mFloatActivityLvl;
    private RadioGroup mRgActivityLvl;
    private SharedPreferences mSharPref;
    private SharedPreferences.Editor mSharPrefEditor;
    private Button mBtnCalcCals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_screen2);
        init();

        mIntentFromCalcScreen1 = getIntent();
        mIntAge = mIntentFromCalcScreen1.getIntExtra("Age", 0);
        mBoolIsMale = mIntentFromCalcScreen1.getBooleanExtra("Gender", true);
        mFloatHeight = mIntentFromCalcScreen1.getFloatExtra("Height", 0.00f);
        mFloatWeight = mIntentFromCalcScreen1.getFloatExtra("Weight", 0.00f);
        mRgActivityLvl.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) ctx);
    }

    protected void init() {
        mIntentFromCalcScreen1 = new Intent();
        mRgActivityLvl = (RadioGroup) findViewById(R.id.rg_calc_screen2_activity_lvl);
        mSharPref = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);
        mSharPrefEditor = mSharPref.edit();
        mBtnCalcCals = (Button) findViewById(R.id.btn_calc_screen2);
        mBtnCalcCals.setOnClickListener(this);
        //Initial value of activity lvl, because first radio button is checked by default
        mFloatActivityLvl = 1.25f;

    }

    public void calculateDailyCalories(View view) {

        /*W = weight in kilograms (weight (lbs)/2.2) =weight in kg
        H = height in centimeters (inches x 2.54) =height in cm
        A = age in years

        Men: BMR=66.47+ (13.75 x W) + (5.0 x H) - (6.75 x A)
        Women: BMR=665.09 + (9.56 x W) + (1.84 x H) - (4.67 x A)*/

        if (mBoolIsMale) {
            mIntDailyCalories = Math.round(66.47f + (13.75f * mFloatWeight) + (5 * mFloatHeight) - (6.75f * mIntAge));
        } else {
            mIntDailyCalories = Math.round(665.09f + (9.56f * mFloatWeight) + (1.84f * mFloatHeight) - (4.67f * mIntAge));
        }
        // adapting calories to the activity level
        mIntDailyCalories = Math.round(mIntDailyCalories * mFloatActivityLvl);

        //Calculating macros
        mIntProtein = Math.round(mFloatWeight * 2.204f);
        mIntFats = Math.round(mFloatWeight);
        mIntCarbs = Math.round((mIntDailyCalories - mIntProtein * 4 - mIntFats * 9) / 4);

        //Saving data in shared prefferences
        mSharPrefEditor.putInt("DailyCalories", mIntDailyCalories);
        mSharPrefEditor.putInt("Protein", mIntProtein);
        mSharPrefEditor.putInt("Fats", mIntFats);
        mSharPrefEditor.putInt("Carbs", mIntCarbs);
        mSharPrefEditor.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        //gets the checked radio button and assigns
        // the corresponding value to mFloatActivityLvl

        if (i == R.id.rb_calc_screen2_low) {
            mFloatActivityLvl = 1.25f;
        } else if (i == R.id.rb_calc_screen2_light) {
            mFloatActivityLvl = 1.375f;
        } else if (i == R.id.rb_calc_screen2_moderate) {
            mFloatActivityLvl = 1.55f;
        } else if (i == R.id.rb_calc_screen2_active) {
            mFloatActivityLvl = 1.725f;
        } else if (i == R.id.rb_calc_screen2_extreme) {
            mFloatActivityLvl = 1.9f;
        }


    }

    @Override
    public void onClick(View view) {

        // Setting onClick function to go to the main screen
        if (view.getId() == R.id.btn_calc_screen2) {
            calculateDailyCalories(view);
            mIntentToOpenActivity = new Intent(ctx, OpenScreen.class);
            startActivity(mIntentToOpenActivity);
        }
    }
}
