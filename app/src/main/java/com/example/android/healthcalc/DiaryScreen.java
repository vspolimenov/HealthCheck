package com.example.android.healthcalc;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

public class DiaryScreen extends AppCompatActivity implements View.OnClickListener {

    private int mIntCurrentCalories=0, mIntCurrentProt=0, mIntCurrentCarbs=0, mIntCurrentFats=0;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration mItemDecoration;
    private DatabaseHelper databaseHelper;
    private Context ctx = this;
    private String mCurrentDate, mTempDate;
    private Intent mIntent;
    private GestureDetectorCompat mDetector;
    final static private int SWIPE_DISTANCE_THRESHOLD = 100;
    final static private int SWIPE_VELOCITY_THRESHOLD = 100;
    private TextView mTvDate;
    private Button mBtnToPrev, mBtnToNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_screen);

        init();

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        mCurrentDate = DateFormat.format("dd/MM/yyyy", new Date()).toString();


        mTvDate.setText(String.valueOf(mCurrentDate));
        showFood(mCurrentDate);

    }

    protected ArrayList<Food> getFood(String date) {
        ArrayList<Food> mArrListDataFromDb;
        mArrListDataFromDb = databaseHelper.searchForDiary(date);
        for(Food food : mArrListDataFromDb){
            mIntCurrentCalories+=food.getmIntCalories();
            mIntCurrentProt+=food.getmIntProtein();
            mIntCurrentCarbs+=food.getmIntCarbs();
            mIntCurrentFats+=food.getmIntFats();
        }
        return mArrListDataFromDb;
    }
    private void showFood(String date) {
        ArrayList<Food> mArrListDataFromDb = getFood(date);



        if (mArrListDataFromDb != null) {
            if (mArrListDataFromDb.size() != 0) {


                for(Food food: mArrListDataFromDb) {
                    food.setmIntCalories(food.getmIntCalories()*(food.getmIntQuantity()/100));
                    food.setmIntCarbs(food.getmIntCarbs()*(food.getmIntQuantity()/100));
                    food.setmIntProtein(food.getmIntProtein()*(food.getmIntQuantity()/100));
                    food.setmIntFats(food.getmIntFats()*(food.getmIntQuantity()/100));
                }
                mAdapter = new RecViewAdapterDiary(mArrListDataFromDb);
                mRecyclerView.setAdapter(mAdapter);

        }

    }
    }
    public void init() {

        mTvDate = (TextView) findViewById(R.id.tv_diary_date);
        mItemDecoration = new RecViewItemDecoration();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_diary_screen);
        mLayoutManager = new LinearLayoutManager(ctx);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mItemDecoration);
        mBtnToPrev = (Button) findViewById(R.id.btn_diary_toprev);
        mBtnToNext = (Button) findViewById(R.id.btn_diary_tonext);
        mBtnToPrev.setOnClickListener(this);
        mBtnToNext.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(this);
        mIntent = new Intent();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_diary_tonext:
                onSwipeLeft();
                break;
            case R.id.btn_diary_toprev:
                onSwipeRight();
                break;
        }
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
            }
            return false;
        }
    }

    private void onSwipeRight() {
        int februaryDays = 28, day, month, year;
        day = Integer.valueOf(mCurrentDate.substring(0, 2));
        if(mCurrentDate.substring(4,5).equals("/")){
            month = Integer.valueOf(mCurrentDate.substring(3, 4));
            year = Integer.valueOf(mCurrentDate.substring(5));
        }else{
            month = Integer.valueOf(mCurrentDate.substring(3, 5));
            year = Integer.valueOf(mCurrentDate.substring(6));
        }


        final int[] leapYears = {2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, 2048};
        for (int i = 0; i < leapYears.length; i++) {
            if (year == leapYears[i]) {
                februaryDays = 29;
                break;
            }
            if ((year < leapYears[i + 1]) && (i != (leapYears.length - 1))) {
                break;
            }
        }
        int[] intArrayCalendar = {0, 31, februaryDays, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if(day > 1){
            day--;
        }else {
            if (month > 1) {
                month--;
                day = intArrayCalendar[month];
            } else {
                year--;
                month = 12;
                day = intArrayCalendar[month];
            }
        }
        if(day < 10){
            mTempDate = "0" + String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
        }else{
            mTempDate =  String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
        }

        mCurrentDate = mTempDate;
        showFood(mTempDate);
        Toast.makeText(ctx, mTempDate, Toast.LENGTH_LONG).show();
        mTvDate.setText(mTempDate);
    }

    private void onSwipeLeft() {
        int februaryDays = 28, day, month, year;
        day = Integer.valueOf(mCurrentDate.substring(0, 2));
        if(mCurrentDate.substring(4,5).equals("/")){
            month = Integer.valueOf(mCurrentDate.substring(3, 4));
            year = Integer.valueOf(mCurrentDate.substring(5));
        }else{
            month = Integer.valueOf(mCurrentDate.substring(3, 5));
            year = Integer.valueOf(mCurrentDate.substring(6));
        }


        final int[] leapYears = {2016, 2020, 2024, 2028, 2032, 2036, 2040, 2044, 2048};
        for (int i = 0; i < leapYears.length; i++) {
            if (year == leapYears[i]) {
                februaryDays = 29;
                break;
            }
            if ((year < leapYears[i + 1]) && (i != (leapYears.length - 1))) {
                break;
            }
        }
        int[] intArrayCalendar = {0, 31, februaryDays, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if(day < intArrayCalendar[month]){
            day++;
        }else {
            if (month < 12) {
                month++;
                day = 1;
            } else {
                year++;
                month = 1;
                day = 1;
            }
        }
        if(day < 10){
            mTempDate = "0" + String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
        }else{
            mTempDate =  String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
        }

        mCurrentDate = mTempDate;
        showFood(mTempDate);
        Toast.makeText(ctx, mTempDate, Toast.LENGTH_LONG).show();
        mTvDate.setText(mTempDate);

    }
}
