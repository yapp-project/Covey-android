package org.yapp.covey.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.yapp.covey.R;
import org.yapp.covey.activity.SignupActivity;
import org.yapp.covey.etc.phoneNumClass;
import org.yapp.covey.util.Singleton;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_01_Fragment extends Fragment {
    private static final String TAG = "signup01";
    public static Signup_01_Fragment newInstance() {
        return new Signup_01_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signup_01, container, false);
        final RelativeLayout next_button = view.findViewById(R.id.signup_01_button);
        ImageView prev_button = view.findViewById(R.id.signup_01_arrow);
        final EditText phoneNum = view.findViewById(R.id.signup_01_phoneNum);

        phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(phoneNum.getText().length() == 11) {
                    next_button.setBackground(getResources().getDrawable(R.drawable.red_square));
                    next_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            phoneNumClass body = new phoneNumClass();
                            body.setPhoneNum(phoneNum.getText().toString());
                            phoneVerify(view, body);
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
                ((SignupActivity)getActivity()).replaceFragment(Signup_Login_Fragment.newInstance());
            }
        });

        return view;
    }

    private void phoneVerify(final View view, phoneNumClass body){
        Singleton.retrofit.phoneVerify(body).enqueue(new Callback<phoneNumClass>() {
            @Override
            public void onResponse(Call<phoneNumClass> call, Response<phoneNumClass> response) {
                if (response.isSuccessful()){
                    if (response.code()==201){
                        final EditText phoneNum = view.findViewById(R.id.signup_01_phoneNum);

                        Fragment next = Signup_02_Fragment.newInstance();
                        Bundle bundle = new Bundle();
                        if(getArguments() != null) bundle.putString("snsid", getArguments().getString("snsid"));
                        bundle.putString("phoneNum", phoneNum.getText().toString());
                        next.setArguments(bundle);
                        ((SignupActivity)getActivity()).replaceFragment(next);
                    }
                    else
                        Log.w(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<phoneNumClass> call, Throwable t) {
                Log.w(TAG,"OnFailure phoneVerify");
            }
        });
    }
}
