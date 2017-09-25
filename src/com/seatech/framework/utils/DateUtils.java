package com.seatech.framework.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;


public class DateUtils {
    public DateUtils() {
        super();
    }

    public static Long DateToLong(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
            String strDateString;
            strDateString = fm.format(date);
            return new Long(strDateString);
        }
    }
    public static String LongToStrDateDDMMYYYY(Long nYYYYMMDD){
        if(nYYYYMMDD == null)
            return "";
        String strDDMMYYYY = nYYYYMMDD.toString();
        if (strDDMMYYYY == null || strDDMMYYYY.equalsIgnoreCase("") || strDDMMYYYY.length() != 8) {
            return "";
        } else {
          String strDate, strMonth, strYear;
          strYear = strDDMMYYYY.substring(0, 4);
          strMonth = strDDMMYYYY.substring(4, 6);
          strDate = strDDMMYYYY.substring(6, 8);
          strDDMMYYYY = strDate + "/" + strMonth + "/" + strYear;
            return strDDMMYYYY;
        }
    }
    
    public static Long DateToLong(String strDDMMYYYY) {
        if (strDDMMYYYY == null || strDDMMYYYY.equalsIgnoreCase("")) {
            return null;
        } else {
          String strDate, strMonth, strYear;
          strDate = strDDMMYYYY.substring(0, 2);
          strMonth = strDDMMYYYY.substring(3, 5);
          strYear = strDDMMYYYY.substring(6, 10);
          strDDMMYYYY = strYear+strMonth+strDate;
            return new Long(strDDMMYYYY);
        }
    }

    public static Date LongToDate(Long number) {
        if (number == null) {
            return null;
        } else {
            SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
            ParsePosition pos = new ParsePosition(0);
            Date d = fm.parse(String.valueOf(number), pos);
            return d;
        }
    }

    public static Long getCurrentDate() {
        Date date = new Date();
        return DateToLong(date);
    }
    
    public static String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    }
    public static int getHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    public static int getMinute(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }
    public static int getSecond(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.SECOND);
    }
}
