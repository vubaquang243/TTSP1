package com.seatech.framework.strustx;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.bean.WriteTag;

public class AppWriteTag extends WriteTag 
{
    public static final String QUARTER_DATE_FORMAT = "q/yyyy";
    public static final String QUARTER_STRING_FORMAT = "Q";
    
    protected String formatValue(Object valueToFormat) throws JspException {
        Calendar dateValue;
        int month;
        int year;
        String quarterStr = "";
        
        if ((valueToFormat instanceof Date || valueToFormat instanceof java.sql.Date) && formatStr.equals(QUARTER_DATE_FORMAT)) 
        {
            dateValue = Calendar.getInstance();
            dateValue.setTime((Date) valueToFormat);
            year = dateValue.get(Calendar.YEAR);
            month = dateValue.get(Calendar.MONTH) + 1;
            if(month == 1 || month == 2 || month == 3) 
            {
                quarterStr = "1";
            }else if(month == 4 || month == 5 || month == 6) 
            {
                quarterStr = "2";
            }else if(month == 7 || month == 8 || month == 9) 
            {
                quarterStr = "3";
            }else if(month == 10 || month == 11 || month == 12) 
            {
                quarterStr = "4";
            }
            return QUARTER_STRING_FORMAT + quarterStr + "/" + Integer.toString(year);
        } else 
        {
            return super.formatValue(valueToFormat);
        }
    }
}