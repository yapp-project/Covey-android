package org.yapp.covey.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculateDateHelper {
    private String today = getCurrentDate();
    int currentYear, currentMonth, currentDay;

    public long calDateBetween(String date)
    {
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            // date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
            Date FirstDate = format.parse(date);
            Date SecondDate = format.parse(today);

            long calDate = FirstDate.getTime() - SecondDate.getTime();
            long calDateDays = calDate / ( 24*60*60*1000);

            calDateDays = Math.abs(calDateDays);
            return calDateDays;
        }
        catch(ParseException e)
        {
            // 예외 처리
        }
        return 0;
    }

    private String getCurrentDate(){
        // Date 로 구하기
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");

        return fm1.format(new Date());
    }
    public void setCurrentDate(){
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatDay = new SimpleDateFormat("dd");

        currentYear = Integer.parseInt(formatYear.format(new Date()));
        currentMonth = Integer.parseInt(formatMonth.format(new Date()));
        currentDay = Integer.parseInt(formatDay.format(new Date()));
    }
}
