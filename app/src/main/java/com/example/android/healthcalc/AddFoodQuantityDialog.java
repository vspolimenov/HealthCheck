package com.example.android.healthcalc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Stanislav on 10/19/2016.
 */
public class AddFoodQuantityDialog extends DialogFragment implements View.OnClickListener {


    //interface for comunication between dialog anddatabase screen
    public interface IDialogListener{
        void onAddBtnClicked(int quantity);
    }

    public static IDialogListener dialogListener;

    private Button mBtnAdd, mBtnCancel;
    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private EditText mEtQuantity;
    private int mIntQuantity;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(getActivity());
        inflater = getActivity().getLayoutInflater();

        //inflating view
        View view = inflater.inflate(R.layout.add_food_quantity_dialog, null);

        mBtnAdd = (Button) view.findViewById(R.id.btn_food_quantity_add);
        mBtnCancel = (Button) view.findViewById(R.id.btn_food_quantity_cancel);
        mEtQuantity = (EditText) view.findViewById(R.id.et_food_quantity);
        dialogListener=(IDialogListener) getActivity();

        mBtnCancel.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);

        //set view to dialog  bilder
        builder.setView(view);

        builder.setCancelable(false);


        return builder.create();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_food_quantity_add){
            mIntQuantity = Integer.valueOf(mEtQuantity.getText().toString());
            dialogListener.onAddBtnClicked(mIntQuantity);
            AddFoodQuantityDialog.this.getDialog().cancel();

        }
        else if(view.getId() == R.id.btn_food_quantity_cancel){
            AddFoodQuantityDialog.this.getDialog().cancel();
        }
    }
}
