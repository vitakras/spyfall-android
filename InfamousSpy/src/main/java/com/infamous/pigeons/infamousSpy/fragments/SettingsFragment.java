package com.infamous.pigeons.infamousSpy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infamous.pigeons.infamousSpy.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsFragment extends Fragment {

    private LayoutInflater inflater;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        getActivity().setTitle(R.string.settings);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}
