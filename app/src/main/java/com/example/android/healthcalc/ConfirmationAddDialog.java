package com.example.android.healthcalc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationAddDialog extends DialogFragment implements View.OnClickListener {

    private Button mBtnAdd, mBtnCancel;
    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private TextView mTvConfirmation ;
    private String mStrNameFromBundle;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(getActivity());
        inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_confirmation_add_dialog,null);

        mBtnAdd = (Button) view.findViewById(R.id.btn_confirmation_add);
        mBtnCancel = (Button) view.findViewById(R.id.btn_confirmation_cancel);
        mTvConfirmation = (TextView) view.findViewById(R.id.tv_confirmation);
        mBtnAdd.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStrNameFromBundle = getArguments().getString("NameToDialog");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirmation_add:
                Intent mIntent = new Intent(getActivity(),AddFoodDatabaseScreen.class);
                mIntent.putExtra("FoodName",mStrNameFromBundle);
                startActivity(mIntent);
                ConfirmationAddDialog.this.getDialog().cancel();
                break;
            case R.id.btn_confirmation_cancel:
                ConfirmationAddDialog.this.getDialog().cancel();
                break;
        }
    }
}
