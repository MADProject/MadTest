package com.mobile.absl.automatedbarcode.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.absl.automatedbarcode.R;

/**
 * Created by user on 27/09/2018.
 */

public class HomeFragment extends Fragment {

    private TextView textViewName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}
