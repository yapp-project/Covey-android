package org.yapp.covey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import org.yapp.covey.R;
import org.yapp.covey.activity.SignupActivity;

import androidx.fragment.app.Fragment;

public class Signup_03_Fragment extends Fragment {

    public static Signup_03_Fragment newInstance() {
        return new Signup_03_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_03, container, false);
        RelativeLayout next_button = view.findViewById(R.id.signup_03_button);
        ImageView prev_button = view.findViewById(R.id.signup_03_arrow);
        Spinner genderSpinner = view.findViewById(R.id.signup_03_spinner_gender);
        Spinner ageSpinner = view.findViewById(R.id.signup_03_spinner_age);
        Spinner locationSpinner = view.findViewById(R.id.signup_03_spinner_location);
        Spinner locationDetailSpinner = view.findViewById(R.id.signup_03_spinner_location_detail);

        String[] genderStr = getResources().getStringArray(R.array.gender);
        String[] ageStr = getResources().getStringArray(R.array.age);
        String[] locationStr = getResources().getStringArray(R.array.city);

        ArrayAdapter<String> genderAdapter= new ArrayAdapter<String>(getContext(),R.layout.spinner_item, genderStr);
        genderAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        ArrayAdapter<String> ageAdapter= new ArrayAdapter<String>(getContext(),R.layout.spinner_item, ageStr);
        ageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        ArrayAdapter<String> locationAdapter= new ArrayAdapter<String>(getContext(),R.layout.spinner_item, locationStr);
        locationAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_Done_Fragment.newInstance());
            }
        });
        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_02_Fragment.newInstance());
            }
        });

        return view;
    }
}
