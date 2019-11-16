package org.yapp.covey.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.yapp.covey.R;
import org.yapp.covey.databinding.ActivityAlertBinding;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.util.Singleton;

public class AlertActivity extends AppCompatActivity {
    ActivityAlertBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_alert);
//        setContentView(R.layout.activity_alert);
        setCustomAppBar();

//        Singleton.retrofit.
    }
    private void setCustomAppBar(){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar("알림");
        customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
            @Override
            public void onBackClick(View v) {
                finish();
            }
        });
    }
}
