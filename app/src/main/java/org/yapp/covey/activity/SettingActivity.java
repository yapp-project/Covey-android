package org.yapp.covey.activity;

import android.os.Bundle;
import android.view.View;

import org.yapp.covey.R;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.fragment.Setting_Main_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_setting, Setting_Main_Fragment.newInstance()).commit();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_setting, fragment).commit();
    }

    public void setCustomAppBar(String title){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar(title);
        if(title == "설정")
            customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
            @Override
            public void onBackClick(View v) {
                finish();
            }
        });
        else if(title == "앱 정보")
            customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
                @Override
                public void onBackClick(View v) {
                    replaceFragment(Setting_Main_Fragment.newInstance());
                }
            });
    }
}