package org.yapp.covey.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.yapp.covey.R;
import org.yapp.covey.activity.SignupActivity;

import androidx.fragment.app.Fragment;

public class Signup_Add_1_Fragment extends Fragment {

    public static Signup_Add_1_Fragment newInstance() {
        return new Signup_Add_1_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_add_1, container, false);
        RelativeLayout next_button = view.findViewById(R.id.signup_add_1_button);
        ImageView prev_button = view.findViewById(R.id.signup_add_1_arrow);

        Spinner genderSpinner = view.findViewById(R.id.signup_add_1_spinner_gender);
        ArrayAdapter genderAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.gender)){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                    return false;
                else
                    return true;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        Spinner ageSpinner = view.findViewById(R.id.signup_add_1_spinner_age);
        ArrayAdapter ageAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.age)){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                    return false;
                else
                    return true;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_Add_2_Fragment.newInstance());
            }
        });
        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_Login_Fragment.newInstance());
            }
        });

        return view;
    }
}
