package org.yapp.covey.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.yapp.covey.R;
import org.yapp.covey.activity.MainActivity;
import org.yapp.covey.activity.SignupActivity;

import androidx.fragment.app.Fragment;

public class Signup_Done_Fragment extends Fragment {

    public static Signup_Done_Fragment newInstance() {
        return new Signup_Done_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_done, container, false);
        final RelativeLayout next_button = view.findViewById(R.id.signup_done_button);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getContext(), MainActivity.class);
                startActivity(mainIntent);
                ((SignupActivity)getActivity()).finish();
            }
        });

        return view;
    }
}
