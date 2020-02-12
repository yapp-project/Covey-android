package org.yapp.covey.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.OrientationHelper;

import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterCustomSpinner;
import org.yapp.covey.databinding.ActivityUploadBinding;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.etc.CustomCalendarDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UploadActivity extends AppCompatActivity {
    ActivityUploadBinding binding;
    AdapterCustomSpinner mAdapterSpinner;

    CalendarView calendarView;

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
        setSpinner(binding.spinnerTimeHour, hourArray,"시");
        setSpinner(binding.spinnerTimeMin, minArray ,"분");

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
        CustomCalendarDialog calendarDialog = new CustomCalendarDialog(this, new OnDaysSelectionListener() {
            @Override
            public void onDaysSelected(List<Day> selectedDays) {
                startDate = String.valueOf(selectedDays.get(0));
                endDate = String.valueOf(selectedDays.get(selectedDays.size()));
                binding.tvPostDateStart.setText(startDate);
                binding.tvPostDateEnd.setText(endDate);
            }
        });
//        calendarDialog.setCalendarBackgroundColor(Color.parseColor("#fa8274"));
//        calendarDialog.setSelectionType(SelectionType.RANGE);

        calendarDialog.show();
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            if(data != null){
//                startDate = data.getStringExtra(AirCalendarDatePickerActivity.RESULT_SELECT_START_DATE);
//                endDate = data.getStringExtra(AirCalendarDatePickerActivity.RESULT_SELECT_END_DATE);
//                binding.tvPostDateStart.setText(startDate);
//                binding.tvPostDateEnd.setText(endDate);
//            }
//        }
//    }
}
