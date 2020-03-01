package org.yapp.covey.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yongbeom.aircalendar.core.AirCalendarIntent;

import org.yapp.covey.R;
import org.yapp.covey.adapter.AdapterCustomSpinner;
import org.yapp.covey.adapter.AdapterUploadImageList;
import org.yapp.covey.databinding.ActivityUploadBinding;
import org.yapp.covey.etc.CustomAppBar;
import org.yapp.covey.helper.PermissionHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnMultiSelectedListener;

public class UploadActivity extends AppCompatActivity{
    ActivityUploadBinding binding;
    AdapterCustomSpinner mAdapterSpinner;
    AdapterUploadImageList mAdapterImageList = new AdapterUploadImageList();
    PermissionHelper permissionHelper = new PermissionHelper(this);

    private String startDate, endDate, selectDate, selectAddress;

    private static int REQUEST_CODE = 202;
    private static int REQUEST_CODE_ADDRESS = 204;

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

        setCustomAppBar();
        getPermission();
        binding.recyclerUploadImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        binding.tvSelectAddress.setOnClickListener(view -> {
            Intent intentAddress = new Intent(getApplicationContext(), SearchAddressActivity.class);
            startActivityForResult(intentAddress, REQUEST_CODE_ADDRESS);
        });
    }
    private void setCustomAppBar(){
        CustomAppBar customAppBar = new CustomAppBar(this, getSupportActionBar());
        customAppBar.setCustomAppBar("대타 등록하기");
        customAppBar.setBackClickListener(v -> finish());
    }

    private void getPermission(){
        permissionHelper.getPermission(100);
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
        intent.setSelectButtonText("선택");
        intent.setResetBtnText("리셋");
        intent.setWeekStart(Calendar.MONDAY);
        intent.setWeekDaysLanguage(AirCalendarIntent.Language.EN);

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
        if (resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE && data != null){
                startDate = data.getStringExtra(CustomCalendarActivity.RESULT_SELECT_START_DATE);
                endDate = data.getStringExtra(CustomCalendarActivity.RESULT_SELECT_END_DATE);
                binding.tvPostDateStart.setText(startDate);
                binding.tvPostDateEnd.setText(endDate);
            }
            else if(requestCode == REQUEST_CODE_ADDRESS){
                assert data != null;
                selectAddress = data.getStringExtra("select Data");
                binding.tvSelectAddress.setText(selectAddress);
            }
        }
    }

    public void dateSelect(View view) {
        showDatePicker();
    }

    public void addImage(View view) {
        TedImagePicker.with(this)
                .cameraTileBackground(R.color.salmon)
                .buttonBackground(R.color.tomato)
                .max(3-mAdapterImageList.getItemCount(), "최대 3장까지 선택 가능합니다")
                .startMultiImage(list -> {
                    mAdapterImageList.addUriList((ArrayList<Uri>) list);
                });
        binding.recyclerUploadImage.setAdapter(mAdapterImageList);
    }
}
