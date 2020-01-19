package org.yapp.covey.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterCustomSpinner;
import org.yapp.covey.databinding.ActivityUploadBinding;
import org.yapp.covey.etc.CalculateDate;
import org.yapp.covey.etc.CustomAppBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UploadActivity extends AppCompatActivity {
    ActivityUploadBinding binding;
    AdapterCustomSpinner mAdapterSpinner;

    private int year, month, day ;
    private String startDate, endDate;

    List<String> hourArray = new ArrayList<>();
    List<String> minArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload);

        setHourArray(hourArray);
        setMinArray(minArray);

        setSpinner(binding.spinnerCategory, Arrays.asList(getResources().getStringArray(R.array.category)),"카테고리를 선택하세요");
        setSpinner(binding.spinnerTimeHour, hourArray,"시");
        setSpinner(binding.spinnerTimeMin, minArray ,"분");

        binding.tvPostDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        binding.tvPostDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

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

    private void setHourArray(List<String> hourArray){
        for (int i = 0 ; i<= 24; i++){
            hourArray.add(String.valueOf(i));
        }
    }

    private void setMinArray(List<String> minArray){
        for (int i = 0 ; i<= 5 ; i++){
            minArray.add(String.valueOf(i*10));
        }
    }

    private void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                year = yyyy;
                month = mm+1;
                day = dd;
                startDate = year+"-"+month+"-"+day;
            }
        }, 2020,1,1);
        datePickerDialog.setTitle("날짜 선택");

        datePickerDialog.show();
    }
}
