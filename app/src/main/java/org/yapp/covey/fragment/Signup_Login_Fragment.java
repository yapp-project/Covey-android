package org.yapp.covey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.yapp.covey.R;
import org.yapp.covey.activity.SignupActivity;

import androidx.fragment.app.Fragment;

public class Signup_Login_Fragment extends Fragment {

    public static Signup_Login_Fragment newInstance() {
        return new Signup_Login_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_login, container, false);
        RelativeLayout login_kakao = view.findViewById(R.id.signup_login_btn_kakao);
        RelativeLayout login_facebook = view.findViewById(R.id.signup_login_btn_facebook);

        login_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_Add_1_Fragment.newInstance());
            }
        });
        login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_Add_1_Fragment.newInstance());
            }
        });

        return view;
    }
}
