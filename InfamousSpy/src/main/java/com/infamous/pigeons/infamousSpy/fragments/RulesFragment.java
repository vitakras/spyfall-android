package com.infamous.pigeons.infamousSpy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infamous.pigeons.infamousSpy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RulesFragment extends Fragment {


    public RulesFragment() {
        // Required empty public constructor
    }

    public static RulesFragment newInstance() {
        return new RulesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(getString(R.string.rules));
        return inflater.inflate(R.layout.fragment_rules, container, false);
    }

}
