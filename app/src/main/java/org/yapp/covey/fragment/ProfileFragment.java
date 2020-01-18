package org.yapp.covey.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.yapp.covey.R;
import org.yapp.covey.activity.CareerActivity;
import org.yapp.covey.activity.ProfileEditActivity;
import org.yapp.covey.activity.SettingActivity;
import org.yapp.covey.etc.userClass;
import org.yapp.covey.etc.userResponseClass;
import org.yapp.covey.util.Singleton;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener{
    private ImageView profile_edit, career_edit, setting;
    private static final String TAG = "Profile";

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        setInitView(rootView);

        return rootView;
    }

    private void setInitView(View view){
        getUser(view);

        profile_edit = view.findViewById(R.id.profile_edit);
        profile_edit.setOnClickListener(this);

        career_edit = view.findViewById(R.id.profile_career_edit);
        career_edit.setOnClickListener(this);

        setting = view.findViewById(R.id.profile_setting);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profile_edit:{
                Intent intentProfileEdit = new Intent(getContext(), ProfileEditActivity.class);
                startActivity(intentProfileEdit);
                break;
            }
            case R.id.profile_career_edit:{
                Intent intentCareerEdit = new Intent(getContext(), CareerActivity.class);
                startActivity(intentCareerEdit);
                break;
            }
            case R.id.profile_setting:{
                Intent intentSetting = new Intent(getContext(), SettingActivity.class);
                startActivity(intentSetting);
            }
        }
    }

    private void getUser(final View view){
        Singleton.retrofit.getUser().enqueue(new Callback<userResponseClass>() {
            @Override
            public void onResponse(Call<userResponseClass> call, Response<userResponseClass> response) {
                if (response.isSuccessful()){
                    if (response.code()==200){
                        userClass user = response.body().getUser();
                        TextView name = view.findViewById(R.id.profile_name);
                        TextView ageGender = view.findViewById(R.id.profile_age_gender);
                        TextView phone = view.findViewById(R.id.profile_phone);
                        TextView intro = view.findViewById(R.id.profile_introduction);
                        TextView address = view.findViewById(R.id.profile_address);
                        name.setText(user.getName());
                        if(user.getGender())
                            ageGender.setText(user.getAge() + " / 남");
                        else
                            ageGender.setText(user.getAge() + " / 여");
                       phone.setText(user.getPhoneNum());
                       address.setText(user.getAddress1() + " " + user.getAddress2());
                       intro.setText(user.getIntro());
                    }
                    else
                        Log.w(TAG, String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<userResponseClass> call, Throwable t) {
                Log.w(TAG,"OnFailure phoneVerify");
            }
        });
    }
}
