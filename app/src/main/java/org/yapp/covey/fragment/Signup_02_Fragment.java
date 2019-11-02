package org.yapp.covey.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.yapp.covey.R;
import org.yapp.covey.activity.SignupActivity;

import androidx.fragment.app.Fragment;

public class Signup_02_Fragment extends Fragment {

    public static Signup_02_Fragment newInstance() {
        return new Signup_02_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_02, container, false);
        String phoneNumStr = "";
        if(getArguments() != null) phoneNumStr = getArguments().getString("phoneNum");

        final RelativeLayout next_button = view.findViewById(R.id.signup_02_button);
        ImageView prev_button = view.findViewById(R.id.signup_02_arrow);
        final TextView phoneNum = view.findViewById(R.id.signup_02_phoneNum);
        final EditText certification = view.findViewById(R.id.signup_02_certification);

        phoneNum.setText(phoneNumStr);
        certification.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(certification.getText().length() == 7) {
                    next_button.setBackground(getResources().getDrawable(R.drawable.red_square));
                    next_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Fragment next = Signup_03_Fragment.newInstance();
                            Bundle bundle = new Bundle();
                            bundle.putString("phoneNum", phoneNum.getText().toString());
                            next.setArguments(bundle);
                            ((SignupActivity)getActivity()).replaceFragment(next);
                        }
                    });
                }
                else {
                    next_button.setBackground(getResources().getDrawable(R.drawable.gray_square));
                    next_button.setOnClickListener(null);
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });

        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_01_Fragment.newInstance());
            }
        });

        return view;
    }
}
