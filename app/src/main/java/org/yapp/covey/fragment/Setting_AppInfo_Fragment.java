package org.yapp.covey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.yapp.covey.R;
import org.yapp.covey.activity.SettingActivity;

import androidx.fragment.app.Fragment;

public class Setting_AppInfo_Fragment  extends Fragment {

    public static Setting_AppInfo_Fragment newInstance() {
        return new Setting_AppInfo_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_appinfo, container, false);

        ((SettingActivity)getActivity()).setCustomAppBar("앱 정보");

        return view;
    }
}
