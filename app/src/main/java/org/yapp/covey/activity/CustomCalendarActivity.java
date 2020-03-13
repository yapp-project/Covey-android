package org.yapp.covey.activity;

import android.os.Bundle;

import com.yongbeom.aircalendar.AirCalendarDatePickerActivity;

import org.yapp.covey.R;

public class CustomCalendarActivity extends AirCalendarDatePickerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar);
    }
}
