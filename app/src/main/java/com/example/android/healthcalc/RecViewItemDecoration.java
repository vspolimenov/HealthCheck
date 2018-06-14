package com.example.android.healthcalc;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Stanislav on 10/15/2016.
 */
public class RecViewItemDecoration extends RecyclerView.ItemDecoration {

    int offset;

    public RecViewItemDecoration(){

        offset=9;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(offset,offset,offset,offset);

    }

}
