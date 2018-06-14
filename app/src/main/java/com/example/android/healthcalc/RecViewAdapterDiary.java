package com.example.android.healthcalc;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fotev on 10/23/2016.
 */
public class RecViewAdapterDiary extends RecyclerView.Adapter<RecViewAdapterDiary.ViewHolder> {

    public interface IRVdiaryOnClick{
        void onItemSelected(int position);
    }

    private IRVdiaryOnClick mListener;
    private String mStringG;
    private ArrayList<Food> mAdapterData;
    private int mExpandedPosition = -1;
    private RecyclerView mRecyclerView;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName, mTvCal, mTvProt, mTvCarbs, mTvFats, mTvVitA, mTvVitB, mTvVitC, mTvVitD,
                mTvZinc, mTvMagnesium, mTvIron, mTvQuantity;
        int position;
        LinearLayout mLinearLayout;

        private void getItemPosition(int position){
            this.position=position;
        }

        public ViewHolder(View itemView) {
            super(itemView);

            mTvName=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_name);
            mTvCal=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_cal);
            mTvProt=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_prot);
            mTvCarbs=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_carbs);
            mTvFats=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_fats);
            mTvVitA=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_vitA);
            mTvVitB=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_vitB6);
            mTvVitC=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_vitC);
            mTvVitD=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_vitD);
            mTvZinc=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_zinc);
            mTvMagnesium=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_magnesium);
            mTvIron=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_iron);
            mTvQuantity=(TextView)itemView.findViewById(R.id.tv_rec_view_diary_item_quantity);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ll_rec_view_diary_expand);
        }
    }

    public RecViewAdapterDiary(ArrayList<Food> data) {
        this.mAdapterData = data;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder!=null){
            if(holder.mTvName.getText().equals("")) {
                holder.mTvName.setText(mAdapterData.get(position).getmStrName());
                holder.mTvCal.append(" " + String.valueOf(mAdapterData.get(position).getmIntCalories()));
                holder.mTvProt.append(" " + String.valueOf(mAdapterData.get(position).getmIntProtein()) + mStringG);
                holder.mTvCarbs.append(" " + String.valueOf(mAdapterData.get(position).getmIntCarbs()) + mStringG);
                holder.mTvFats.append(" " + String.valueOf(mAdapterData.get(position).getmIntFats()) + mStringG);
                holder.mTvVitA.append(" " + String.valueOf(mAdapterData.get(position).getmIntVitA()));
                holder.mTvVitB.append(" " + String.valueOf(mAdapterData.get(position).getmIntVitB6()));
                holder.mTvVitC.append(" " + String.valueOf(mAdapterData.get(position).getmIntVitC()));
                holder.mTvVitD.append(" " + String.valueOf(mAdapterData.get(position).getmIntVitD()));
                holder.mTvZinc.append(" " + String.valueOf(mAdapterData.get(position).getmIntZinc()));
                holder.mTvMagnesium.append(" " + String.valueOf(mAdapterData.get(position).getmIntMagnesium()));
                holder.mTvIron.append(" " + String.valueOf(mAdapterData.get(position).getmIntIron()));
                holder.mTvQuantity.append(" " + String.valueOf(mAdapterData.get(position).getmIntQuantity()) + mStringG);
                holder.getItemPosition(position);
            }
            final int tempPosition = position;

            final boolean isExpanded = position==mExpandedPosition;
            holder.mLinearLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
            holder.itemView.setActivated(isExpanded);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExpandedPosition = isExpanded ? -1:tempPosition;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        TransitionManager.beginDelayedTransition(mRecyclerView);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mStringG = parent.getResources().getString(R.string.g);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_diary_item,parent,false);

        ViewHolder vh = new ViewHolder(view);

        mRecyclerView = (RecyclerView) parent.findViewById(R.id.rv_diary_screen);

        return vh;
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }
}
