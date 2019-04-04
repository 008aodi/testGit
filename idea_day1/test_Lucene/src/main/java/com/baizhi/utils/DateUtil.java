package com.baizhi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getStringDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        return format;
    }
    public static Date getDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse=null;
        try {
            parse = sdf.parse("2018-12-24");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
