package com.example.android.healthcalc;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Fotev on 10/20/2016.
 */
public class AddFoodDatabaseScreen extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtFoodName, mEtCalories, mEtProteins, mEtCarbs, mEtFats;
    private Button mBtnAdd;
    private String mStringFoodName;
    private int mIntCalories, mIntProteins, mIntCarbs, mIntFats;
    private DatabaseHelper mDatabaseHelper;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_database_screen);
        init();

        mBtnAdd.setOnClickListener(this);
        mEtFoodName.setText(getIntent().getStringExtra("FoodName"));
    }

    protected void init(){
        mEtFoodName=(EditText)findViewById(R.id.et_add_in_db_foodname);
        mEtCalories=(EditText)findViewById(R.id.et_add_in_db_calories);
        mEtProteins=(EditText)findViewById(R.id.et_add_in_db_proteins);
        mEtCarbs=(EditText)findViewById(R.id.et_add_in_db_carbs);
        mEtFats=(EditText)findViewById(R.id.et_add_in_db_fats);
        mBtnAdd=(Button)findViewById(R.id.btn_add_in_db_add);
        mDatabaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View view) {

        mStringFoodName=mEtFoodName.getText().toString();
        mIntCalories=Integer.valueOf(mEtCalories.getText().toString());
        mIntProteins=Integer.valueOf(mEtProteins.getText().toString());
        mIntCarbs=Integer.valueOf(mEtCarbs.getText().toString());
        mIntFats=Integer.valueOf(mEtFats.getText().toString());

        if(view.getId()==R.id.btn_add_in_db_add){
            boolean check=mDatabaseHelper.addItemInFoodTable(mStringFoodName,mIntCalories,mIntProteins,mIntCarbs,mIntFats,0,0,0,0,0,0,0);
            if(check){

                mIntent=new Intent(this,DatabaseScreen.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mIntent);
            }
            else {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
