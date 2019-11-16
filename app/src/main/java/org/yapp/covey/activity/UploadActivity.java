package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import org.yapp.covey.R;
import org.yapp.covey.etc.CustomAppBar;

public class UploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        setCustomAppBar();
    }
    private void setCustomAppBar(){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar("대타 등록하기");
        customAppBar.setBackClickListener(new CustomAppBar.backClickListener() {
            @Override
            public void onBackClick(View v) {
                finish();
            }
        });
    }
}
