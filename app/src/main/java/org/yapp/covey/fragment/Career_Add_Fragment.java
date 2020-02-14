package org.yapp.covey.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import org.yapp.covey.R;
import org.yapp.covey.activity.CareerActivity;
import org.yapp.covey.activity.SettingActivity;

import androidx.fragment.app.Fragment;

public class Career_Add_Fragment extends Fragment implements View.OnClickListener{

    public static Career_Add_Fragment newInstance() {
        return new Career_Add_Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_career_add, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ((CareerActivity)getActivity()).setCustomAppBar("경력사항 추가");

        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*case R.id.setting_app_info:{
                ((SettingActivity)getActivity()).replaceFragment(Setting_AppInfo_Fragment.newInstance());
                break;
            }*/
        }
    }
}
