package com.kyrostechnologies.sample.shop.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.kyrostechnologies.sample.shop.LoginActivity;
import com.kyrostechnologies.sample.shop.R;

import DataBases.DBHandler;

/**
 * Created by Rohin on 02-03-2017.
 */

public class LogoutFragment extends android.support.v4.app.Fragment {

    private View view;
    private TextView log_out;
    private DBHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.logout, null);
        db = new DBHandler(getContext());

        log_out=(TextView)view.findViewById(R.id.log_out);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db!=null){
                    db.deleteContact();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                try {
                    LoginManager.getInstance().logOut();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return view;
    }


}

