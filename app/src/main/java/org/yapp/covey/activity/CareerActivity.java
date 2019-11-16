package org.yapp.covey.activity;

import android.os.Bundle;
import android.view.View;

import org.yapp.covey.R;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.fragment.Career_Add_Fragment;
import org.yapp.covey.fragment.Career_Main_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CareerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_career, Career_Main_Fragment.newInstance()).commit();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_career, fragment).commit();
    }

    public void setCustomAppBar(String title){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar(title);
        if(title == "경력사항") {
            customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
                @Override
                public void onBackClick(View v) {
                    finish();
                }
            });
            customAppBar.setAddVisible();
            customAppBar.setAddClickListener(new CustomAppBar.addClickListener(){
                @Override
                public void onAddClick(View v){
                    replaceFragment(Career_Add_Fragment.newInstance());
                }
            });
        }
        else if(title == "경력사항 수정")
            customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
                @Override
                public void onBackClick(View v) {
                    replaceFragment(Career_Main_Fragment.newInstance());
                }
            });
        else if(title == "경력사항 추가")
            customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
                @Override
                public void onBackClick(View v) {
                    replaceFragment(Career_Main_Fragment.newInstance());
                }
            });
    }
}
