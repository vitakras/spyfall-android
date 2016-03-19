package com.vitaliy.krasylovets.spyfall;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.concurrent.RecursiveAction;

/**
 * Divider Drawer for Recycle view, example taken from
 * Google
 * Created by vitaliy on 2016-03-16.
 */
public class DividerItemDecorator extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable drawable;

    public DividerItemDecorator(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.drawable = a.getDrawable(0);
        a.recycle();
    }

    /**
     * Custom divider will be used
     */
    public DividerItemDecorator(Context context, int resId) {
        this.drawable = ContextCompat.getDrawable(context,resId);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int ty = (int)(child.getTranslationY() + 0.5f);
            int dividerTop = child.getBottom() + params.bottomMargin + ty;
            int dividerBottom = dividerTop + drawable.getIntrinsicHeight();

            drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            drawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


        outRect.top = drawable.getIntrinsicHeight();
    }
}
