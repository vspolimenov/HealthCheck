package com.example.android.healthcalc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;

public class

OpenScreen extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnGoToCalcScreen1, mBtnHelloLayout, mBtnGoDb, mBtnGoDiary,mBtnMenu;
    private Intent mIntent;
    private Context ctx = this;
    private SharedPreferences mSharPref;
    private TextView mTvMaxCalories,mTvCurrentCalories,remainingCalories, mTvMaxProt, mTvCurrentProt,mTvMaxCarbs, mTvCurrentCarbs, mTvMaxFats,mTvCurrentFats;
    private int mIntCurrentCalories=0, mIntCurrentProt=0, mIntCurrentCarbs=0, mIntCurrentFats=0, maxCalories=0, mIntremainingCalories;
    private String mStrData;
    private ArrayList<Food> mArrListDataFromDb;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharPref = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE);

        //Checking which screen need to be shown based on the data in SharedPrefferences

        //If no data show the Hello screen
        if ((mSharPref.getInt("DailyCalories", 0)) == 0) {
            setContentView(R.layout.hello_layout);
            mBtnHelloLayout = (Button) findViewById(R.id.btn_hello_layout);
            mBtnHelloLayout.setOnClickListener((View.OnClickListener) ctx);

        } else {
            //If there is data in shared prefferences we show the main activity
            setContentView(R.layout.start_screen);
            initCalorioes();
            mBtnMenu.setOnClickListener((View.OnClickListener) ctx);
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        if ((mSharPref.getInt("DailyCalories", 0)) != 0) {
            mStrData = android.text.format.DateFormat.format("dd/MM/yyyy",new Date()).toString();
            mArrListDataFromDb = mDatabaseHelper.searchForDiary(mStrData);
            mIntCurrentCalories = 0;

            for(Food food: mArrListDataFromDb) {
                food.setmIntCalories(food.getmIntCalories()*(food.getmIntQuantity()/100));
                food.setmIntCarbs(food.getmIntCarbs()*(food.getmIntQuantity()/100));
                food.setmIntProtein(food.getmIntProtein()*(food.getmIntQuantity()/100));
                food.setmIntFats(food.getmIntFats()*(food.getmIntQuantity()/100));
            }

            for(Food food : mArrListDataFromDb) {
                mIntCurrentCalories += food.getmIntCalories();
                mIntCurrentProt += food.getmIntProtein();
                mIntCurrentCarbs += food.getmIntCarbs();
                mIntCurrentFats += food.getmIntFats();
            }
            mIntremainingCalories = maxCalories - mIntCurrentCalories;
            remainingCalories.setText(String.valueOf(mIntremainingCalories));
            if(mIntremainingCalories > 0) {
                remainingCalories.setTextColor(Color.parseColor("green"));
            } else if (mIntremainingCalories == 0 ) {
                remainingCalories.setTextColor(Color.parseColor("grey"));
            } else {
                remainingCalories.setTextColor(Color.parseColor("red"));
            }

        }
    }

    protected void initCalorioes() {
        //binding the UI elements to variables
        mDatabaseHelper = new DatabaseHelper(this);
        remainingCalories=(TextView)findViewById(R.id.textView);
        mBtnMenu = (Button) findViewById(R.id.button);
        maxCalories = mSharPref.getInt("DailyCalories",0);
    }


    @Override
    public void onClick(View view) {
         if (view.getId() == R.id.button) {

            mIntent = new Intent(ctx, Menu.class);
            startActivity(mIntent);
        } else if (view.getId() == R.id.btn_hello_layout) {

             mIntent = new Intent(ctx, CalcScreen1.class);
             startActivity(mIntent);
         }
    }
}