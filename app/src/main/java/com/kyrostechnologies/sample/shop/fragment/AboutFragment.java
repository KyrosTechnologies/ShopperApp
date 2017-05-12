package com.kyrostechnologies.sample.shop.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;


import com.kyrostechnologies.sample.shop.R;



public class AboutFragment extends Fragment {
    public static String TAG_CATEGORY = "com.app.sample.shop.tagCategory";


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login, null);

        return view;

    }
}