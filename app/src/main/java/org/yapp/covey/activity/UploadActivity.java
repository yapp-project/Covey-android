package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterCustomSpinner;
import org.yapp.covey.databinding.ActivityUploadBinding;
import org.yapp.covey.etc.CustomAppBar;

import java.util.Arrays;
import java.util.List;

public class UploadActivity extends AppCompatActivity {
    ActivityUploadBinding binding;
    AdapterCustomSpinner mAdapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload);
//        setContentView(R.layout.activity_upload);

        setSpinner(binding.spinnerCategory, Arrays.asList(getResources().getStringArray(R.array.category)),"카테고리를 선택하세요");
        setSpinner(binding.spinnerTimeHour, Arrays.asList(getResources().getStringArray(R.array.category)),"시");
        setSpinner(binding.spinnerTimeMin, Arrays.asList(getResources().getStringArray(R.array.category)),"분");

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

    private void setSpinner(Spinner spinner, List<String> spinnerData, String title){
        mAdapterSpinner = new AdapterCustomSpinner(this,spinnerData, title);
        spinner.setAdapter(mAdapterSpinner);
    }
}
