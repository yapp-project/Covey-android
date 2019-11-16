package org.yapp.covey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.yapp.covey.R;
import org.yapp.covey.activity.SettingActivity;
import org.yapp.covey.activity.SignupActivity;
import org.yapp.covey.etc.CustomAppBar;

import androidx.fragment.app.Fragment;

public class Setting_Main_Fragment extends Fragment implements View.OnClickListener{
    private TextView appInfo, logout;

    public static Setting_Main_Fragment newInstance() {
        return new Setting_Main_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_main, container, false);

        appInfo = view.findViewById(R.id.setting_app_info);
        appInfo.setOnClickListener(this);

        logout = view.findViewById(R.id.setting_logout);
        logout.setOnClickListener(this);

        ((SettingActivity)getActivity()).setCustomAppBar("설정");

        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setting_app_info:{
                ((SettingActivity)getActivity()).replaceFragment(Setting_AppInfo_Fragment.newInstance());
                break;
            }
            case R.id.setting_logout:{
            }
        }
    }
}
