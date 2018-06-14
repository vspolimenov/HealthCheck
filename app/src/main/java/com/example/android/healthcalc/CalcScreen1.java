package com.example.android.healthcalc;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalcScreen1 extends AppCompatActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    private Context ctx = this;
    private EditText mEtYears, mEtMetersFeet, mEtCmInch, mEtKgLbs;
    private Button mBtnGoToCalcScreen2;
    private Intent mIntentGoToCalcScreen2;
    private int mIntYears;
    private float mFloatWeight, mFloatHeight;
    private boolean mBoolIsMale, mBoolMetrOrImper; //mBoolMetrOrImper true = Metric , false = Imperial
    private TabLayout mTlMetersFeet, mTlKgLbs, mTlMaleFemale;


    protected void init() {
        //Edit Text for entering years
        mEtYears = (EditText) findViewById(R.id.et_calc_screen1_years);
        //Edit Text for height - Meter or Feet
        mEtMetersFeet = (EditText) findViewById(R.id.et_calc_screen1_meters_feet);
        //Edit Text for height - Centimeter or Inch
        mEtCmInch = (EditText) findViewById(R.id.et_calc_screen1_sm_inch);
        //Edit Text for weight - kg or lbs
        mEtKgLbs = (EditText) findViewById(R.id.et_calc_screen1_kg_lbs);
        //Button to go to class CalcScreen2
        mBtnGoToCalcScreen2 = (Button) findViewById(R.id.btn_calc_screen1_go_calc_screen2);
        //Tab Layout to choose Meters or Feets
        mTlMetersFeet = (TabLayout) findViewById(R.id.tl_calc_screen1_meters_feet);
        //Tab Layout to choose Kg or Lbs
        mTlKgLbs=(TabLayout)findViewById(R.id.tl_calc_screen1_lbs_kg);
        //Tab Layout to choose Male or Female
        mTlMaleFemale=(TabLayout)findViewById(R.id.tl_calc_screen1_male_female);

        //Initial values
        mBoolIsMale = true;
        mBoolMetrOrImper = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_screen1);

        init();

        //Setting onClick and onTabSelected Listeners
        mBtnGoToCalcScreen2.setOnClickListener((View.OnClickListener) ctx);
        mTlMetersFeet.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) ctx);
        mTlKgLbs.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) ctx);



    }


    @Override
    public void onClick(View view) {

        //When mBtnGoToCalcScreen2( id = btn_calc_screen1_go_calc_screen2) is clicked -> go to CalcScreen2.class
        if (view.getId() == R.id.btn_calc_screen1_go_calc_screen2) {

            try {

                if (mBoolMetrOrImper == false) {
                    //If imperial transition data to metric
                    mFloatWeight =  Integer.valueOf(mEtKgLbs.getText().toString()) * 0.453592f; // weight in kg
                    mFloatHeight = Integer.valueOf(mEtMetersFeet.getText().toString()) * 30.48f +
                            Integer.valueOf(mEtCmInch.getText().toString()) * 2.54f; // height in cm
                } else {
                    //if metric transition meters to cm
                    mFloatWeight = Integer.valueOf(mEtKgLbs.getText().toString()); // weight in kg
                    mFloatHeight = Integer.valueOf(mEtMetersFeet.getText().toString())*100 +
                            Integer.valueOf(mEtCmInch.getText().toString()); // height in cm

                }

                mIntYears = Integer.parseInt(mEtYears.getText().toString());

                mIntentGoToCalcScreen2 = new Intent(ctx, CalcScreen2.class);
                mIntentGoToCalcScreen2.putExtra("Age",mIntYears);
                mIntentGoToCalcScreen2.putExtra("Gender",mBoolIsMale);
                mIntentGoToCalcScreen2.putExtra("Height", mFloatHeight);
                mIntentGoToCalcScreen2.putExtra("Weight", mFloatWeight);
                //send data and start new activity to get user activity level and to calculate
                //the daily calorie needs
                startActivity(mIntentGoToCalcScreen2);
            } catch (Exception ex) {
                Toast.makeText(ctx, "Wrong input", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //verifying that user data is only in metric or only in imperial
        //the tab layouts for meters/feet and kg/lbs are connected

        //Kilograms is selected
        if (tab.equals(mTlKgLbs.getTabAt(0))) {
            mTlMetersFeet.getTabAt(0).select();
            mBoolMetrOrImper = true;
        //Pounds are selected
        } else if (tab.equals(mTlKgLbs.getTabAt(1))) {
            mTlMetersFeet.getTabAt(1).select();
            mBoolMetrOrImper = false;
        //Meters are selected
        } else if (tab.equals(mTlMetersFeet.getTabAt(0))) {
            mTlKgLbs.getTabAt(0).select();
            mBoolMetrOrImper = true;
        //Feet are selected
        } else if (tab.equals(mTlMetersFeet.getTabAt(1))) {
            mTlKgLbs.getTabAt(1).select();
            mBoolMetrOrImper = false;
        //Male is selected
        } else if (tab.equals(mTlMaleFemale.getTabAt(0))) {
            mBoolIsMale = true;
        //Female is selected
        } else if (tab.equals(mTlMaleFemale.getTabAt(1))) {
            mBoolIsMale = false;
        }

    }



    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
