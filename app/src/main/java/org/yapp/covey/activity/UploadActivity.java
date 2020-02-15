package org.yapp.covey.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.yongbeom.aircalendar.AirCalendarDatePickerActivity;
import com.yongbeom.aircalendar.core.AirCalendarIntent;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterCustomSpinner;
import org.yapp.covey.databinding.ActivityUploadBinding;
import org.yapp.covey.etc.CustomAppBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class UploadActivity extends AppCompatActivity {
    ActivityUploadBinding binding;
    AdapterCustomSpinner mAdapterSpinner;


    private String startDate, endDate, selectDate;

    private static int REQUEST_CODE = 202;

    List<String> hourArray = new ArrayList<>();
    List<String> minArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload);

        setHourArray(hourArray);
        setMinArray(minArray);

        setSpinner(binding.spinnerCategory, Arrays.asList(getResources().getStringArray(R.array.category)),"카테고리를 선택하세요");
        setSpinner(binding.spinnerStartTimeHour, hourArray,"시");
        setSpinner(binding.spinnerStartTimeMin, minArray ,"분");

        setSpinner(binding.spinnerEndTimeHour, hourArray,"시");
        setSpinner(binding.spinnerEndTimeMin, minArray ,"분");

        binding.tvPostDateStart.setOnClickListener(new View.OnClickListener() {
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

    public void showDatePicker(){
        AirCalendarIntent intent = new AirCalendarIntent(this);
        intent.isBooking(false);
        intent.isSelect(false);
        intent.isMonthLabels(false);
        intent.setSelectButtonText("선택"); //the select button text
        intent.setResetBtnText("리셋"); //the reset button text
        intent.setWeekStart(Calendar.MONDAY);
        intent.setWeekDaysLanguage(AirCalendarIntent.Language.EN); //language for the weekdays

        ArrayList<String> weekDay = new ArrayList<>();
        weekDay.add("MON");
        weekDay.add("TUE");
        weekDay.add("WED");
        weekDay.add("THR");
        weekDay.add("FRI");
        weekDay.add("SAT");
        weekDay.add("SUN");
        intent.setCustomWeekDays(weekDay); //custom weekdays

        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if(data != null){
                startDate = data.getStringExtra(CustomCalendarActivity.RESULT_SELECT_START_DATE);
                endDate = data.getStringExtra(CustomCalendarActivity.RESULT_SELECT_END_DATE);
                binding.tvPostDateStart.setText(startDate);
                binding.tvPostDateEnd.setText(endDate);
            }
        }
    }
}
