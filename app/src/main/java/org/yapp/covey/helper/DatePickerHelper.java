package org.yapp.covey.helper;

import android.content.Context;

import com.yongbeom.aircalendar.core.AirCalendarIntent;

import java.util.ArrayList;
import java.util.Calendar;

public class DatePickerHelper {
    private Context context;
    private ArrayList<String> weekDay;

    private AirCalendarIntent intent;

    public DatePickerHelper(Context context, ArrayList<String> weekDay){
        this.context = context;
        this.weekDay = weekDay;
        setDatePicker();
    }

    private void setDatePicker(){
        intent = new AirCalendarIntent(context);
        intent.isBooking(false);
        intent.isSelect(false);
        intent.isMonthLabels(false);
        intent.setSelectButtonText("선택");
        intent.setResetBtnText("리셋");
        intent.setWeekStart(Calendar.MONDAY);
        intent.setWeekDaysLanguage(AirCalendarIntent.Language.EN);
        intent.setCustomWeekDays(weekDay);
    }

    public AirCalendarIntent getCalendarIntent (){
        return intent;
    }
}
