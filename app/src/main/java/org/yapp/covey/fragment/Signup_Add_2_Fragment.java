package org.yapp.covey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.yapp.covey.R;
import org.yapp.covey.activity.SignupActivity;

import androidx.fragment.app.Fragment;

public class Signup_Add_2_Fragment extends Fragment {

    public static Signup_Add_2_Fragment newInstance() {
        return new Signup_Add_2_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_add_2, container, false);
        RelativeLayout next_button = view.findViewById(R.id.signup_add_2_button);
        ImageView prev_button = view.findViewById(R.id.signup_add_2_arrow);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_Done_Fragment.newInstance());
            }
        });
        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_Add_1_Fragment.newInstance());
            }
        });

        return view;
    }
}
