package org.yapp.covey.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.yapp.covey.R;
import org.yapp.covey.activity.ProfileEditActivity;
import org.yapp.covey.activity.SettingActivity;

import androidx.fragment.app.Fragment;

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
            }
            case R.id.profile_setting:{
                Intent intentSetting = new Intent(getContext(), SettingActivity.class);
                startActivity(intentSetting);
            }
        }
    }
}
