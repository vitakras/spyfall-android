package com.vitaliy.krasylovets.spyfall;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This Class is Responsible for
 * making a non Scrollable list
 * Created by vitaliy on 2016-02-10.
 */
public class LinearListLayout {

    // Instance Variables
    private ViewGroup viewGroup;
    private Observer observer;
    private Adapter adapter;

    public LinearListLayout(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.observer = new Observer(this);
        this.adapter = null;
    }

    public void setAdapter(Adapter adapter) {
        if (this.adapter != null) {
            this.adapter.unregisterDataSetObserver(this.observer);
        }

        this.adapter = adapter;
        this.adapter.registerDataSetObserver(this.observer);
        this.observer.onChanged();
    }

    private class Observer extends DataSetObserver {

        // Instance Variables
        LinearListLayout linearListLayout;

        public Observer(LinearListLayout linearListLayout) {
            this.linearListLayout = linearListLayout;
        }

        @Override
        public void onChanged() {
            List<View> oldViews = new ArrayList<>(viewGroup.getChildCount());

            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                oldViews.add(viewGroup.getChildAt(i));
            }

            Iterator<View> iter = oldViews.iterator();

            viewGroup.removeAllViews();

            for (int i = 0; i < adapter.getCount(); i++) {
                View convertView = iter.hasNext() ? iter.next() : null;
                viewGroup.addView(adapter.getView(i, convertView, viewGroup));
            }

            super.onChanged();
        }

        @Override
        public void onInvalidated() {
            viewGroup.removeAllViews();
            super.onInvalidated();
        }
    }

}
