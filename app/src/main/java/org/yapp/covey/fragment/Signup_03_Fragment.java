package org.yapp.covey.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import org.yapp.covey.R;
import org.yapp.covey.activity.SignupActivity;
import org.yapp.covey.etc.userClass;
import org.yapp.covey.util.Singleton;

import java.lang.reflect.Field;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.annotation.Dimension.DP;

public class Signup_03_Fragment extends Fragment {

    private static final String TAG = "signup03";

    public static Signup_03_Fragment newInstance() {
        return new Signup_03_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_03, container, false);
        final RelativeLayout next_button = view.findViewById(R.id.signup_03_button);
        ImageView prev_button = view.findViewById(R.id.signup_03_arrow);
        final EditText name = view.findViewById(R.id.signup_03_name);
        final Spinner genderSpinner = view.findViewById(R.id.signup_03_spinner_gender);
        final Spinner ageSpinner = view.findViewById(R.id.signup_03_spinner_age);
        final Spinner locationSpinner = view.findViewById(R.id.signup_03_spinner_location);
        final Spinner locationDetailSpinner = view.findViewById(R.id.signup_03_spinner_location_detail);

        String[] genderStr = getResources().getStringArray(R.array.gender);
        String[] ageStr = getResources().getStringArray(R.array.age);
        String[] locationStr = getResources().getStringArray(R.array.city);

        final DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(name.getText().length() > 1 && name.getText().length() < 6) {
                    next_button.setBackground(getResources().getDrawable(R.drawable.red_square));
                    next_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String userName = name.getText().toString();
                            String userAge = ageSpinner.getSelectedItem().toString();
                            String userGender = genderSpinner.getSelectedItem().toString();
                            String address1 = locationSpinner.getSelectedItem().toString();
                            String address2 = locationDetailSpinner.getSelectedItem().toString();
                            userClass user = new userClass();
                            user.setName(userName);
                            user.setAge(userAge);
                            if(userGender == "남자") user.setGender(true);
                            else user.setGender(false);
                            user.setAddress1(address1);
                            user.setAddress2(address2);
                            userInfo(user);
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

        ArrayAdapter<String> genderAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, genderStr);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        ArrayAdapter<String> ageAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, ageStr);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        ArrayAdapter<String> locationAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, locationStr);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String[] locationDetailStr = null;
                switch(position) {
                    case 0:
                        locationDetailStr = getResources().getStringArray(R.array.seoul);
                        break;
                    case 1:
                        locationDetailStr = getResources().getStringArray(R.array.kyung_gi);
                        break;
                    case 2:
                        locationDetailStr = getResources().getStringArray(R.array.incheon);
                        break;
                    case 3:
                        locationDetailStr = getResources().getStringArray(R.array.busan);
                        break;
                    case 4:
                        locationDetailStr = getResources().getStringArray(R.array.daegu);
                        break;
                    case 5:
                        locationDetailStr = getResources().getStringArray(R.array.daejeon);
                        break;
                    case 6:
                        locationDetailStr = getResources().getStringArray(R.array.ulsan);
                        break;
                    case 7:
                        locationDetailStr = getResources().getStringArray(R.array.gwangju);
                        break;
                    case 8:
                        locationDetailStr = getResources().getStringArray(R.array.gangwon);
                        break;
                    case 9:
                        locationDetailStr = getResources().getStringArray(R.array.chungnam);
                        break;
                    case 10:
                        locationDetailStr = getResources().getStringArray(R.array.chungbuk);
                        break;
                    case 11:
                        locationDetailStr = getResources().getStringArray(R.array.jeonnam);
                        break;
                    case 12:
                        locationDetailStr = getResources().getStringArray(R.array.jeonbuk);
                        break;
                    case 13:
                        locationDetailStr = getResources().getStringArray(R.array.kyungnam);
                        break;
                    case 14:
                        locationDetailStr = getResources().getStringArray(R.array.kyungbuk);
                        break;
                    case 15:
                        locationDetailStr = getResources().getStringArray(R.array.jeju);
                }
                ArrayAdapter<String> locationDetailAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, locationDetailStr);
                locationDetailAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                locationDetailSpinner.setAdapter(locationDetailAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}

        });

        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignupActivity)getActivity()).replaceFragment(Signup_02_Fragment.newInstance());
            }
        });

        return view;
    }

    private void userInfo(userClass body){
        Log.w(TAG, "userinfo 실행");
        Singleton.retrofit.userInfo(body).enqueue(new Callback<userClass>() {
            @Override
            public void onResponse(Call<userClass> call, Response<userClass> response) {
                Log.w(TAG, "userinfo response");
                Log.w(TAG, response.toString());
                if (response.isSuccessful()){
                    Log.w(TAG, String.valueOf(response.code()));
                    if (response.code() == 201){
                        Fragment next = Signup_Done_Fragment.newInstance();
                        ((SignupActivity)getActivity()).replaceFragment(next);
                    }
                    else
                        Log.w(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<userClass> call, Throwable t) {
                Log.w(TAG,"OnFailure");
            }
        });
    }
}
