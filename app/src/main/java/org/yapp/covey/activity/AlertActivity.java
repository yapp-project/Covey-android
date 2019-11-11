package org.yapp.covey.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.yapp.covey.R;
import org.yapp.covey.etc.CustomAppBar;

public class AlertActivity extends AppCompatActivity {

//    CustomAppBar customAppBar = new CustomAppBar();
//    ActionBar actionBar = getSupportActionBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

//        customAppBar.setCustomAppBar(actionBar,this, "알림");
    }
}
