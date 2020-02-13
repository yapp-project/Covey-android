package org.yapp.covey.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;

import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import org.yapp.covey.R;

public class CustomCalendarDialog extends CalendarDialog {
    CalendarView calendarView;

    public CustomCalendarDialog(@NonNull Context context, OnDaysSelectionListener onDaysSelectionListener) {
        super(context, onDaysSelectionListener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_custom_calendar_dialog);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_calendar);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        calendarView = findViewById(R.id.calendar_view);
        initView();
    }
    public void initView(){
        calendarView.setSelectedDayBackgroundColor(Color.parseColor("#fa8274"));
        calendarView.setSelectedDayBackgroundStartColor(Color.parseColor("#fa8274"));
        calendarView.setSelectedDayBackgroundEndColor(Color.parseColor("#fa8274"));
        calendarView.setSelectionType(SelectionType.RANGE);
    }
}
